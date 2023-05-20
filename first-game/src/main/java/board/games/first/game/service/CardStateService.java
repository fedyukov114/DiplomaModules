package board.games.first.game.service;

import board.games.first.game.entity.CardState;
import board.games.first.game.entity.CompanyCard;
import board.games.first.game.entity.repo.CardStateRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static board.games.first.game.params.InitialGameValue.*;

@Service
public class CardStateService {

    private final CardStateRepository cardStateRepository;

    public CardStateService(CardStateRepository cardStateRepository) {
        this.cardStateRepository = cardStateRepository;
    }

    @Transactional(readOnly = true)
    public List<CardState> getNewCardStates(List<CompanyCard> companyCards) {
        return companyCards
                .stream()
                .map(companyCard -> {
                    CardState cardState = new CardState();
                    cardState.setCard(companyCard);
                    cardState.setCurrentFine(INITIAL_CARD_FINE);
                    cardState.setLevel(INITIAL_CARD_LEVEL);
                    cardState.setOwnerName(INITIAL_CARD_OWNER_NAME);
                    return cardState;
                }).collect(Collectors.toList());
    }

    @Transactional
    public void saveCardStates(List<CardState> cardStates) {
        cardStateRepository.saveAll(cardStates);
    }

    @Transactional(readOnly = true)
    public List<CardState> findByCardIds(List<String> cardIds) {
        return cardStateRepository.findByCardIdIn(cardIds);
    }

}
