package board.games.first.game.controller;

import board.games.first.game.dto.request.PlayerInfoDTO;
import board.games.first.game.dto.response.PlayerBalanceDTO;
import board.games.first.game.dto.response.PlayerPositionDTO;
import board.games.first.game.dto.response.ResultMessageDTO;
import board.games.first.game.entity.ChanceCard;
import board.games.first.game.entity.Player;
import board.games.first.game.mapper.PlayerMapper;
import board.games.first.game.service.ChanceCardService;
import board.games.first.game.service.ChatService;
import board.games.first.game.service.PlayerService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import static board.games.first.game.enums.ChanceCardType.CHANGE_BALANCE;
import static board.games.first.game.params.PlayingFieldParam.FIELD_MIN_SIZE;
import static board.games.first.game.params.PlayingFieldParam.FIELD_SIZE;

@Controller
public class ChanceCardController {
    private final ChanceCardService chanceCardService;
    private final PlayerService playerService;
    private final ChatService chatService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    public ChanceCardController(ChanceCardService chanceCardService, PlayerService playerService, ChatService chatService, SimpMessagingTemplate simpMessagingTemplate) {
        this.chanceCardService = chanceCardService;
        this.playerService = playerService;
        this.chatService = chatService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/api/monopoly/cards/chance")
    public void getChanceCard(PlayerInfoDTO dto) {
        ChanceCard card = chanceCardService.getRandomChanceCard();

        String sessionId = dto.getSessionId();
        String playerName = dto.getPlayerName();
        Player player = playerService.getPlayer(sessionId, playerName);

        if (card.getType() == CHANGE_BALANCE) {
            Long newBalance = player.getBalance() + card.getMoneyDifference();
            playerService.updatePlayerBalance(newBalance, sessionId, playerName);

            PlayerBalanceDTO result = PlayerMapper.playerBalanceToDTO(playerName, newBalance);
            simpMessagingTemplate.convertAndSend("/topic/change-balance/" + sessionId, result);
        } else {
            int potentialPos = player.getPosition() + card.getStep();
            int newPosition;
            if (potentialPos < FIELD_MIN_SIZE) {
                newPosition = FIELD_SIZE + potentialPos;
            } else if (potentialPos > FIELD_SIZE) {
                newPosition = potentialPos - FIELD_SIZE;
            } else {
                newPosition = potentialPos;
            }
            playerService.updatePlayerPosition(newPosition, sessionId, playerName);

            PlayerPositionDTO result = PlayerMapper.playerPositionToDTO(playerName, newPosition);
            simpMessagingTemplate.convertAndSend("/topic/change-position/" + sessionId, result);
        }

        String description = card.getDescription();
        ResultMessageDTO resultMessage = new ResultMessageDTO(playerName, description);
        chatService.addCommonMessageToChatHistory(sessionId, playerName, description);
        simpMessagingTemplate.convertAndSend("/topic/chat/" + sessionId, resultMessage);
    }
}
