package board.games.bunker.entity.cards;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "catastrophe")
public class Catastrophe {
    @Id
    private String id = UUID.randomUUID().toString();

    @Column(name = "text", nullable = false,length = 1000)
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
