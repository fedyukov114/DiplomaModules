package board.games.first.game.entity;

import javax.persistence.*;
import java.util.UUID;

import static board.games.first.game.params.InitialGameValue.*;
import static javax.persistence.FetchType.EAGER;

@Entity
@Table(name = "card_state")
public class CardState {
    @Id
    private String id = UUID.randomUUID().toString();

    @Column(name = "current_fine")
    private Long currentFine = INITIAL_CARD_FINE;

    @Column(name = "level", nullable = false)
    private Integer level = INITIAL_CARD_LEVEL;

    @Column(name = "ownerName")
    private String ownerName = INITIAL_CARD_OWNER_NAME;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "card_id", nullable = false)
    private CompanyCard card;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getCurrentFine() {
        return currentFine;
    }

    public void setCurrentFine(Long currentFine) {
        this.currentFine = currentFine;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public CompanyCard getCard() {
        return card;
    }

    public void setCard(CompanyCard card) {
        this.card = card;
    }
}
