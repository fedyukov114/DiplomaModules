package board.games.first.game.dto.response;

import java.util.List;


public class MoveResultDTO {
    private PlayerPositionDTO player;

    private List<Integer> digits;

    public PlayerPositionDTO getPlayer() {
        return player;
    }

    public void setPlayer(PlayerPositionDTO player) {
        this.player = player;
    }

    public List<Integer> getDigits() {
        return digits;
    }

    public void setDigits(List<Integer> digits) {
        this.digits = digits;
    }

}
