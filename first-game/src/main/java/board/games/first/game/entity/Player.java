package board.games.first.game.entity;

import static board.games.first.game.params.InitialGameValue.INITIAL_BALANCE;
import static board.games.first.game.params.InitialGameValue.INITIAL_PLAYER_STATUS;
import static javax.persistence.EnumType.STRING;

import board.games.first.game.enums.PlayerColour;
import board.games.first.game.enums.PlayerRole;
import board.games.first.game.enums.PlayerStatus;

import java.util.UUID;

import javax.persistence.*;

@Entity

@Table(name = "player")
public class Player {

    @Id
    private String id = UUID.randomUUID().toString();

    @Column(name = "session_id")
    private String sessionId;

    @Column(name = "name", nullable = false,unique = true)
    private String name;

    @Column(name = "position", nullable = false)
    private Integer position;

    @Column(name = "role", nullable = false)
    @Enumerated(STRING)
    private PlayerRole role;

    @Column(name = "colour")
    @Enumerated(STRING)
    private PlayerColour colour;

    @Column(name = "balance", nullable = false)
    private Long balance = INITIAL_BALANCE;

    @Column(name = "status", nullable = false)
    @Enumerated(STRING)
    private PlayerStatus status = INITIAL_PLAYER_STATUS;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public PlayerRole getRole() {
        return role;
    }

    public void setRole(PlayerRole role) {
        this.role = role;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
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
}
