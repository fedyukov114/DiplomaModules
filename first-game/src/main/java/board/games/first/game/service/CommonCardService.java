package board.games.first.game.service;

import board.games.first.game.entity.CommonCard;
import board.games.first.game.entity.repo.CommonCardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommonCardService{
    private final CommonCardRepository commonCardRepository;

    public CommonCardService(CommonCardRepository commonCardDAO) {
        this.commonCardRepository = commonCardDAO;
    }

    @Transactional(readOnly = true)
    public List<CommonCard> getAllCards() {
        return commonCardRepository.findAll();
    }
}
