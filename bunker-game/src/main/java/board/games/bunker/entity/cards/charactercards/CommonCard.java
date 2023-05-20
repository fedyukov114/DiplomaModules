package board.games.bunker.entity.cards.charactercards;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "common_card")
@DiscriminatorColumn(name="dtype")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class CommonCard {
    @Id
    private String id = UUID.randomUUID().toString();

    @Column(name = "text", nullable = false)
    private String text;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
