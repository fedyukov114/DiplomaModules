package board.games.bunker.entity.session;

import static board.games.bunker.entity.session.SessionToBunkerCard.SESSION_2_BUNKER_CARD_TABLE_NAME;
import static board.games.bunker.entity.session.SessionToBunkerCard.ColumnName.S2BCT_BUNKER_CARD_ID;
import static board.games.bunker.entity.session.SessionToBunkerCard.ColumnName.S2BCT_SESSION_ID;
import static board.games.bunker.entity.session.SessionToMessage.SESSION_TO_MESSAGE_TABLE_NAME;
import static board.games.bunker.entity.session.SessionToMessage.ColumnName.S2M_MESSAGE_ID;
import static board.games.bunker.entity.session.SessionToMessage.ColumnName.S2M_SESSION_ID;
import static board.games.bunker.entity.session.SessionToPlayers.SESSION_TO_PLAYERS_TABLE_NAME;
import static board.games.bunker.entity.session.SessionToPlayers.ColumnName.S2P_PLAYER_ID;
import static board.games.bunker.entity.session.SessionToPlayers.ColumnName.S2P_SESSION_ID;
import static board.games.bunker.enums.SessionState.NEW;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;

import board.games.bunker.entity.Message;
import board.games.bunker.entity.Player;
import board.games.bunker.entity.Votes;
import board.games.bunker.entity.cards.BunkerCard;
import board.games.bunker.entity.cards.Catastrophe;
import board.games.bunker.enums.SessionState;

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

    @ManyToMany(fetch = LAZY, cascade = ALL)
    @JoinTable(name = SESSION_TO_PLAYERS_TABLE_NAME, joinColumns = @JoinColumn(name = S2P_SESSION_ID),
            inverseJoinColumns = @JoinColumn(name = S2P_PLAYER_ID))
    private List<Player> players = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "сatastrophe")
    private Catastrophe сatastrophe;

    @ManyToMany
    @JoinTable(name = SESSION_2_BUNKER_CARD_TABLE_NAME, joinColumns = @JoinColumn(name = S2BCT_SESSION_ID),
            inverseJoinColumns = @JoinColumn(name = S2BCT_BUNKER_CARD_ID))
    private List<BunkerCard> bunkerCards = new ArrayList<>();

    @OneToMany(mappedBy = Votes.PropertyNames.SESSION_ID, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Votes> votes = new ArrayList<>();

    @ManyToMany(fetch = LAZY, cascade = ALL)
    @JoinTable(name = SESSION_TO_MESSAGE_TABLE_NAME, joinColumns = @JoinColumn(name = S2M_SESSION_ID),
            inverseJoinColumns = @JoinColumn(name = S2M_MESSAGE_ID))
    private List<Message> messages = new ArrayList<>();

    public List<Votes> getVotes() {
        return votes;
    }

    public void setVotes(List<Votes> votes) {
        this.votes = votes;
    }

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

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Catastrophe getСatastrophe() {
        return сatastrophe;
    }

    public void setСatastrophe(Catastrophe сatastrophe) {
        this.сatastrophe = сatastrophe;
    }

    public List<BunkerCard> getBunkerCards() {
        return bunkerCards;
    }

    public void setBunkerCards(List<BunkerCard> bunkerCards) {
        this.bunkerCards = bunkerCards;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
