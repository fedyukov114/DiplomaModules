package board.games.bunker.dto.response;

import board.games.bunker.entity.cards.charactercards.*;
import board.games.bunker.entity.cards.specialcards.SpecialCard;

import java.util.ArrayList;
import java.util.List;

public class PlayerCardsDTO {

    private Profession profession;

    private Health health;

    private Biology biology;

    private ExtraSkills extraSkills;

    private HumanQualities humanQualities;

    private Hobby hobby;

    private Phobias phobias;

    private List<SpecialCard> specialCard = new ArrayList<>();

    private Luggage luggage;

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public Health getHealth() {
        return health;
    }

    public void setHealth(Health health) {
        this.health = health;
    }

    public Biology getBiology() {
        return biology;
    }

    public void setBiology(Biology biology) {
        this.biology = biology;
    }

    public ExtraSkills getExtraSkills() {
        return extraSkills;
    }

    public void setExtraSkills(ExtraSkills extraSkills) {
        this.extraSkills = extraSkills;
    }

    public HumanQualities getHumanQualities() {
        return humanQualities;
    }

    public void setHumanQualities(HumanQualities humanQualities) {
        this.humanQualities = humanQualities;
    }

    public Hobby getHobby() {
        return hobby;
    }

    public void setHobby(Hobby hobby) {
        this.hobby = hobby;
    }

    public Phobias getPhobias() {
        return phobias;
    }

    public void setPhobias(Phobias phobias) {
        this.phobias = phobias;
    }

    public List<SpecialCard> getSpecialCard() {
        return specialCard;
    }

    public void setSpecialCard(List<SpecialCard> specialCard) {
        this.specialCard = specialCard;
    }

    public Luggage getLuggage() {
        return luggage;
    }

    public void setLuggage(Luggage luggage) {
        this.luggage = luggage;
    }
}
