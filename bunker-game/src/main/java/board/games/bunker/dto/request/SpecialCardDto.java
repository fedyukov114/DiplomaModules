package board.games.bunker.dto.request;

public class SpecialCardDto {

    private String sessionId;

    /**
     * Имя игрока, использовавшего карту
     */
    private String playerName;

    /**
     * Имя игрока, против которого используют карту
     */
    private String secondPlayerName;

    private String specialCardId;

    /**
     * Ид карты, с которой производится действие
     */
    private String cardId;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

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

    public String getSpecialCardId() {
        return specialCardId;
    }

    public void setSpecialCardId(String specialCardId) {
        this.specialCardId = specialCardId;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }
}
