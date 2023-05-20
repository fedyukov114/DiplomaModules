package board.games.bunker.entity.cards.specialcards;

import board.games.bunker.enums.VoteInteraction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;

import static javax.persistence.EnumType.STRING;

@Entity
public class VoteSpecialCard extends SpecialCard {
    @Column(name = "interaction", nullable = false)
    @Enumerated(STRING)
    private VoteInteraction interaction;

    public VoteInteraction getInteraction() {
        return interaction;
    }
}
