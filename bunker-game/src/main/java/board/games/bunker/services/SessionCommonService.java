package board.games.bunker.services;

import board.games.bunker.entity.session.Session;
import board.games.bunker.entity.session.repo.SessionRepository;
import board.games.bunker.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service

public class SessionCommonService {
    private static final String SESSION_NOT_FOUND = "Session Not Found";
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
