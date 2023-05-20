package board.games.first.game.mapper;

import board.games.first.game.dto.response.CardStateDTO;
import board.games.first.game.dto.response.CardStatePlayerBalanceDTO;
import board.games.first.game.entity.CardState;

import java.util.HashMap;
import java.util.Map;

public class CardActionMapper {

    public static CardStatePlayerBalanceDTO cardActionTODTO(String playerName, Long balance, CardState cardState) {
        Map<String, CardStateDTO> cardStateMap = new HashMap<>();
        cardStateMap.put(CardStateMapper.cardStateToCardId(cardState),
                CardStateMapper.cardStateEntityToDTO(cardState));

        return new CardStatePlayerBalanceDTO(
                PlayerMapper.playerBalanceToDTO(playerName, balance),
                cardStateMap
        );
    }
}
