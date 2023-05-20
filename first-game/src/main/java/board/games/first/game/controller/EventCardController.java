package board.games.first.game.controller;

import board.games.first.game.dto.request.JackpotEventDTO;
import board.games.first.game.dto.request.PlayerInfoDTO;
import board.games.first.game.dto.response.PlayerBalanceDTO;
import board.games.first.game.dto.response.PlayerPositionDTO;
import board.games.first.game.dto.response.ResultMessageDTO;
import board.games.first.game.entity.Player;
import board.games.first.game.mapper.MessageContentMapper;
import board.games.first.game.service.ChatService;
import board.games.first.game.service.PlayerService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Random;

import static board.games.first.game.params.EventParam.*;
import static board.games.first.game.params.PlayingFieldParam.MAX_BORDER;
import static board.games.first.game.params.PlayingFieldParam.MIN_BORDER;

@Controller
public class EventCardController {
    private final PlayerService playerService;
    private final ChatService chatService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    public EventCardController(PlayerService playerService, ChatService chatService, SimpMessagingTemplate simpMessagingTemplate) {
        this.playerService = playerService;
        this.chatService = chatService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/api/monopoly/cards/rest")
    public void handleJackpotEvent(JackpotEventDTO dto) {
        String sessionId = dto.getSessionId();
        String playerName = dto.getPlayerName();
        List<Integer> digits = dto.getDigits();
        String message;

        Long balance = playerService.getPlayer(sessionId, playerName).getBalance();
        int digit = new Random().nextInt(MAX_BORDER + 1 - MIN_BORDER) + MIN_BORDER;
        
        if (digits.contains(digit)) {
            Long benefit = (JACKPOT_MAX_DIGITS_COUNT + 1 - digits.size())
                    * JACKPOT_BET * JACKPOT_MULTIPLIER;
            balance += benefit;
            message = MessageContentMapper
                    .jackpotEventWinToMessageContent(digits, digit, benefit);
        } else {
            balance -= JACKPOT_BET;
            message = MessageContentMapper
                    .jackpotEventLoseToMessageContent(digits, digit, JACKPOT_BET);
        }

        handleCommonMoneyEvent(sessionId, playerName, balance, message);
    }

    @MessageMapping("/api/monopoly/cards/taxIncome")
    public void handleTaxIncomeEvent(PlayerInfoDTO dto) {
        String sessionId = dto.getSessionId();
        String playerName = dto.getPlayerName();

        Player player = playerService.getPlayer(sessionId, playerName);
        Long newBalance = player.getBalance() - TAX_INCOME;
        String message = MessageContentMapper.taxIncomeEventToMessageContent(TAX_INCOME);

        handleCommonMoneyEvent(sessionId, playerName, newBalance, message);
    }

    @MessageMapping("/api/monopoly/cards/taxLuxury")
    public void handleTaxLuxuryEvent(PlayerInfoDTO dto) {
        String sessionId = dto.getSessionId();
        String playerName = dto.getPlayerName();

        Player player = playerService.getPlayer(sessionId, playerName);
        Long newBalance = player.getBalance() - TAX_LUXURY;
        String message = MessageContentMapper.taxLuxuryEventToMessageContent(TAX_LUXURY);

        handleCommonMoneyEvent(sessionId, playerName, newBalance, message);
    }

    @MessageMapping("/api/monopoly/cards/start")
    public void handleStartEvent(PlayerInfoDTO dto) {
        String sessionId = dto.getSessionId();
        String playerName = dto.getPlayerName();

        Player player = playerService.getPlayer(sessionId, playerName);
        Long newBalance = player.getBalance() + START_BONUS;
        String message = MessageContentMapper.startBonusEventToMessageContent(START_BONUS);

        handleCommonMoneyEvent(sessionId, playerName, newBalance, message);
    }

    @MessageMapping("/api/monopoly/cards/goToJail")
    public void handleTeleportEvent(PlayerInfoDTO dto) {
        String sessionId = dto.getSessionId();
        String playerName = dto.getPlayerName();

        Integer newPosition = TELEPORT_POSITION;
        String message = MessageContentMapper.teleportEventToMessageContent(newPosition);

        PlayerPositionDTO result = new PlayerPositionDTO(playerName, newPosition);
        ResultMessageDTO resultMessage = new ResultMessageDTO(playerName, message);

        playerService.updatePlayerPosition(newPosition, sessionId, playerName);
        chatService.addCommonMessageToChatHistory(sessionId, playerName, message);

        simpMessagingTemplate.convertAndSend("/topic/change-position/" + sessionId, result);
        simpMessagingTemplate.convertAndSend("/topic/chat/" + sessionId, resultMessage);
    }

    public void handleCommonMoneyEvent(String sessionId, String playerName, Long balance, String message) {
        PlayerBalanceDTO result = new PlayerBalanceDTO(playerName, balance);
        ResultMessageDTO resultMessage = new ResultMessageDTO(playerName, message);

        playerService.updatePlayerBalance(balance, sessionId, playerName);
        chatService.addCommonMessageToChatHistory(sessionId, playerName, message);

        simpMessagingTemplate.convertAndSend("/topic/change-balance/" + sessionId, result);
        simpMessagingTemplate.convertAndSend("/topic/chat/" + sessionId, resultMessage);
    }
}
