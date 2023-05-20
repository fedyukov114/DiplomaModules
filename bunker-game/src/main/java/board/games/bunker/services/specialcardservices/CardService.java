package board.games.bunker.services.specialcardservices;

import static board.games.bunker.params.ErrorMessage.CARD_NOT_FOUND;

import board.games.bunker.entity.Character;
import board.games.bunker.entity.Player;
import board.games.bunker.entity.cards.charactercards.*;
import board.games.bunker.entity.cards.charactercards.repo.CommonCardRepository;
import board.games.bunker.entity.cards.specialcards.*;
import board.games.bunker.entity.cards.specialcards.repo.OpenSpecialCard;
import board.games.bunker.entity.cards.specialcards.repo.SpecialCardRepository;
import board.games.bunker.enums.SpecialCardType;
import board.games.bunker.exception.ResourceNotFoundException;
import board.games.bunker.services.PlayerService;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    private static final String SPECIAL_CARD_NOT_FOUND = "Special card not found";

    private final ChangeSpecialCardService changeSpecialCardService;

    private final MixingSpecialCardService mixingSpecialCardService;

    private final ChangeWithPlayerSpecialCardService changeHobbyCardBetweenTwoPlayers;

    private final SpecialCardRepository specialCardRepository;

    private final VoteSpecialCardService voteSpecialCardService;

    private final DropSpecialCardService dropSpecialCardService;

    private final OpenSpecialCardService openSpecialCardService;

    private final CommonCardRepository commonCardRepository;

    private final PlayerService playerService;

    public CardService(ChangeSpecialCardService changeSpecialCardService,
            MixingSpecialCardService mixingSpecialCardService,
            ChangeWithPlayerSpecialCardService changeHobbyCardBetweenTwoPlayers,
            SpecialCardRepository specialCardRepository,
            VoteSpecialCardService voteSpecialCardService,
            DropSpecialCardService dropSpecialCardService,
            OpenSpecialCardService openSpecialCardService,
            CommonCardRepository commonCardRepository,
            PlayerService playerService) {
        this.changeHobbyCardBetweenTwoPlayers = changeHobbyCardBetweenTwoPlayers;
        this.changeSpecialCardService = changeSpecialCardService;
        this.mixingSpecialCardService = mixingSpecialCardService;
        this.specialCardRepository = specialCardRepository;
        this.voteSpecialCardService = voteSpecialCardService;
        this.dropSpecialCardService = dropSpecialCardService;
        this.openSpecialCardService = openSpecialCardService;
        this.commonCardRepository = commonCardRepository;
        this.playerService = playerService;
    }

    public SpecialCardType useSpecialCard(String sessionId,
            String playerName,
            String secondPlayerName,
            String specialCardId,
            String cardId) {
        SpecialCard specialCard = specialCardRepository.findById(specialCardId)
                .orElseThrow(() -> new ResourceNotFoundException(SPECIAL_CARD_NOT_FOUND));
        SpecialCardType specialCardType = null;
        if (specialCard instanceof MixingSpecialCard mixingSpecialCard) {
            switch (mixingSpecialCard.getInteraction()) {
                case HOBBY -> mixingSpecialCardService.changeHobbyCardBetweenPlayers(sessionId);
                case HEALTH -> mixingSpecialCardService.changeHealthCardBetweenPlayers(sessionId);
                case BIOLOGY -> mixingSpecialCardService.changeBiologyCardBetweenPlayers(sessionId);
                case LUGGAGE -> mixingSpecialCardService.changeLuggageCardBetweenPlayers(sessionId);
                case PHOBIAS -> mixingSpecialCardService.changePhobiasCardBetweenPlayers(sessionId);
                case PROFESSION -> mixingSpecialCardService.changeProfessionCardBetweenPlayers(sessionId);
                case EXTRA_SKILLS -> mixingSpecialCardService.changeExtraSkillsCardBetweenPlayers(sessionId);
                case HUMAN_QUALITIES -> mixingSpecialCardService.changeHumanQualitiesCardBetweenPlayers(sessionId);
                default -> throw new RuntimeException("No action found");
            }
            specialCardType = SpecialCardType.MIX;
        } else if (specialCard instanceof ChangeWithPlayerSpecialCard changeWithPlayerSpecialCard) {
            switch (changeWithPlayerSpecialCard.getInteraction()) {
                case HOBBY -> changeHobbyCardBetweenTwoPlayers
                        .changeHobbyCardBetweenTwoPlayers(sessionId, playerName, secondPlayerName);
                case HEALTH -> changeHobbyCardBetweenTwoPlayers
                        .changeHealthCardBetweenTwoPlayers(sessionId, playerName, secondPlayerName);
                case BIOLOGY -> changeHobbyCardBetweenTwoPlayers
                        .changeBiologyCardBetweenTwoPlayers(sessionId, playerName, secondPlayerName);
                case LUGGAGE -> changeHobbyCardBetweenTwoPlayers
                        .changeLuggageCardBetweenTwoPlayers(sessionId, playerName, secondPlayerName);
                case PHOBIAS -> changeHobbyCardBetweenTwoPlayers
                        .changePhobiasCardBetweenTwoPlayers(sessionId, playerName, secondPlayerName);
                case PROFESSION -> changeHobbyCardBetweenTwoPlayers
                        .changeProfessionCardBetweenTwoPlayers(sessionId, playerName, secondPlayerName);
                case EXTRA_SKILLS -> changeHobbyCardBetweenTwoPlayers
                        .changeExtraSkillsCardBetweenTwoPlayers(sessionId, playerName, secondPlayerName);
                case HUMAN_QUALITIES -> changeHobbyCardBetweenTwoPlayers
                        .changeHumanQualitiesCardBetweenTwoPlayers(sessionId, playerName, secondPlayerName);
                default -> throw new RuntimeException("No action found");
            }
            specialCardType = SpecialCardType.CHANGE_WITH_PLAYER;
        } else if (specialCard instanceof ChangeSpecialCard changeSpecialCard) {
            switch (changeSpecialCard.getInteraction()) {
                case HOBBY -> changeSpecialCardService.changeHobbyCard(sessionId, playerName);
                case HEALTH -> changeSpecialCardService.changeHealthCard(sessionId, playerName);
                case BIOLOGY -> changeSpecialCardService.changeBiologyCard(sessionId, playerName);
                case LUGGAGE -> changeSpecialCardService.changeLuggageCard(sessionId, playerName);
                case PHOBIAS -> changeSpecialCardService.changePhobiasCard(sessionId, playerName);
                case PROFESSION -> changeSpecialCardService.changeProfessionCard(sessionId, playerName);
                case EXTRA_SKILLS -> changeSpecialCardService.changeExtraSkillsCard(sessionId, playerName);
                case HUMAN_QUALITIES -> changeSpecialCardService.changeHumanQualitiesCard(sessionId, playerName);
                case BUNKER -> changeSpecialCardService.changeBunkerCard(sessionId, cardId);
                default -> throw new RuntimeException("No action found");
            }
            specialCardType = SpecialCardType.CHANGE;
        } else if (specialCard instanceof VoteSpecialCard voteSpecialCard) {
            switch (voteSpecialCard.getInteraction()) {
                case NOT_VOTE -> voteSpecialCardService.notVote(sessionId, playerName);
                case DOUBLE_VOTE -> voteSpecialCardService.doubleVote(sessionId, playerName);
                case REVOTE -> voteSpecialCardService.revote(sessionId);
                case VOTE_FOR_YOURSELF -> voteSpecialCardService.voteForYourself(sessionId, playerName);
                case DOUBLE_VOTE_NOT_VOTE -> voteSpecialCardService
                        .doubleVotesForPlayer(sessionId, playerName, secondPlayerName);
                case NOT_VOTE_FOR_YOU -> voteSpecialCardService.notVoteForYou(sessionId, secondPlayerName, playerName);
                default -> throw new RuntimeException("No action found");
            }
            specialCardType = SpecialCardType.VOTE;
        } else if (specialCard instanceof DropSpecialCard dropSpecialCard) {
            switch (dropSpecialCard.getInteraction()) {
                case HOBBY -> dropSpecialCardService.dropHobbyCard(sessionId, playerName);
                case HEALTH -> dropSpecialCardService.dropHealthCard(sessionId, playerName);
                case LUGGAGE -> dropSpecialCardService.dropLuggageCard(sessionId, playerName);
                case PHOBIAS -> dropSpecialCardService.dropPhobiasCard(sessionId, playerName);
                case EXTRA_SKILLS -> dropSpecialCardService.dropExtraSkillsCard(sessionId, playerName);
                case HUMAN_QUALITIES -> dropSpecialCardService.dropHumanQualitiesCard(sessionId, playerName);
                case BUNKER -> dropSpecialCardService.dropBunkerCard(sessionId, cardId);
                case HOBBY_GET_SPECIAL_CARD -> dropSpecialCardService.dropHobbyCardGetSpecialCard(sessionId,
                        playerName);
                case HEALTH_GET_SPECIAL_CARD -> dropSpecialCardService.dropHealthCardGetSpecialCard(sessionId,
                        playerName);
                case LUGGAGE_GET_SPECIAL_CARD -> dropSpecialCardService.dropLuggageCardGetSpecialCard(sessionId,
                        playerName);
                case PHOBIAS_GET_SPECIAL_CARD -> dropSpecialCardService.dropPhobiasCardGetSpecialCard(sessionId,
                        playerName);
                case EXTRA_SKILLS_GET_SPECIAL_CARD -> dropSpecialCardService
                        .dropExtraSkillsCardGetSpecialCard(sessionId, playerName);
                case HUMAN_QUALITIES_GET_SPECIAL_CARD -> dropSpecialCardService
                        .dropHumanQualitiesCardGetSpecialCard(sessionId, playerName);
                default -> throw new RuntimeException("No action found");
            }
            specialCardType = SpecialCardType.DROP;
        } else if (specialCard instanceof OpenSpecialCard openSpecialCard) {
            switch (openSpecialCard.getInteraction()) {
                case HOBBY -> openSpecialCardService.openBiologyCard();
                case HEALTH -> openSpecialCardService.openExtraSkillsCard();
                case BIOLOGY -> openSpecialCardService.openHealthCard();
                case LUGGAGE -> openSpecialCardService.openHobbyCard();
                case PHOBIAS -> openSpecialCardService.openHumanQualitiesCard();
                case PROFESSION -> openSpecialCardService.openProfessionCard();
                case EXTRA_SKILLS -> openSpecialCardService.openPhobiasCard();
                case HUMAN_QUALITIES -> openSpecialCardService.openLuggageCard();
                default -> throw new RuntimeException("No action found");
            }
            specialCardType = SpecialCardType.OPEN;
        }
        return specialCardType;
    }

    public CommonCard openCard(String sessionId, String playerName, String cardId) {
        CommonCard card =
                commonCardRepository.findById(cardId).orElseThrow(() -> new ResourceNotFoundException(CARD_NOT_FOUND));
        Player player = playerService.getPlayer(sessionId, playerName);
        Character character = player.getCharacter();
        String cardsToOpen = character.getCardToOpen();
        String openedCards = character.getOpenedCardTypes();
        if (card instanceof Biology) {
            Biology biologyCrad = (Biology) card;
            if ((openedCards != null && openedCards.contains(biologyCrad.getClass().getName()))
                    || (openedCards != null && !cardsToOpen.contains(biologyCrad.getClass().getName()))
                    || !character.getBiology().equals(biologyCrad)) {
                throw new RuntimeException("Cannt open this card");
            }
            return card;
        } else if (card instanceof Profession) {
            Profession profession = (Profession) card;
            boolean ventil = character.getProfession().equals(profession);
            if ((openedCards != null && openedCards.contains(profession.getClass().getName()))
                    || (openedCards != null && !cardsToOpen.contains(profession.getClass().getName()))
                    || !character.getProfession().getId().equals(profession.getId())) {
                throw new RuntimeException("Cannt open this card");
            }
            return profession;
        } else if (card instanceof Health) {
            Health health = (Health) card;
            if ((openedCards != null && openedCards.contains(health.getClass().getName()))
                    || (openedCards != null && !cardsToOpen.contains(health.getClass().getName()))
                    || !character.getHealth().getId().equals(health.getId())) {
                throw new RuntimeException("Cannt open this card");
            }
            return health;
        } else if (card instanceof ExtraSkills) {
            ExtraSkills extraSkills = (ExtraSkills) card;
            if ((openedCards != null && openedCards.contains(extraSkills.getClass().getName()))
                    || (openedCards != null && !cardsToOpen.contains(extraSkills.getClass().getName()))
                    || !character.getExtraSkills().getId().equals(extraSkills.getId())) {
                throw new RuntimeException("Cannt open this card");
            }
            return extraSkills;
        } else if (card instanceof HumanQualities) {
            HumanQualities humanQualities = (HumanQualities) card;
            if ((openedCards != null && openedCards.contains(humanQualities.getClass().getName()))
                    || (openedCards != null && !cardsToOpen.contains(humanQualities.getClass().getName()))
                    || !character.getHumanQualities().getId().equals(card.getId())) {
                throw new RuntimeException("Cannt open this card");
            }
            return humanQualities;
        } else if (card instanceof Hobby) {
            Hobby hobby = (Hobby) card;
            if ((openedCards != null && openedCards.contains(hobby.getClass().getName()))
                    || (openedCards != null && !cardsToOpen.contains(hobby.getClass().getName()))
                    || !character.getHobby().getId().equals(hobby.getId())) {
                throw new RuntimeException("Cannt open this card");
            }
            return hobby;
        } else if (card instanceof Phobias) {
            Phobias phobias = (Phobias) card;
            if ((openedCards != null && openedCards.contains(phobias.getClass().getName()))
                    || (openedCards != null && !cardsToOpen.contains(phobias.getClass().getName()))
                    || !character.getPhobias().getId().equals(phobias.getId())) {
                throw new RuntimeException("Cannt open this card");
            }
            return phobias;
        } else if (card instanceof Luggage) {
            Luggage luggage = (Luggage) card;
            if ((openedCards != null && openedCards.contains(luggage.getClass().getName()))
                    || (openedCards != null && !cardsToOpen.contains(luggage.getClass().getName()))
                    || !character.getLuggage().getId().equals(luggage.getId())) {
                throw new RuntimeException("Cannt open this card");
            }
            return luggage;
        } else {
            throw new ResourceNotFoundException(CARD_NOT_FOUND);
        }
    }
}
