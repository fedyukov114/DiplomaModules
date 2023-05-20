package board.games.first.game.service;

import board.games.first.game.entity.Message;
import board.games.first.game.entity.session.Session;
import board.games.first.game.entity.repo.MessageRepository;
import board.games.first.game.MessageCreator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ChatService {
    private final MessageRepository messageRepository;
    private final SessionCommonService sessionCommonService;

    public ChatService(MessageRepository messageDAO, SessionCommonService sessionCommonService) {
        this.messageRepository = messageDAO;
        this.sessionCommonService = sessionCommonService;
    }

    @Transactional
    public void addCommonMessageToChatHistory(String sessionId, String sender, String message) {
        Session session = sessionCommonService.getSession(sessionId);
        Message newMessage = MessageCreator.createSentMessage(message, sender);
        messageRepository.save(newMessage);
        session.getMessages().add(newMessage);
        sessionCommonService.saveSession(session);
    }
}
