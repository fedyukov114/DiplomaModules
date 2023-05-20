package board.games.bunker.dto.request;

public class VoteDto {

    private String votingPlayerName;

    private String votedForPlayerName;

    private String sessionId;

    public String getVotingPlayerName() {
        return votingPlayerName;
    }

    public void setVotingPlayerName(String votingPlayerName) {
        this.votingPlayerName = votingPlayerName;
    }

    public String getVotedForPlayerName() {
        return votedForPlayerName;
    }

    public void setVotedForPlayerName(String votedForPlayerName) {
        this.votedForPlayerName = votedForPlayerName;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
