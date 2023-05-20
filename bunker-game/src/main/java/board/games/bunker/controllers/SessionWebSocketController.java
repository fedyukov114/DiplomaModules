package board.games.bunker.controllers;

import static board.games.bunker.params.ResultMessage.GAME_WAS_STARTED;
import static board.games.bunker.params.ResultMessage.NEW_PLAYER;

import board.games.bunker.dto.request.PlayerInfoDTO;
import board.games.bunker.dto.response.*;
import board.games.bunker.entity.Player;
import board.games.bunker.mapper.PlayerMapper;
import board.games.bunker.services.SessionWebSocketService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class SessionWebSocketController {

    private final SessionWebSocketService sessionWebSocketService;

    private final SimpMessagingTemplate simpMessagingTemplate;

    public SessionWebSocketController(SessionWebSocketService sessionWebSocketService,
            SimpMessagingTemplate simpMessagingTemplate) {
        this.sessionWebSocketService = sessionWebSocketService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping(value = "/api/bunker/sessions/add-player")
    public void addPlayer(PlayerInfoDTO dto) {
        Player player = sessionWebSocketService.addPlayerToSession(dto.getSessionId(), dto.getPlayerName());
        PlayerDTO playerDTO = PlayerMapper.entityToPLayerDTO(player);
        ResultMessageDTO resultMessage = new ResultMessageDTO(dto.getPlayerName(), NEW_PLAYER);

        simpMessagingTemplate.convertAndSend("/topic/add-player/" + dto.getSessionId(), playerDTO);
        simpMessagingTemplate.convertAndSend("/topic/chat/" + dto.getSessionId(), resultMessage);
    }

    @MessageMapping(value = "/api/bunker/sessions/start-game")
    public void startGame(PlayerInfoDTO dto) {

        StartGameResultDTO result = sessionWebSocketService.startGame(dto.getSessionId(), dto.getPlayerName());
        ResultMessageDTO resultMessage = new ResultMessageDTO(null, GAME_WAS_STARTED);

        simpMessagingTemplate.convertAndSend("/topic/start-game/" + dto.getSessionId(), result);
        simpMessagingTemplate.convertAndSend("/topic/chat/" + dto.getSessionId(), resultMessage);
    }

    @MessageMapping(value = "/api/bunker/sessions/move-transition")
    public String moveTransition(PlayerInfoDTO dto) {
        String currentPlayer = sessionWebSocketService.getNextPlayer(dto.getSessionId(), dto.getPlayerName());
        CurrentPlayerDTO result = new CurrentPlayerDTO(currentPlayer);

        simpMessagingTemplate.convertAndSend("/topic/move-transition/" + dto.getSessionId(), result);

        return currentPlayer;
    }

    @MessageMapping(value = "/api/bunker/sessions/kick-out-player")
    public void kickOutPlayer(PlayerInfoDTO dto) {
        KickedPlayerDTO result = sessionWebSocketService.kickOutPlayer(dto.getSessionId(), dto.getPlayerName());

        simpMessagingTemplate.convertAndSend("/topic/kick-out-player/" + dto.getSessionId(), result);
    }

}
