package board.games.first.game.service;

import board.games.first.game.entity.ChanceCard;
import board.games.first.game.entity.repo.ChanceCardRepository;
import board.games.first.game.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

import static board.games.first.game.params.ErrorMessage.CARD_NOT_FOUND;

@Service

public class ChanceCardService {
    private final ChanceCardRepository chanceCardRepository;

    public ChanceCardService(ChanceCardRepository chanceCardDAO) {
        this.chanceCardRepository = chanceCardDAO;
    }

    @Transactional(readOnly = true)
    public ChanceCard getRandomChanceCard() {
        Long count = getChanceCardCount();
        String randomCardId =String.valueOf(new Random().nextLong(count) + 1);

        return chanceCardRepository.findById(randomCardId).
                orElseThrow(() -> new ResourceNotFoundException(CARD_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public Long getChanceCardCount() {
        return chanceCardRepository.count();
    }

}
