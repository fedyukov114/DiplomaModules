package board.games.bunker.dto.response;


import board.games.bunker.entity.Character;

public class KickedPlayerDTO {
    private PlayerStatusDTO player;
    private PlayerCardsDTO playerCards;


    public PlayerStatusDTO getPlayer() {
        return player;
    }

    public void setPlayer(PlayerStatusDTO player) {
        this.player = player;
    }

    public PlayerCardsDTO getPlayerCards() {
        return playerCards;
    }

    public void setPlayerCards(PlayerCardsDTO playerCards) {
        this.playerCards = playerCards;
    }
}
