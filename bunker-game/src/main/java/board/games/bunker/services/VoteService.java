package board.games.bunker.services;

import static board.games.bunker.params.ErrorMessage.VOTES_NOT_FOUND;

import board.games.bunker.MessageCreator;
import board.games.bunker.dto.response.KickedPlayerDTO;
import board.games.bunker.dto.response.VotesInfoDto;
import board.games.bunker.entity.Message;
import board.games.bunker.entity.Player;
import board.games.bunker.entity.Votes;
import board.games.bunker.entity.repo.MessageRepository;
import board.games.bunker.entity.repo.VoteRepository;
import board.games.bunker.entity.session.Session;
import board.games.bunker.entity.session.repo.SessionRepository;
import board.games.bunker.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
public class VoteService {

    private static final String EMPTY_STRING = "";

    private final VoteRepository voteRepository;

    private final PlayerService playerService;

    private final SessionWebSocketService sessionWebSocketService;

    private final MessageRepository messageRepository;

    private final SessionRepository sessionRepository;

    private final SessionCommonService sessionCommonService;

    public VoteService(VoteRepository voteRepository,
                       PlayerService playerService,
                       SessionWebSocketService sessionWebSocketService, MessageRepository messageRepository,
                       SessionRepository sessionRepository, SessionCommonService sessionCommonService) {
        this.voteRepository = voteRepository;
        this.playerService = playerService;
        this.sessionWebSocketService = sessionWebSocketService;
        this.messageRepository = messageRepository;
        this.sessionRepository = sessionRepository;
        this.sessionCommonService = sessionCommonService;
    }

    @Transactional
    public List<VotesInfoDto> vote(String votingPlayerName, String votedForPlayerName, String sessionId) {
        Player votingPlayer = playerService.getPlayer(sessionId, votingPlayerName);
        if (!votingPlayer.isCanVote()) {
            throw new RuntimeException("Player cannot vote");
        }
        if ((votingPlayer.isVoteForYourself() && !votingPlayer.getName().equals(votedForPlayerName))
                || (votingPlayer.getCantVoteFor() != null
                        && votingPlayer.getCantVoteFor().contains(votedForPlayerName))) {
            throw new RuntimeException("Cannot vote for this player");
        }
        Votes votes = this.getVote(votedForPlayerName, sessionId);
        if (votingPlayer.isDoubleVote()
                || (votes.getDoubledVotesFor() != null && votes.getDoubledVotesFor().contains(votedForPlayerName))) {
            votes.setVotesNumber(votes.getVotesNumber() + 2);
        } else {
            votes.setVotesNumber(votes.getVotesNumber() + 1);
        }
        this.saveVote(votes);
        Session session = sessionCommonService.getSession(sessionId);
        Message message = MessageCreator.createVoteMessage(votingPlayerName);
        messageRepository.save(message);
        session.getMessages().add(message);
        sessionRepository.saveAndFlush(session);
        return this.getAllVotesInSession(sessionId).stream().map(vote -> new VotesInfoDto(vote.getPlayerName(),
                vote.getVotesNumber())).toList();
    }

    @Transactional
    public KickedPlayerDTO endVoting(String sessionId) {
        List<Votes> votes = this.getAllVotesInSession(sessionId);
        Votes maxVotes = votes.stream()
                .max(Comparator.comparing(Votes::getVotesNumber))
                .orElseThrow(() -> new RuntimeException("Cannot find person with max votes"));
        List<Votes> maxVotedPlayersList =
                votes.stream().filter(vote -> vote.getVotesNumber().equals(maxVotes.getVotesNumber())).toList();
        if (maxVotedPlayersList.size() > 1) {
            votes.forEach(vote -> {
                vote.setVotesNumber(0);
                voteRepository.saveAndFlush(vote);
            });
            throw new RuntimeException(
                    "Need to revote. More than one player has " + maxVotes.getVotesNumber() + " votes");
        }

        playerService.getAllPlayers().forEach(player -> {
            player.setCantVoteFor(EMPTY_STRING);
            player.setCanVote(Boolean.TRUE);
            player.setDoubleVote(Boolean.FALSE);
            player.setVoteForYourself(Boolean.FALSE);
        });
        votes.forEach(vote -> {
            vote.setVotesNumber(0);
            vote.setDoubledVotesFor(EMPTY_STRING);
            this.saveVote(vote);
        });
        return sessionWebSocketService.kickOutPlayer(sessionId, maxVotes.getPlayerName());
    }

    @Transactional(readOnly = true)
    public Votes getVote(String playerName, String sessionId) {
        return voteRepository.findVotesByPlayerNameAndSessionId(playerName, sessionId)
                .orElseThrow(() -> new ResourceNotFoundException(VOTES_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public List<Votes> getAllVotesInSession(String sessionId) {
        return voteRepository.getAllBySessionId(sessionId);
    }

    @Transactional
    public void saveVote(Votes vote) {
        voteRepository.saveAndFlush(vote);
    }
}
