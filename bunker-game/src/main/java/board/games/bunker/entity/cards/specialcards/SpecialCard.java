package board.games.bunker.entity.cards.specialcards;

import java.util.UUID;

import javax.persistence.*;

@Entity
@Table(name = "special_card")
@DiscriminatorColumn(name="dtype")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class SpecialCard {

    @Id
    private String id = UUID.randomUUID().toString();

    @Column(name = "text", nullable = false, length = 1000)
    private String text;

    @Column(name = "name", nullable = false)
    private String name;

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getName() {
        return name;
    }
}
