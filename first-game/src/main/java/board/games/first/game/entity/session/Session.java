package board.games.first.game.entity.session;

import static board.games.first.game.entity.session.SessionToCardState.SESSION_TO_CARD_STATE_TABLE_NAME;
import static board.games.first.game.entity.session.SessionToCardState.ColumnName.S2CT_CARD_STATE_ID;
import static board.games.first.game.entity.session.SessionToCardState.ColumnName.S2CT_SESSION_ID;
import static board.games.first.game.entity.session.SessionToMessage.SESSION_TO_MESSAGE_TABLE_NAME;
import static board.games.first.game.entity.session.SessionToMessage.ColumnName.S2M_MESSAGE_ID;
import static board.games.first.game.entity.session.SessionToMessage.ColumnName.S2M_SESSION_ID;
import static board.games.first.game.entity.session.SessionToPlayers.SESSION_TO_PLAYERS_TABLE_NAME;
import static board.games.first.game.entity.session.SessionToPlayers.ColumnName.S2P_PLAYER_ID;
import static board.games.first.game.entity.session.SessionToPlayers.ColumnName.S2P_SESSION_ID;
import static board.games.first.game.enums.SessionState.NEW;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;

import board.games.first.game.entity.CardState;
import board.games.first.game.entity.Message;
import board.games.first.game.entity.Player;
import board.games.first.game.enums.MoveStatus;
import board.games.first.game.enums.SessionState;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.*;

@Entity
@Table(name = "session")
public class Session {

    @Id
    private String id = UUID.randomUUID().toString();

    @Column(name = "state", nullable = false)
    @Enumerated(STRING)
    private SessionState state = NEW;

    @Column(name = "current_player")
    private String currentPlayer;

    @Column(name = "move_status")
    @Enumerated(STRING)
    private MoveStatus moveStatus;

    @ManyToMany(fetch = LAZY, cascade = ALL)
    @JoinTable(name = SESSION_TO_PLAYERS_TABLE_NAME, joinColumns = @JoinColumn(name = S2P_SESSION_ID),
            inverseJoinColumns = @JoinColumn(name = S2P_PLAYER_ID))
    private List<Player> players = new ArrayList<>();

    @ManyToMany(fetch = LAZY, cascade = ALL)
    @JoinTable(name = SESSION_TO_CARD_STATE_TABLE_NAME, joinColumns = @JoinColumn(name = S2CT_SESSION_ID),
            inverseJoinColumns = @JoinColumn(name = S2CT_CARD_STATE_ID))
    private List<CardState> cardStates = new ArrayList<>();

    @ManyToMany(fetch = LAZY, cascade = ALL)
    @JoinTable(name = SESSION_TO_MESSAGE_TABLE_NAME, joinColumns = @JoinColumn(name = S2M_SESSION_ID),
            inverseJoinColumns = @JoinColumn(name = S2M_MESSAGE_ID))
    private List<Message> messages = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SessionState getState() {
        return state;
    }

    public void setState(SessionState state) {
        this.state = state;
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(String currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public MoveStatus getMoveStatus() {
        return moveStatus;
    }

    public void setMoveStatus(MoveStatus moveStatus) {
        this.moveStatus = moveStatus;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<CardState> getCardStates() {
        return cardStates;
    }

    public void setCardStates(List<CardState> cardStates) {
        this.cardStates = cardStates;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
