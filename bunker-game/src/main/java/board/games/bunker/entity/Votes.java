package board.games.bunker.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "votes")
public class Votes {

    private static class ColumnNames {

        public static final String PLAYER_NAME = "player_name";

        public static final String VOTES_NUMBER = "votes_number";

        public static final String SESSION_ID = "session_id";

        public static final String DOUBLED_VOTES_FOR = "doubled_votes_for";
    }

    public static class PropertyNames {

        public static final String PLAYER_NAME = "playerName";

        public static final String VOTES_NUMBER = "votesNumber";

        public static final String SESSION_ID = "sessionId";

        public static final String DOUBLED_VOTES_FOR = "doubledVotesFor";
    }

    @Id
    @Column(name = ColumnNames.PLAYER_NAME, nullable = false)
    private String playerName;

    @Column(name = ColumnNames.VOTES_NUMBER)
    private Integer votesNumber;

    @Column(name = ColumnNames.SESSION_ID)
    private String sessionId;

    @Column(name = ColumnNames.DOUBLED_VOTES_FOR)
    private String doubledVotesFor;

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

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getDoubledVotesFor() {
        return doubledVotesFor;
    }

    public void setDoubledVotesFor(String doubledVotesFor) {
        this.doubledVotesFor = doubledVotesFor;
    }
}
