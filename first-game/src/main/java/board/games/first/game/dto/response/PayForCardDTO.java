package board.games.first.game.dto.response;

public class PayForCardDTO {
    private PlayerBalanceDTO buyer;
    private PlayerBalanceDTO owner;

    public PlayerBalanceDTO getBuyer() {
        return buyer;
    }

    public void setBuyer(PlayerBalanceDTO buyer) {
        this.buyer = buyer;
    }

    public PlayerBalanceDTO getOwner() {
        return owner;
    }

    public void setOwner(PlayerBalanceDTO owner) {
        this.owner = owner;
    }
}
