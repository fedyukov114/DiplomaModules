package board.games.bunker.entity.cards.specialcards;

import static javax.persistence.EnumType.STRING;

import board.games.bunker.enums.DropInteraction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;

/**
 * Класс описывающий специальные сбрасывающию карту.
 */
@Entity
public class DropSpecialCard extends SpecialCard {

    @Column(name = "interaction", nullable = false)
    @Enumerated(STRING)
    private DropInteraction interaction;

    public DropInteraction getInteraction() {
        return interaction;
    }
}
