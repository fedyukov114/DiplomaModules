package board.games.first.game.dto.request;

public class OfferDTO {
    private String sessionId;
    private PlayerOfferDTO creator;
    private PlayerOfferDTO receiver;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public PlayerOfferDTO getCreator() {
        return creator;
    }

    public void setCreator(PlayerOfferDTO creator) {
        this.creator = creator;
    }

    public PlayerOfferDTO getReceiver() {
        return receiver;
    }

    public void setReceiver(PlayerOfferDTO receiver) {
        this.receiver = receiver;
    }
}
