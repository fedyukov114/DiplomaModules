package board.games.first.game.entity;

import board.games.first.game.enums.ChanceCardType;

import javax.persistence.*;

import java.util.UUID;

import static javax.persistence.EnumType.STRING;


@Entity
@Table(name = "chance_card")
public class ChanceCard {
    @Id
    private String id = UUID.randomUUID().toString();

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "money_difference")
    private Long moneyDifference;

    @Column(name = "step")
    private Integer step;

    @Column(name = "type", nullable = false)
    @Enumerated(STRING)
    private ChanceCardType type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getMoneyDifference() {
        return moneyDifference;
    }

    public void setMoneyDifference(Long moneyDifference) {
        this.moneyDifference = moneyDifference;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public ChanceCardType getType() {
        return type;
    }

    public void setType(ChanceCardType type) {
        this.type = type;
    }
}
