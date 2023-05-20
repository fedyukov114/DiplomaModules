package board.games.bunker.entity.cards;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "bunker_card")
public class BunkerCard {
    @Id
    private String id = UUID.randomUUID().toString();

    @Column(name = "text", nullable = false)
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
