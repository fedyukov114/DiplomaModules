package board.games.bunker.services.specialcardservices;

import board.games.bunker.entity.Character;
import board.games.bunker.entity.Player;
import board.games.bunker.entity.cards.charactercards.*;
import board.games.bunker.entity.cards.charactercards.repo.CommonCardRepository;
import board.games.bunker.entity.cards.repo.BunkerCardRepository;
import board.games.bunker.entity.cards.specialcards.repo.SpecialCardRepository;
import board.games.bunker.entity.repo.CharacterRepository;
import board.games.bunker.entity.session.Session;
import board.games.bunker.entity.session.repo.SessionRepository;
import board.games.bunker.services.PlayerService;
import board.games.bunker.services.SessionCommonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ChangeSpecialCardService {

    private final SessionCommonService sessionCommonService;

    private final PlayerService playerService;

    private final CharacterRepository characterRepository;

    private final CommonCardRepository commonCardRepository;

    private final SpecialCardRepository specialCardRepository;

    private final BunkerCardRepository bunkerCardRepository;

    private final SessionRepository sessionRepository;

    public ChangeSpecialCardService(SessionCommonService sessionCommonService,
            PlayerService playerService,
            CharacterRepository characterRepository,
            CommonCardRepository commonCardRepository,
            SpecialCardRepository specialCardRepository,
            BunkerCardRepository bunkerCardRepository,
            SessionRepository sessionRepository) {
        this.sessionCommonService = sessionCommonService;
        this.playerService = playerService;
        this.characterRepository = characterRepository;
        this.commonCardRepository = commonCardRepository;
        this.specialCardRepository = specialCardRepository;
        this.bunkerCardRepository = bunkerCardRepository;
        this.sessionRepository = sessionRepository;
    }

    @Transactional
    public void changeBiologyCard(String sessionId, String playerName) {
        Player player = playerService.getPlayer(sessionId, playerName);
        Character character = player.getCharacter();
        if (character.getOpenedCardTypes() != null
                && !character.getOpenedCardTypes().contains(Biology.class.getName())) {
            throw new RuntimeException("Can`t change closed card");
        }
        character.setBiology(commonCardRepository.getRandomBiology());
        characterRepository.saveAndFlush(character);
    }

    @Transactional
    public void changeExtraSkillsCard(String sessionId, String playerName) {
        Player player = playerService.getPlayer(sessionId, playerName);
        Character character = player.getCharacter();
        if (character.getOpenedCardTypes() != null
                && !character.getOpenedCardTypes().contains(ExtraSkills.class.getName())) {
            throw new RuntimeException("Can`t change closed card");
        }
        character.setExtraSkills(commonCardRepository.getRandomExtraSkills());
        characterRepository.saveAndFlush(character);
    }

    @Transactional
    public void changeHealthCard(String sessionId, String playerName) {
        Player player = playerService.getPlayer(sessionId, playerName);
        Character character = player.getCharacter();
        if (character.getOpenedCardTypes() != null
                && !character.getOpenedCardTypes().contains(Health.class.getName())) {
            throw new RuntimeException("Can`t change closed card");
        }
        character.setHealth(commonCardRepository.getRandomHealth());
        characterRepository.saveAndFlush(character);
    }

    @Transactional
    public void changeHobbyCard(String sessionId, String playerName) {
        Player player = playerService.getPlayer(sessionId, playerName);
        Character character = player.getCharacter();
        if (character.getOpenedCardTypes() != null && !character.getOpenedCardTypes().contains(Hobby.class.getName())) {
            throw new RuntimeException("Can`t change closed card");
        }
        character.setHobby(commonCardRepository.getRandomHobby());
        characterRepository.saveAndFlush(character);
    }

    @Transactional
    public void changeHumanQualitiesCard(String sessionId, String playerName) {
        Player player = playerService.getPlayer(sessionId, playerName);
        Character character = player.getCharacter();
        if (character.getOpenedCardTypes() != null
                && !character.getOpenedCardTypes().contains(HumanQualities.class.getName())) {
            throw new RuntimeException("Can`t change closed card");
        }
        character.setHumanQualities(commonCardRepository.getRandomHumanQualities());
        characterRepository.saveAndFlush(character);
    }

    @Transactional
    public void changeProfessionCard(String sessionId, String playerName) {
        Player player = playerService.getPlayer(sessionId, playerName);
        Character character = player.getCharacter();
        if (character.getOpenedCardTypes() != null
                && !character.getOpenedCardTypes().contains(Profession.class.getName())) {
            throw new RuntimeException("Can`t change closed card");
        }
        character.setProfession(commonCardRepository.getRandomProfession());
        characterRepository.saveAndFlush(character);
    }

    @Transactional
    public void changePhobiasCard(String sessionId, String playerName) {
        Player player = playerService.getPlayer(sessionId, playerName);
        Character character = player.getCharacter();
        if (character.getOpenedCardTypes() != null
                && !character.getOpenedCardTypes().contains(Phobias.class.getName())) {
            throw new RuntimeException("Can`t change closed card");
        }
        character.setPhobias(commonCardRepository.getRandomPhobias());
        characterRepository.saveAndFlush(character);
    }

    @Transactional
    public void changeLuggageCard(String sessionId, String playerName) {
        Player player = playerService.getPlayer(sessionId, playerName);
        Character character = player.getCharacter();
        if (character.getOpenedCardTypes() != null
                && !character.getOpenedCardTypes().contains(Luggage.class.getName())) {
            throw new RuntimeException("Can`t change closed card");
        }
        character.setLuggage(commonCardRepository.getRandomLuggae());
        characterRepository.saveAndFlush(character);
    }

    @Transactional
    public void changeSpecialCard(String sessionId, String playerName, String cardId) {
        Player player = playerService.getPlayer(sessionId, playerName);
        Character character = player.getCharacter();
        character.getSpecialCard()
                .stream()
                .filter(card -> card.getId().equals(cardId))
                .forEach(card -> card = specialCardRepository.getRandomSpecialCard());
        characterRepository.saveAndFlush(character);
    }

    @Transactional
    public void changeBunkerCard(String sessionId, String cardId) {
        Session session = sessionCommonService.getSession(sessionId);
        session.getBunkerCards()
                .stream()
                .filter(bunkerCard -> bunkerCard.getId().equals(cardId))
                .forEach(bunkerCard -> bunkerCard = bunkerCardRepository.getRandomBunkerCard());
        sessionRepository.saveAndFlush(session);
    }
}
