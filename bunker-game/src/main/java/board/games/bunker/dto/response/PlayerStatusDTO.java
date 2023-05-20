package board.games.bunker.dto.response;

public class PlayerStatusDTO {
    private String playerName;
    private String status;

    public PlayerStatusDTO(String playerName, String status) {
        this.playerName = playerName;
        this.status = status;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
