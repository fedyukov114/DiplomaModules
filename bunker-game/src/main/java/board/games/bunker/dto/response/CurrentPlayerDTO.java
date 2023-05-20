package board.games.bunker.dto.response;

public class CurrentPlayerDTO {
    private String currentPlayer;

    public CurrentPlayerDTO(String currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(String currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
}
