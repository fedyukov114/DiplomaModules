package board.games.first.game.service;

import static board.games.first.game.enums.MoveStatus.END;
import static board.games.first.game.enums.MoveStatus.START;
import static board.games.first.game.params.ErrorMessage.CARD_NOT_FOUND;
import static board.games.first.game.params.InitialGameValue.INITIAL_CARD_FINE;
import static board.games.first.game.params.InitialGameValue.INITIAL_CARD_OWNER_NAME;

import board.games.first.game.MessageCreator;
import board.games.first.game.dto.response.CardStatePlayerBalanceDTO;
import board.games.first.game.dto.response.PayForCardDTO;
import board.games.first.game.entity.CardState;
import board.games.first.game.entity.CompanyCard;
import board.games.first.game.entity.Message;
import board.games.first.game.entity.Player;
import board.games.first.game.entity.repo.CardStateRepository;
import board.games.first.game.entity.repo.MessageRepository;
import board.games.first.game.entity.repo.SessionRepository;
import board.games.first.game.entity.session.Session;
import board.games.first.game.exception.ResourceNotFoundException;
import board.games.first.game.mapper.CardActionMapper;
import board.games.first.game.mapper.PlayerMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class CardActionService {

    private final SessionRepository sessionRepository;

    private final MessageRepository messageRepository;

    private final CardStateRepository cardStateRepository;

    private final SessionCommonService sessionCommonService;

    private final PlayerService playerService;

    public CardActionService(SessionRepository sessionRepository,
            MessageRepository messageRepository,
            CardStateRepository cardStateRepository,
            SessionCommonService sessionCommonService,
            PlayerService playerService) {
        this.sessionRepository = sessionRepository;
        this.messageRepository = messageRepository;
        this.cardStateRepository = cardStateRepository;
        this.sessionCommonService = sessionCommonService;
        this.playerService = playerService;
    }

    @Transactional
    public CardStatePlayerBalanceDTO buyCard(String sessionId, String playerName, String cardId) {
        Session session = sessionCommonService.getSession(sessionId);
        session.setMoveStatus(END);
        sessionRepository.saveAndFlush(session);
        Message message = MessageCreator.createBuyCardMessage(playerName);
        messageRepository.save(message);
        session.getMessages().add(message);
        sessionRepository.saveAndFlush(session);
        CardState cardState = findCardStateByCardId(session.getCardStates(), cardId);
        CompanyCard card = cardState.getCard();
        Player player = playerService.getPlayer(sessionId, playerName);

        Integer level = cardState.getLevel();
        Long fine = card.getFines().get(level).getValue();
        Long newBalance = player.getBalance() - card.getPrice();
        cardState.setCurrentFine(fine);
        cardState.setOwnerName(playerName);
        cardStateRepository.saveAndFlush(cardState);
        playerService.updatePlayerBalance(newBalance, sessionId, playerName);

        return CardActionMapper.cardActionTODTO(playerName, newBalance, cardState);
    }

    @Transactional
    public CardStatePlayerBalanceDTO improveCard(String sessionId, String playerName, String cardId) {
        Session session = sessionCommonService.getSession(sessionId);
        Message message = MessageCreator.createImproveCardMessage(playerName);
        messageRepository.save(message);
        session.getMessages().add(message);
        sessionRepository.saveAndFlush(session);
        CardState cardState = findCardStateByCardId(session.getCardStates(), cardId);
        CompanyCard card = cardState.getCard();
        Player player = playerService.getPlayer(sessionId, playerName);

        Integer level = cardState.getLevel() + 1;
        Long fine = card.getFines().get(level).getValue();
        Long newBalance = player.getBalance() - card.getStarPrice();
        cardState.setCurrentFine(fine);
        cardState.setLevel(level);
        cardStateRepository.saveAndFlush(cardState);
        playerService.updatePlayerBalance(newBalance, sessionId, playerName);

        return CardActionMapper.cardActionTODTO(playerName, newBalance, cardState);
    }

    @Transactional
    public CardStatePlayerBalanceDTO sellCard(String sessionId, String playerName, String cardId) {
        Session session = sessionCommonService.getSession(sessionId);
        Message message = null;

        CardState cardState = findCardStateByCardId(session.getCardStates(), cardId);
        CompanyCard card = cardState.getCard();
        Player player = playerService.getPlayer(sessionId, playerName);

        Long newBalance = player.getBalance();
        Integer level = cardState.getLevel();

        if (level > 0) {
            newBalance += card.getStarPrice();
            level -= 1;
            cardState.setCurrentFine(card.getFines().get(level).getValue());
            cardState.setLevel(level);
            message = MessageCreator.createSellCardMessage(playerName);
        } else {
            newBalance += card.getSalePrice();
            cardState.setOwnerName(INITIAL_CARD_OWNER_NAME);
            cardState.setCurrentFine(INITIAL_CARD_FINE);
            message = MessageCreator.createLowerCardLevelMessage(playerName);
        }

        messageRepository.save(message);
        session.getMessages().add(message);
        sessionRepository.saveAndFlush(session);
        cardStateRepository.saveAndFlush(cardState);
        playerService.updatePlayerBalance(newBalance, sessionId, playerName);

        return CardActionMapper.cardActionTODTO(playerName, newBalance, cardState);
    }

    @Transactional
    public PayForCardDTO payForCard(String sessionId, String buyerName, String cardId) {
        Session session = sessionCommonService.getSession(sessionId);
        session.setMoveStatus(START);
        sessionRepository.saveAndFlush(session);
        Message newMessage = MessageCreator.createPayForCardMessage(buyerName);
        messageRepository.save(newMessage);
        session.getMessages().add(newMessage);
        sessionRepository.saveAndFlush(session);
        CardState cardState = findCardStateByCardId(session.getCardStates(), cardId);
        String ownerName = cardState.getOwnerName();
        Long fine = cardState.getCurrentFine();

        Player buyer = playerService.getPlayer(sessionId, buyerName);
        Player owner = playerService.getPlayer(sessionId, ownerName);
        Long buyerBalance = buyer.getBalance() - fine;
        Long ownerBalance = owner.getBalance() + fine;

        playerService.updatePlayerBalance(buyerBalance, sessionId, buyerName);
        playerService.updatePlayerBalance(ownerBalance, sessionId, ownerName);

        return PlayerMapper.playerBalancesToDTO(buyerName, buyerBalance, ownerName, ownerBalance);
    }

    private CardState findCardStateByCardId(List<CardState> cardStates, String cardId) {
        return cardStates.stream()
                .filter(cs -> Objects.equals(cs.getCard().getId(), cardId))
                .findAny()
                .orElseThrow(() -> new ResourceNotFoundException(CARD_NOT_FOUND));
    }
}
