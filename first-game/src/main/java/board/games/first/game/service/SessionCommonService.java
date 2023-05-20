package board.games.first.game.service;

import board.games.first.game.entity.session.Session;
import board.games.first.game.entity.repo.SessionRepository;
import board.games.first.game.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static board.games.first.game.params.ErrorMessage.SESSION_NOT_FOUND;

@Service

public class SessionCommonService {
    private final SessionRepository sessionRepository;

    public SessionCommonService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Transactional(readOnly = true)
    public Session getSession(String sessionId) {
        return sessionRepository.findById(sessionId)
                .orElseThrow(() -> new ResourceNotFoundException(SESSION_NOT_FOUND));
    }

    public void saveSession(Session session){
        sessionRepository.saveAndFlush(session);
    }

    @Transactional(readOnly = true)
    public void checkSessionExists(String sessionId) {
        if (!sessionRepository.existsSessionById(sessionId)) {
            throw new ResourceNotFoundException(SESSION_NOT_FOUND);
        }
    }
}
