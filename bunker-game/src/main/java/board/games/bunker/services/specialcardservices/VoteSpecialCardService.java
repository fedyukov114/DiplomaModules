package board.games.bunker.services.specialcardservices;

import board.games.bunker.entity.Player;
import board.games.bunker.services.PlayerService;
import board.games.bunker.services.VoteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VoteSpecialCardService {

    private static final String SEPARATOR = " ;";

    private final PlayerService playerService;

    private final VoteService voteService;

    public VoteSpecialCardService(PlayerService playerService, VoteService voteService) {
        this.playerService = playerService;
        this.voteService = voteService;
    }

    @Transactional
    public void doubleVote(String sessionId, String playerName) {
        Player player = playerService.getPlayer(sessionId, playerName);
        player.setDoubleVote(Boolean.TRUE);
        playerService.savePlayer(player);
    }

    @Transactional
    public void notVote(String sessionId, String playerName) {
        Player player = playerService.getPlayer(sessionId, playerName);
        player.setCanVote(Boolean.FALSE);
        playerService.savePlayer(player);
    }

    @Transactional
    public void voteForYourself(String sessionId, String playerName) {
        Player player = playerService.getPlayer(sessionId, playerName);
        player.setVoteForYourself(Boolean.TRUE);
        playerService.savePlayer(player);
    }

    @Transactional
    public void notVoteForYou(String sessionId, String playerName, String playerNameNoteVoteFor) {
        Player player = playerService.getPlayer(sessionId, playerName);
        player.setCantVoteFor(
                player.getCantVoteFor() == null ? "" : player.getCantVoteFor() + SEPARATOR + playerNameNoteVoteFor);
        playerService.savePlayer(player);
    }

    @Transactional
    public void doubleVotesForPlayer(String sessionId, String playerName, String playerNameDoubledVotes) {
        voteService.getAllVotesInSession(sessionId).forEach(vote -> {
            vote.setDoubledVotesFor(vote.getDoubledVotesFor() == null
                    ? ""
                    : vote.getDoubledVotesFor() + SEPARATOR + playerNameDoubledVotes);
            voteService.saveVote(vote);
        });
        Player player = playerService.getPlayer(sessionId, playerName);
        player.setCanVote(Boolean.FALSE);
        playerService.savePlayer(player);
    }

    @Transactional
    public void revote(String sessionId) {
        voteService.getAllVotesInSession(sessionId).forEach(vote -> {
            vote.setVotesNumber(0);
            voteService.saveVote(vote);
        });
    }
}
