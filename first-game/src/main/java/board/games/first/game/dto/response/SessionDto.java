package board.games.first.game.dto.response;

import java.util.List;

public class SessionDto {
    private String id;

    private String state;

    private Integer amountOfPlayers;

    private List<String> playerNames;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getAmountOfPlayers() {
        return amountOfPlayers;
    }

    public void setAmountOfPlayers(Integer amountOfPlayers) {
        this.amountOfPlayers = amountOfPlayers;
    }

    public List<String> getPlayerNames() {
        return playerNames;
    }

    public void setPlayerNames(List<String> playerNames) {
        this.playerNames = playerNames;
    }
}
