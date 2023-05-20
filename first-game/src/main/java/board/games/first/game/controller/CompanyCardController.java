package board.games.first.game.controller;

import board.games.first.game.dto.response.CardDetailDTO;
import board.games.first.game.service.CompanyCardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompanyCardController {
    private final CompanyCardService companyCardService;

    public CompanyCardController(CompanyCardService companyCardService) {
        this.companyCardService = companyCardService;
    }

    @GetMapping(value = "/api/monopoly/cards", params = {"sessionId", "cardId"})
    public ResponseEntity<CardDetailDTO> getDetailedCard(
            @RequestParam("sessionId") String sessionId,
            @RequestParam("cardId") String cardId) {
        CardDetailDTO result = companyCardService.getDetailedCardInfo(sessionId, cardId);

        return ResponseEntity.ok().body(result);
    }
}
