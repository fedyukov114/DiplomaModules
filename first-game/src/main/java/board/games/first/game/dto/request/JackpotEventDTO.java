package board.games.first.game.dto.request;

import java.util.List;

public class JackpotEventDTO {
    private String sessionId;
    private String playerName;
    private List<Integer> digits;

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

    public List<Integer> getDigits() {
        return digits;
    }

    public void setDigits(List<Integer> digits) {
        this.digits = digits;
    }
}
