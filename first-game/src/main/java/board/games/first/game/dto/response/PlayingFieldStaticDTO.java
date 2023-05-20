package board.games.first.game.dto.response;

import board.games.first.game.entity.CommonCard;

import java.util.List;


public class PlayingFieldStaticDTO {
    private List<CommonCard> cards;

    public PlayingFieldStaticDTO(List<CommonCard> cards) {
        this.cards = cards;
    }

    public List<CommonCard> getCards() {
        return cards;
    }

    public void setCards(List<CommonCard> cards) {
        this.cards = cards;
    }
}
