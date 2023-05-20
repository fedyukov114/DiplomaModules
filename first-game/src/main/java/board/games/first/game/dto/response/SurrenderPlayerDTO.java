package board.games.first.game.dto.response;

import java.util.Map;


public class SurrenderPlayerDTO {
    private PlayerStatusDTO player;
    private Map<String, CardStateDTO> cardStates;

    public PlayerStatusDTO getPlayer() {
        return player;
    }

    public void setPlayer(PlayerStatusDTO player) {
        this.player = player;
    }

    public Map<String, CardStateDTO> getCardStates() {
        return cardStates;
    }

    public void setCardStates(Map<String, CardStateDTO> cardStates) {
        this.cardStates = cardStates;
    }
}
