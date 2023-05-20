package board.games.bunker.controllers;

import static board.games.bunker.params.ResultMessage.PLAYER_WAS_KICKED;

import board.games.bunker.dto.request.SessionIdDTO;
import board.games.bunker.dto.request.VoteDto;
import board.games.bunker.dto.response.KickedPlayerDTO;
import board.games.bunker.dto.response.ResultMessageDTO;
import board.games.bunker.dto.response.VotesInfoDto;
import board.games.bunker.services.VoteService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class VoteController {

    private final VoteService voteService;

    private final SimpMessagingTemplate simpMessagingTemplate;

    public VoteController(VoteService voteService, SimpMessagingTemplate simpMessagingTemplate) {
        this.voteService = voteService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/api/bunker/vote")
    public void vote(VoteDto voteDto) {
        String votingPlayerName = voteDto.getVotingPlayerName();
        String votedForPlayerName = voteDto.getVotedForPlayerName();
        String sessionId = voteDto.getSessionId();

        List<VotesInfoDto> votesInfoList = voteService.vote(votingPlayerName, votedForPlayerName, sessionId);
        simpMessagingTemplate.convertAndSend("/topic/votes/info/" + sessionId, votesInfoList);
    }

    @MessageMapping("/api/bunker/endVoting")
    public void endVoting(SessionIdDTO sessionIdDTO) {
        String sessionId = sessionIdDTO.getSessionId();
        KickedPlayerDTO kickedPlayer = voteService.endVoting(sessionId);
        ResultMessageDTO resultMessage =
                new ResultMessageDTO(kickedPlayer.getPlayer().getPlayerName(), PLAYER_WAS_KICKED);
        simpMessagingTemplate.convertAndSend("/topic/kicked/" + sessionId, kickedPlayer);
        simpMessagingTemplate.convertAndSend("/topic/chat/" + sessionId, resultMessage);
    }
}
