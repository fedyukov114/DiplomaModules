package board.games.bunker.controllers;


import board.games.bunker.dto.response.PlayingFieldStateDTO;
import board.games.bunker.dto.request.PlayerInfoDTO;
import board.games.bunker.dto.response.SuccessMessageDTO;
import board.games.bunker.entity.session.Session;
import board.games.bunker.services.SessionCommonService;
import board.games.bunker.services.SessionHttpService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static board.games.bunker.params.ResultMessage.*;


@RestController
@RequestMapping("/api")
public class SessionHttpController {
    private final SessionCommonService sessionCommonService;

    private final SessionHttpService sessionHttpService;

    public SessionHttpController(SessionCommonService sessionCommonService, SessionHttpService sessionHttpService) {
        this.sessionCommonService = sessionCommonService;
        this.sessionHttpService = sessionHttpService;
    }

    @PostMapping(value = "/bunker/sessions/create")
    public ResponseEntity<SuccessMessageDTO> createSession(@RequestBody PlayerInfoDTO dto) {
        sessionHttpService.saveSession(dto.getSessionId(), dto.getPlayerName());

        return ResponseEntity.ok().body(new SuccessMessageDTO(SESSION_WAS_CREATED));
    }

    @GetMapping(value = "/bunker/sessions/check/{sessionId}")
    public ResponseEntity<SuccessMessageDTO> isSessionValid(@PathVariable String sessionId) {
        sessionCommonService.checkSessionExists(sessionId);

        return ResponseEntity.ok().body(new SuccessMessageDTO(SESSION_IS_VALID));
    }

    @GetMapping(value = "/bunker/sessions/state/{sessionId}")
    public ResponseEntity<PlayingFieldStateDTO> getPlayingFieldState(@PathVariable String sessionId) {
        PlayingFieldStateDTO playingFieldState = sessionHttpService.getStatePlayingField(sessionId);

        return ResponseEntity.ok().body(playingFieldState);
    }

    @DeleteMapping(value = "/bunker/sessions/{id}")
    public ResponseEntity<SuccessMessageDTO> finishSession(@PathVariable String id) {
        sessionHttpService.finishSession(id);

        return ResponseEntity.ok().body(new SuccessMessageDTO(SESSION_WAS_DELETED));
    }

    @GetMapping(value = "/bunker/sessions")
    public ResponseEntity<List<Session>> getSessions() {
        return ResponseEntity.ok().body(sessionHttpService.getSessions());
    }

}
