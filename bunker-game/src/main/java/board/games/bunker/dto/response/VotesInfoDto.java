package board.games.bunker.dto.response;

public class VotesInfoDto {

    public VotesInfoDto(String playerName, Integer votesNumber) {
        this.playerName = playerName;
        this.votesNumber = votesNumber;
    }

    public VotesInfoDto() {
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Integer getVotesNumber() {
        return votesNumber;
    }

    public void setVotesNumber(Integer votesNumber) {
        this.votesNumber = votesNumber;
    }

    private String playerName;

    private Integer votesNumber;
}
