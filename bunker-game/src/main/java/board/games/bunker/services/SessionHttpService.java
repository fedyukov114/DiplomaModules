package board.games.bunker.services;

import board.games.bunker.dto.response.PlayingFieldStateDTO;
import board.games.bunker.entity.Player;
import board.games.bunker.entity.session.Session;
import board.games.bunker.entity.session.repo.SessionRepository;
import board.games.bunker.mapper.PlayingFieldMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static board.games.bunker.enums.PlayerRole.ADMIN;
import static board.games.bunker.params.InitialGameValue.INITIAL_CURRENT_PLAYER_NAME;

@Service
public class SessionHttpService {
    private final SessionRepository sessionRepository;
    private final SessionCommonService sessionCommonService;
    private final PlayerService playerService;

    public SessionHttpService(SessionRepository sessionRepository, SessionCommonService sessionCommonService, PlayerService playerService) {
        this.sessionRepository = sessionRepository;
        this.sessionCommonService = sessionCommonService;
        this.playerService = playerService;
    }

    @Transactional
    public void saveSession(String sessionId, String playerName) {
        playerService.savePlayer(sessionId, playerName, ADMIN);
        Player player = playerService.getPlayer(sessionId, playerName);

        Session session = new Session();
        session.setId(sessionId);
        session.setCurrentPlayer(INITIAL_CURRENT_PLAYER_NAME);
        session.getPlayers().add(player);
        sessionRepository.save(session);
    }

    @Transactional(readOnly = true)
    public PlayingFieldStateDTO getStatePlayingField(String sessionId) {
        Session session = sessionCommonService.getSession(sessionId);

        return PlayingFieldMapper.buildPlayingFieldState(session);
    }

    @Transactional
    public void finishSession(String sessionId) {
        sessionRepository.deleteById(sessionId);
    }

    @Transactional
    public List<Session> getSessions() {
        return sessionRepository.findAll();
    }


}
