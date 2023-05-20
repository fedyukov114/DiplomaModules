package board.games.first.game.mapper;

import board.games.first.game.dto.response.CardDetailDTO;
import board.games.first.game.dto.response.CardStateDTO;
import board.games.first.game.entity.CardState;
import board.games.first.game.entity.CompanyCard;
import board.games.first.game.entity.LevelFine;

import java.util.List;
import java.util.stream.Collectors;

public class CardStateMapper {

    public static CardDetailDTO cardStateTOCardDetailDTO(CardState cardState) {
        CompanyCard card = cardState.getCard();

        return new CardDetailDTO(
                card.getTitle(),
                card.getRegion(),
                CardStateMapper.levelFinesToFines(card.getFines()),
                card.getPrice(),
                card.getSalePrice(),
                card.getStarPrice(),
                cardState.getOwnerName(),
                card.getCollectionNumber()
        );
    }

    public static CardStateDTO cardStateEntityToDTO(CardState cs) {
        return new CardStateDTO(
                cs.getCard().getPrice(),
                cs.getCurrentFine(),
                cs.getOwnerName(),
                cs.getLevel(),
                cs.getCard().getCollectionNumber());
    }

    public static String cardStateToCardId(CardState cardState) {
        return cardState.getCard().getId();
    }

    private static List<Long> levelFinesToFines(List<LevelFine> levelFines) {
        return levelFines
                .stream()
                .map(LevelFine::getValue)
                .collect(Collectors.toList());
    }
}
