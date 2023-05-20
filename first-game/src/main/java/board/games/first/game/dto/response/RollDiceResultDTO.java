package board.games.first.game.dto.response;

import java.util.List;


public class RollDiceResultDTO {
    private PlayerBalanceDTO playerBalance;
    private PlayerPositionDTO player;
    private List<Integer> digits;

    public PlayerBalanceDTO getPlayerBalance() {
        return playerBalance;
    }

    public void setPlayerBalance(PlayerBalanceDTO playerBalance) {
        this.playerBalance = playerBalance;
    }

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
