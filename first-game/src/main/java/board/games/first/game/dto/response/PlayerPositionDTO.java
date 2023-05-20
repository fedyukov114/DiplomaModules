package board.games.first.game.dto.response;


public class PlayerPositionDTO {
    private String playerName;
    private Integer position;

    public PlayerPositionDTO(String playerName, Integer position) {
        this.playerName = playerName;
        this.position = position;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}
