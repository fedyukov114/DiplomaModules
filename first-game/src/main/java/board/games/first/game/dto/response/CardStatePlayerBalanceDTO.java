package board.games.first.game.dto.response;

import java.util.Map;

public class CardStatePlayerBalanceDTO {
    private PlayerBalanceDTO player;
    private Map<String, CardStateDTO> cardState;

    public CardStatePlayerBalanceDTO(PlayerBalanceDTO player, Map<String, CardStateDTO> cardState) {
        this.player = player;
        this.cardState = cardState;
    }

    public PlayerBalanceDTO getPlayer() {
        return player;
    }

    public void setPlayer(PlayerBalanceDTO player) {
        this.player = player;
    }

    public Map<String, CardStateDTO> getCardState() {
        return cardState;
    }

    public void setCardState(Map<String, CardStateDTO> cardState) {
        this.cardState = cardState;
    }
}
