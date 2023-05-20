package board.games.bunker.entity;

import board.games.bunker.entity.cards.charactercards.*;
import board.games.bunker.entity.cards.specialcards.SpecialCard;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.*;

import static board.games.bunker.entity.CharacterToSpecialCard.CHARACTER_2_SPECIAL_CARD_TABLE_NAME;
import static board.games.bunker.entity.CharacterToSpecialCard.ColumnName.C2SCT_CHARACTER_ID;
import static board.games.bunker.entity.CharacterToSpecialCard.ColumnName.C2SCT_SPECIAL_CARD_ID;
import static board.games.bunker.entity.session.SessionToBunkerCard.ColumnName.S2BCT_BUNKER_CARD_ID;
import static board.games.bunker.entity.session.SessionToBunkerCard.ColumnName.S2BCT_SESSION_ID;
import static board.games.bunker.entity.session.SessionToBunkerCard.SESSION_2_BUNKER_CARD_TABLE_NAME;

@Entity
@Table(name = "character")
public class Character {

    @Id
    private String id = UUID.randomUUID().toString();

    @OneToOne
    @JoinColumn(name = "profession")
    private Profession profession;

    @OneToOne
    @JoinColumn(name = "health")
    private Health health;

    @OneToOne
    @JoinColumn(name = "biology")
    private Biology biology;

    @OneToOne
    @JoinColumn(name = "extra_skills")
    private ExtraSkills extraSkills;

    @OneToOne
    @JoinColumn(name = "human_qualities")
    private HumanQualities humanQualities;

    @OneToOne
    @JoinColumn(name = "hobby")
    private Hobby hobby;

    @OneToOne
    @JoinColumn(name = "phobias")
    private Phobias phobias;

    @ManyToMany
    @JoinTable(name = CHARACTER_2_SPECIAL_CARD_TABLE_NAME, joinColumns = @JoinColumn(name = C2SCT_CHARACTER_ID),
            inverseJoinColumns = @JoinColumn(name = C2SCT_SPECIAL_CARD_ID))
    private List<SpecialCard> specialCard = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "luggage")
    private Luggage luggage;

    @Column(name = "opened_card_types")
    private String openedCardTypes;

    @Column(name = "card_to_open")
    private String cardToOpen;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public Biology getBiology() {
        return biology;
    }

    public void setBiology(Biology biology) {
        this.biology = biology;
    }

    public Hobby getHobby() {
        return hobby;
    }

    public void setHobby(Hobby hobby) {
        this.hobby = hobby;
    }

    public Health getHealth() {
        return health;
    }

    public void setHealth(Health health) {
        this.health = health;
    }

    public HumanQualities getHumanQualities() {
        return humanQualities;
    }

    public void setHumanQualities(HumanQualities luggage) {
        this.humanQualities = luggage;
    }

    public ExtraSkills getExtraSkills() {
        return extraSkills;
    }

    public void setExtraSkills(ExtraSkills fact) {
        this.extraSkills = fact;
    }

    public List<SpecialCard> getSpecialCard() {
        return specialCard;
    }

    public void setSpecialCard(List<SpecialCard> specialCard) {
        this.specialCard = specialCard;
    }

    public Phobias getPhobias() {
        return phobias;
    }

    public void setPhobias(Phobias phobias) {
        this.phobias = phobias;
    }

    public Luggage getLuggage() {
        return luggage;
    }

    public void setLuggage(Luggage luggage) {
        this.luggage = luggage;
    }

    public String getOpenedCardTypes() {
        return openedCardTypes;
    }

    public void setOpenedCardTypes(String openedCardTypes) {
        this.openedCardTypes = openedCardTypes;
    }

    public String getCardToOpen() {
        return cardToOpen;
    }

    public void setCardToOpen(String cardToOpen) {
        this.cardToOpen = cardToOpen;
    }

}