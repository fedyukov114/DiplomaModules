package board.games.bunker.entity.cards.specialcards;

import static javax.persistence.EnumType.STRING;

import board.games.bunker.enums.Interaction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;

/**
 * Класс описывающий специальные карты перемешивающие свойтсва между игрокаим.
 */
@Entity
public class MixingSpecialCard extends SpecialCard {

    @Column(name = "interaction", nullable = false)
    @Enumerated(STRING)
    private Interaction interaction;

    public Interaction getInteraction() {
        return interaction;
    }
}
