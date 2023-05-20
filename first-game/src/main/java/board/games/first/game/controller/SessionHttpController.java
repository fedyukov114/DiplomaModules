package board.games.first.game.controller;

import static board.games.first.game.params.ResultMessage.*;

import board.games.first.game.dto.request.PlayerInfoDTO;
import board.games.first.game.dto.response.PlayingFieldStateDTO;
import board.games.first.game.dto.response.PlayingFieldStaticDTO;
import board.games.first.game.dto.response.SessionDto;
import board.games.first.game.dto.response.SuccessMessageDTO;
import board.games.first.game.entity.CardState;
import board.games.first.game.entity.CompanyCard;
import board.games.first.game.service.CardStateService;
import board.games.first.game.service.CompanyCardService;
import board.games.first.game.service.SessionCommonService;
import board.games.first.game.service.SessionHttpService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SessionHttpController {

    private final SessionHttpService sessionHttpService;

    private final SessionCommonService sessionCommonService;

    private final CompanyCardService companyCardService;

    private final CardStateService cardStateService;

    public SessionHttpController(SessionHttpService sessionHttpService,
            SessionCommonService sessionCommonService,
            CompanyCardService companyCardService,
            CardStateService cardStateService) {
        this.sessionHttpService = sessionHttpService;
        this.sessionCommonService = sessionCommonService;
        this.companyCardService = companyCardService;
        this.cardStateService = cardStateService;
    }

    @PostMapping(value = "/monopoly/sessions/create")
    public ResponseEntity<SuccessMessageDTO> createSession(@RequestBody PlayerInfoDTO dto) {
        List<CompanyCard> companyCards = companyCardService.getCompanyCards();
        List<CardState> cardStates = cardStateService.getNewCardStates(companyCards);
        cardStateService.saveCardStates(cardStates);
        sessionHttpService.saveSession(dto.getSessionId(), dto.getPlayerName(), cardStates);

        return ResponseEntity.ok().body(new SuccessMessageDTO(SESSION_WAS_CREATED));
    }

    @GetMapping(value = "/monopoly/sessions/check/{sessionId}")
    public ResponseEntity<SuccessMessageDTO> isSessionValid(@PathVariable String sessionId) {
        sessionCommonService.checkSessionExists(sessionId);

        return ResponseEntity.ok().body(new SuccessMessageDTO(SESSION_IS_VALID));
    }

    @GetMapping(value = "/monopoly/sessions/state/{sessionId}")
    public ResponseEntity<PlayingFieldStateDTO> getPlayingFieldState(@PathVariable String sessionId) {
        PlayingFieldStateDTO playingFieldState = sessionHttpService.getStatePlayingField(sessionId);

        return ResponseEntity.ok().body(playingFieldState);
    }

    @GetMapping(value = "/monopoly/sessions/static")
    public ResponseEntity<PlayingFieldStaticDTO> getPlayingFieldStatic() {
        PlayingFieldStaticDTO playingFieldStatic = sessionHttpService.getStaticPlayingField();

        return ResponseEntity.ok().body(playingFieldStatic);
    }

    @DeleteMapping(value = "/monopoly/sessions/{id}")
    public ResponseEntity<SuccessMessageDTO> finishSession(@PathVariable String id) {
        sessionHttpService.finishSession(id);

        return ResponseEntity.ok().body(new SuccessMessageDTO(SESSION_WAS_DELETED));
    }

    @GetMapping(value = "/monopoly/sessions")
    public ResponseEntity<List<SessionDto>> getSessions() {
        return ResponseEntity.ok().body(sessionHttpService.getSessions().stream().map(session -> {
            SessionDto sessionDto = new SessionDto();
            List<String> playerNames = session.getPlayers().stream().map(player -> player.getName()).toList();
            sessionDto.setId(session.getId());
            sessionDto.setAmountOfPlayers(playerNames.size());
            sessionDto.setPlayerNames(playerNames);
            sessionDto.setState(session.getState().name());
            return sessionDto;
        }).toList());
    }

}
