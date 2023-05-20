package board.games.first.game.dto.response;

import java.util.Map;

public class OfferAcceptDTO {
    private PlayerBalanceDTO creator;
    private PlayerBalanceDTO receiver;
    private Map<String, CardStateDTO> cardStates;

    public OfferAcceptDTO(PlayerBalanceDTO creator, PlayerBalanceDTO receiver, Map<String, CardStateDTO> cardStates) {
        this.creator = creator;
        this.receiver = receiver;
        this.cardStates = cardStates;
    }

    public PlayerBalanceDTO getCreator() {
        return creator;
    }

    public void setCreator(PlayerBalanceDTO creator) {
        this.creator = creator;
    }

    public PlayerBalanceDTO getReceiver() {
        return receiver;
    }

    public void setReceiver(PlayerBalanceDTO receiver) {
        this.receiver = receiver;
    }

    public Map<String, CardStateDTO> getCardStates() {
        return cardStates;
    }

    public void setCardStates(Map<String, CardStateDTO> cardStates) {
        this.cardStates = cardStates;
    }
}
