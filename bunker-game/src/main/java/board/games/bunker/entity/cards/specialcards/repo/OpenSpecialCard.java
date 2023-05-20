package board.games.bunker.entity.cards.specialcards.repo;

import static javax.persistence.EnumType.STRING;

import board.games.bunker.entity.cards.specialcards.SpecialCard;
import board.games.bunker.enums.Interaction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;

/**
 * Класс описывающий специальный карты, приказывающий открыть определенную карту.
 */
@Entity
public class OpenSpecialCard extends SpecialCard {

    @Column(name = "interaction", nullable = false)
    @Enumerated(STRING)
    private Interaction interaction;

    public Interaction getInteraction() {
        return interaction;
    }
}
