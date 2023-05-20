package board.games.bunker.dto.response;

import board.games.bunker.entity.cards.BunkerCard;
import board.games.bunker.entity.cards.Catastrophe;

import java.util.ArrayList;
import java.util.List;

public class StartGameResultDTO {
    private String sessionState;
    private String currentPlayer;
    private List<BunkerCard> bunkerCards = new ArrayList<>();
    private Catastrophe catastrophe;

    public StartGameResultDTO(String sessionState, String currentPlayer) {
        this.sessionState = sessionState;
        this.currentPlayer = currentPlayer;
    }

    public String getSessionState() {
        return sessionState;
    }

    public void setSessionState(String sessionState) {
        this.sessionState = sessionState;
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(String currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public List<BunkerCard> getBunkerCards() {
        return bunkerCards;
    }

    public void setBunkerCards(List<BunkerCard> bunkerCards) {
        this.bunkerCards = bunkerCards;
    }

    public Catastrophe getCatastrophe() {
        return catastrophe;
    }

    public void setCatastrophe(Catastrophe catastrophe) {
        this.catastrophe = catastrophe;
    }
}
