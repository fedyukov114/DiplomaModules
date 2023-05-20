package board.games.first.game.dto.response;

public class PlayerBalanceDTO {
    private String playerName;
    private Long balance;

    public PlayerBalanceDTO(String playerName, Long balance) {
        this.playerName = playerName;
        this.balance = balance;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }
}
