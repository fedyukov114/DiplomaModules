package board.games.first.game.entity;

import javax.persistence.*;

import java.util.UUID;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "level_fine")
public class LevelFine {
    @Id
    private String id = UUID.randomUUID().toString();

    @Column(name = "value", nullable = false)
    private Long value;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }
}
