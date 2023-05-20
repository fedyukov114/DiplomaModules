package board.games.first.game.controller;

import board.games.first.game.dto.request.PerformActionWithCardDTO;
import board.games.first.game.dto.response.CardStateDTO;
import board.games.first.game.dto.response.CardStatePlayerBalanceDTO;
import board.games.first.game.dto.response.PayForCardDTO;
import board.games.first.game.dto.response.ResultMessageDTO;
import board.games.first.game.service.CardActionService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import static board.games.first.game.params.ResultMessage.*;

@Controller
public class CardActionController {
    private final CardActionService cardActionService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    public CardActionController(CardActionService cardActionService, SimpMessagingTemplate simpMessagingTemplate) {
        this.cardActionService = cardActionService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping(value = "/api/monopoly/buy-card")
    public void buyCard(PerformActionWithCardDTO dto) {
        CardStatePlayerBalanceDTO result = cardActionService.buyCard(dto.getSessionId(), dto.getPlayerName(), dto.getCardId());
        ResultMessageDTO resultMessage = new ResultMessageDTO(dto.getPlayerName(), BUY_CARD);

        simpMessagingTemplate.convertAndSend("/topic/card-action/" + dto.getSessionId(), result);
        simpMessagingTemplate.convertAndSend("/topic/chat/" + dto.getSessionId(), resultMessage);
    }

    @MessageMapping(value = "/api/monopoly/improve-card")
    public void improveCard(PerformActionWithCardDTO dto) {
        CardStatePlayerBalanceDTO result = cardActionService.improveCard(dto.getSessionId(), dto.getPlayerName(), dto.getCardId());
        ResultMessageDTO resultMessage = new ResultMessageDTO(dto.getPlayerName(), IMPROVE_CARD);

        simpMessagingTemplate.convertAndSend("/topic/card-action/" + dto.getSessionId(), result);
        simpMessagingTemplate.convertAndSend("/topic/chat/" + dto.getSessionId(), resultMessage);
    }

    @MessageMapping(value = "/api/monopoly/sell-card")
    public void sellCard(PerformActionWithCardDTO dto) {
        CardStatePlayerBalanceDTO result = cardActionService.sellCard(dto.getSessionId(), dto.getPlayerName(), dto.getCardId());
        CardStateDTO cardState = result.getCardState().get(dto.getCardId());
        ResultMessageDTO resultMessage;

        if (cardState.getOwnerName() == null) {
            resultMessage = new ResultMessageDTO(dto.getPlayerName(), SELL_CARD);
        } else {
            resultMessage = new ResultMessageDTO(dto.getPlayerName(), LOWER_CARD_LEVEL);
        }

        simpMessagingTemplate.convertAndSend("/topic/card-action/" + dto.getSessionId(), result);
        simpMessagingTemplate.convertAndSend("/topic/chat/" + dto.getSessionId(), resultMessage);
    }

    @MessageMapping(value = "/api/monopoly/pay-for-card")
    public void payForCard(PerformActionWithCardDTO dto) {
        PayForCardDTO result = cardActionService.payForCard(dto.getSessionId(), dto.getPlayerName(), dto.getCardId());
        ResultMessageDTO resultMessage = new ResultMessageDTO(dto.getPlayerName(), PAY_FOR_CARD);

        simpMessagingTemplate.convertAndSend("/topic/pay-for-card/" + dto.getSessionId(), result);
        simpMessagingTemplate.convertAndSend("/topic/chat/" + dto.getSessionId(), resultMessage);
    }
}
