package board.games.bunker.entity;

import static board.games.bunker.enums.PlayerStatus.PLAYING;
import static javax.persistence.EnumType.STRING;

import board.games.bunker.enums.PlayerRole;
import board.games.bunker.enums.PlayerStatus;

import java.util.UUID;

import javax.persistence.*;

@Entity

@Table(name = "player")
public class Player {

    @Id
    private String id = UUID.randomUUID().toString();

    @Column(name = "session_id")
    private String sessionId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "role", nullable = false)
    @Enumerated(STRING)
    private PlayerRole role;

    @Column(name = "status", nullable = false,unique = true)
    @Enumerated(STRING)
    private PlayerStatus status = PLAYING;

    @OneToOne
    @JoinColumn(name = "character")
    private Character character;

//    @Column(name = "position", nullable = false)
//    private int position;

    @Column(name = "can_vote", nullable = false)
    private boolean canVote = true;

    @Column(name = "double_vote", nullable = false)
    private boolean doubleVote = false;

    @Column(name = "cant_vote_for")
    private String cantVoteFor;

    @Column(name = "vote_for_yourself", nullable = false)
    private boolean voteForYourself = false;

    public String getCantVoteFor() {
        return cantVoteFor;
    }

    public void setCantVoteFor(String cantVoteFor) {
        this.cantVoteFor = cantVoteFor;
    }

    public boolean isVoteForYourself() {
        return voteForYourself;
    }

    public void setVoteForYourself(boolean voteForYourself) {
        this.voteForYourself = voteForYourself;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PlayerRole getRole() {
        return role;
    }

    public void setRole(PlayerRole role) {
        this.role = role;
    }

    public PlayerStatus getStatus() {
        return status;
    }

    public void setStatus(PlayerStatus status) {
        this.status = status;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public boolean isCanVote() {
        return canVote;
    }

    public void setCanVote(boolean canVote) {
        this.canVote = canVote;
    }

    public boolean isDoubleVote() {
        return doubleVote;
    }

    public void setDoubleVote(boolean doubleVote) {
        this.doubleVote = doubleVote;
    }

}
