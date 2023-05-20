package board.games.first.game.service;

import board.games.first.game.dto.response.CardDetailDTO;
import board.games.first.game.entity.CardState;
import board.games.first.game.entity.CompanyCard;
import board.games.first.game.entity.session.Session;
import board.games.first.game.entity.repo.CompanyCardRepository;
import board.games.first.game.exception.ResourceNotFoundException;
import board.games.first.game.mapper.CardStateMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static board.games.first.game.params.ErrorMessage.CARD_NOT_FOUND;

@Service
public class CompanyCardService{
    private final SessionCommonService sessionCommonService;
    private final CompanyCardRepository companyCardRepository;

    public CompanyCardService(SessionCommonService sessionCommonService, CompanyCardRepository companyCardRepository) {
        this.sessionCommonService = sessionCommonService;
        this.companyCardRepository = companyCardRepository;
    }

    @Transactional(readOnly = true)
    public List<CompanyCard> getCompanyCards() {
        return companyCardRepository.findAll();
    }

    @Transactional(readOnly = true)
    public CardDetailDTO getDetailedCardInfo(String sessionId, String cardId) {
        Session session = sessionCommonService.getSession(sessionId);
        CardState cardState = session.getCardStates()
                .stream()
                .filter(cs -> Objects.equals(cs.getCard().getId(), cardId))
                .findAny()
                .orElseThrow(() -> new ResourceNotFoundException(CARD_NOT_FOUND));

        return CardStateMapper.cardStateTOCardDetailDTO(cardState);
    }
}
