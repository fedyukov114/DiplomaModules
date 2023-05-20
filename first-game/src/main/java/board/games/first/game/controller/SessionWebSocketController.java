package board.games.first.game.controller;

import board.games.first.game.dto.request.*;
import board.games.first.game.dto.response.*;
import board.games.first.game.entity.Player;
import board.games.first.game.enums.MoveStatus;
import board.games.first.game.mapper.MessageContentMapper;
import board.games.first.game.mapper.PlayerMapper;
import board.games.first.game.mapper.RollDicesMapper;
import board.games.first.game.service.ChatService;
import board.games.first.game.service.SessionWebSocketService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static board.games.first.game.enums.SessionState.IN_PROGRESS;
import static board.games.first.game.params.ResultMessage.*;

@Controller
public class SessionWebSocketController {
    private final SessionWebSocketService sessionWebSocketService;
    private final ChatService chatService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    public SessionWebSocketController(SessionWebSocketService sessionWebSocketService, ChatService chatService, SimpMessagingTemplate simpMessagingTemplate) {
        this.sessionWebSocketService = sessionWebSocketService;
        this.chatService = chatService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping(value = "/api/monopoly/sessions/add-player")
    public void addPlayer(PlayerInfoDTO dto) {
        System.out.println("Отработало");
        Player player = sessionWebSocketService.addPlayerToSession(dto.getSessionId(), dto.getPlayerName());
        PlayerDTO playerDTO = PlayerMapper.entityToPLayerDTO(player);
        ResultMessageDTO resultMessage = new ResultMessageDTO(dto.getPlayerName(), NEW_PLAYER);

        simpMessagingTemplate.convertAndSend("/topic/add-player/" + dto.getSessionId(), playerDTO);
        simpMessagingTemplate.convertAndSend("/topic/chat/" + dto.getSessionId(), resultMessage);
    }

    @MessageMapping(value = "/api/monopoly/sessions/start-game")
    public void startGame(PlayerInfoDTO dto) {
        sessionWebSocketService.startGame(dto.getSessionId(), dto.getPlayerName());

        StartGameResultDTO result = new StartGameResultDTO(IN_PROGRESS.toString(), dto.getPlayerName());
        ResultMessageDTO resultMessage = new ResultMessageDTO(null, GAME_WAS_STARTED);

        simpMessagingTemplate.convertAndSend("/topic/start-game/" + dto.getSessionId(), result);
        simpMessagingTemplate.convertAndSend("/topic/chat/" + dto.getSessionId(), resultMessage);
    }

    @MessageMapping(value = "/api/monopoly/sessions/move-transition")
    public String moveTransition(PlayerInfoDTO dto) {
        String currentPlayer = sessionWebSocketService.getNextPlayer(dto.getSessionId(), dto.getPlayerName());
        CurrentPlayerDTO result = new CurrentPlayerDTO(currentPlayer);

        simpMessagingTemplate.convertAndSend("/topic/move-transition/" + dto.getSessionId(), result);

        return currentPlayer;
    }

    @MessageMapping(value = "/api/monopoly/sessions/move-status")
    public void getCurrentMoveStatus(SessionIdDTO dto) {
        MoveStatus status = sessionWebSocketService.getCurrentMoveStatus(dto.getSessionId());
        MoveStatusDTO result = new MoveStatusDTO(String.valueOf(status));

        simpMessagingTemplate.convertAndSend("/topic/move-status/" + dto.getSessionId(), result);
    }

    @MessageMapping(value = "/api/monopoly/sessions/roll-dice")
    public void getNewPlayerPosition(PlayerMoveDTO dto) {
        RollDiceResultDTO rollDiceResult = sessionWebSocketService.rollDices(dto.getSessionId(), dto.getPlayerName());
        Long balance = rollDiceResult.getPlayerBalance().getBalance();

        if (balance != null) {
            ResultMessageDTO startMessage = new ResultMessageDTO(dto.getPlayerName(), START_BONUS_PASS);
            simpMessagingTemplate.convertAndSend(
                    "/topic/change-balance/" + dto.getSessionId(),
                    rollDiceResult.getPlayerBalance());
            simpMessagingTemplate.convertAndSend("/topic/chat/" + dto.getSessionId(), startMessage);
        }

        ResultMessageDTO resultMessage = new ResultMessageDTO(
                dto.getPlayerName(),
                MessageContentMapper.rollDiceToMessageContent(rollDiceResult.getDigits())
        );
        MoveResultDTO result = RollDicesMapper.rollResultToMoveDTO(rollDiceResult);

        simpMessagingTemplate.convertAndSend("/topic/roll-dice/" + dto.getSessionId(), result);
        simpMessagingTemplate.convertAndSend("/topic/chat/" + dto.getSessionId(), resultMessage);
    }

    @MessageMapping(value = "/api/monopoly/sessions/surrender")
    public void surrenderTheGame(PlayerInfoDTO dto) {
        SurrenderPlayerDTO result = sessionWebSocketService.getSurrenderPlayer(dto.getSessionId(), dto.getPlayerName());
        ResultMessageDTO resultMessage = new ResultMessageDTO(dto.getPlayerName(), SURRENDER);

        simpMessagingTemplate.convertAndSend("/topic/surrender/" + dto.getSessionId(), result);
        simpMessagingTemplate.convertAndSend("/topic/chat/" + dto.getSessionId(), resultMessage);
    }

    @MessageMapping(value = "/api/monopoly/sessions/send-offer")
    public void sendContract(OfferDTO offer) {
        String sessionId = offer.getSessionId();
        String creatorName = offer.getCreator().getPlayerName();
        String receiverName = offer.getReceiver().getPlayerName();
        String message = MessageContentMapper.sendOfferToMessageContent(receiverName);
        ResultMessageDTO resultMessage = new ResultMessageDTO(creatorName, message);

        chatService.addCommonMessageToChatHistory(sessionId, null, message);

        simpMessagingTemplate.convertAndSend("/topic/send-contract/" + sessionId + "/" + receiverName, offer);
        simpMessagingTemplate.convertAndSend("/topic/chat/" + sessionId, resultMessage);
    }

    @MessageMapping(value = "/api/monopoly/sessions/reject-offer")
    public void rejectOffer(PlayerInfoDTO dto) {
        String sessionId = dto.getSessionId();
        String playerName = dto.getPlayerName();
        ResultMessageDTO resultMessage = new ResultMessageDTO(playerName, REJECT_OFFER);

        chatService.addCommonMessageToChatHistory(sessionId, playerName, REJECT_OFFER);

        simpMessagingTemplate.convertAndSend("/topic/chat/" + sessionId, resultMessage);
    }

    @MessageMapping(value = "/api/monopoly/sessions/accept-offer")
    public void acceptOffer(OfferDTO offer) {
        String sessionId = offer.getSessionId();
        PlayerOfferDTO creator = offer.getCreator();
        PlayerOfferDTO receiver = offer.getReceiver();

        CardStatePlayerBalanceDTO creatorDTO = sessionWebSocketService
                .acceptOffer(
                        sessionId,
                        creator.getPlayerName(),
                        receiver.getPlayerName(),
                        receiver.getMoney(),
                        creator.getCards(),
                        creator.getMoney()
                );
        CardStatePlayerBalanceDTO receiverDTO = sessionWebSocketService
                .acceptOffer(
                        sessionId,
                        receiver.getPlayerName(),
                        creator.getPlayerName(),
                        creator.getMoney(),
                        receiver.getCards(),
                        receiver.getMoney()
                );
        chatService.addCommonMessageToChatHistory(sessionId, receiver.getPlayerName(), ACCEPT_OFFER);
        Map<String, CardStateDTO> cardStates = Stream.of(creatorDTO.getCardState(), receiverDTO.getCardState())
                .flatMap(map -> map.entrySet().stream())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue));
        OfferAcceptDTO result = new OfferAcceptDTO(creatorDTO.getPlayer(), receiverDTO.getPlayer(), cardStates);
        ResultMessageDTO resultMessage = new ResultMessageDTO(receiver.getPlayerName(), ACCEPT_OFFER);

        simpMessagingTemplate.convertAndSend("/topic/accept-offer/" + sessionId, result);
        simpMessagingTemplate.convertAndSend("/topic/chat/" + sessionId, resultMessage);
    }
}
