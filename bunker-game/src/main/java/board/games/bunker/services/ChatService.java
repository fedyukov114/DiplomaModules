package board.games.bunker.services;

import board.games.bunker.MessageCreator;
import board.games.bunker.entity.Message;
import board.games.bunker.entity.repo.MessageRepository;
import board.games.bunker.entity.session.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ChatService {

    private final MessageRepository messageRepository;

    private final SessionCommonService sessionCommonService;

    public ChatService(MessageRepository messageRepository, SessionCommonService sessionCommonService) {
        this.messageRepository = messageRepository;
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
