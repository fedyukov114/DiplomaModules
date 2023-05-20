package board.games.bunker.controllers;

import board.games.bunker.dto.request.CardToOpenDto;
import board.games.bunker.dto.request.PlayerInfoDTO;
import board.games.bunker.dto.request.SpecialCardDto;
import board.games.bunker.dto.response.PlayerCardsDTO;
import board.games.bunker.dto.response.UsedSpecialCardDto;
import board.games.bunker.enums.SpecialCardType;
import board.games.bunker.services.SessionWebSocketService;
import board.games.bunker.services.specialcardservices.CardService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class CardController {

    private final CardService cardService;

    private final SimpMessagingTemplate simpMessagingTemplate;

    private final SessionWebSocketService sessionWebSocketService;

    public CardController(CardService cardService,
            SimpMessagingTemplate simpMessagingTemplate,
            SessionWebSocketService sessionWebSocketService) {
        this.cardService = cardService;
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.sessionWebSocketService = sessionWebSocketService;
    }

    @MessageMapping("/api/useSpecialCard")
    public void useSpecialCard(SpecialCardDto specialCardDto) {
        String sessionId = specialCardDto.getSessionId();
        String playerName = specialCardDto.getPlayerName();
        String secondPlayerName = specialCardDto.getSecondPlayerName();
        String specialCardId = specialCardDto.getSpecialCardId();
        String cardId = specialCardDto.getCardId();

        SpecialCardType specialCardType =
                cardService.useSpecialCard(sessionId, playerName, secondPlayerName, specialCardId, cardId);

        UsedSpecialCardDto result = new UsedSpecialCardDto();
        result.setPlayerName(playerName);
        result.setSecondPlayerName(secondPlayerName);
        result.setSpecialCardType(specialCardType);

        simpMessagingTemplate.convertAndSend("/topic/used-special-card/" + sessionId, result);
    }

    @MessageMapping("/api/get-player-cards")
    public void getPlayerCards(PlayerInfoDTO playerInfoDTO) {
        final String sessionId = playerInfoDTO.getSessionId();
        final String playerName = playerInfoDTO.getPlayerName();
        final PlayerCardsDTO playerCardsDTO = sessionWebSocketService.getPlayerCards(sessionId, playerName);

        simpMessagingTemplate.convertAndSend("/topic/player-cards/" + playerName + "/" + sessionId, playerCardsDTO);
    }

    @MessageMapping("/api/show-player-cards")
    public void showPlayerCard(CardToOpenDto cardToOpenDto) {
        simpMessagingTemplate.convertAndSend("/topic/open-card/" + cardToOpenDto.getSessionId(),
                cardService.openCard(cardToOpenDto.getSessionId(),
                        cardToOpenDto.getPlayerName(),
                        cardToOpenDto.getCardId()));
    }
}
