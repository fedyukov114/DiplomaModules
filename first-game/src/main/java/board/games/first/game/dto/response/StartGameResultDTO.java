package board.games.first.game.dto.response;

public class StartGameResultDTO {
    private String sessionState;
    private String currentPlayer;

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
}
