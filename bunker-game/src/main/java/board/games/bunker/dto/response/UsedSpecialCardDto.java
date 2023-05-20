package board.games.bunker.dto.response;

import board.games.bunker.enums.SpecialCardType;

public class UsedSpecialCardDto {

    /**
     * Имя игрока, использовавшего карту
     */
    private String playerName;

    /**
     * Имя игрока, против которого используют карту
     */
    private String secondPlayerName;

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getSecondPlayerName() {
        return secondPlayerName;
    }

    public void setSecondPlayerName(String secondPlayerName) {
        this.secondPlayerName = secondPlayerName;
    }

    public SpecialCardType getSpecialCardType() {
        return specialCardType;
    }

    public void setSpecialCardType(SpecialCardType specialCardType) {
        this.specialCardType = specialCardType;
    }

    private SpecialCardType specialCardType;
}
