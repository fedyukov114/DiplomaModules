package board.games.bunker.services.specialcardservices;

import static board.games.bunker.params.ErrorMessage.CARD_NOT_FOUND;

import board.games.bunker.entity.Character;
import board.games.bunker.entity.cards.BunkerCard;
import board.games.bunker.entity.cards.specialcards.repo.SpecialCardRepository;
import board.games.bunker.entity.repo.CharacterRepository;
import board.games.bunker.entity.session.Session;
import board.games.bunker.entity.session.repo.SessionRepository;
import board.games.bunker.exception.ResourceNotFoundException;
import board.games.bunker.services.PlayerService;
import board.games.bunker.services.SessionCommonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DropSpecialCardService {

    private final SessionCommonService sessionCommonService;

    private final PlayerService playerService;

    private final CharacterRepository characterRepository;

    private final SpecialCardRepository specialCardRepository;

    private final SessionRepository sessionRepository;

    public DropSpecialCardService(SessionCommonService sessionCommonService,
            PlayerService playerService,
            CharacterRepository characterRepository,
            SpecialCardRepository specialCardRepository,
            SessionRepository sessionRepository) {
        this.sessionCommonService = sessionCommonService;
        this.playerService = playerService;
        this.characterRepository = characterRepository;
        this.specialCardRepository = specialCardRepository;
        this.sessionRepository = sessionRepository;
    }

    @Transactional
    public void dropHobbyCard(String sessionId, String playerName) {
        Character character = playerService.getPlayer(sessionId, playerName).getCharacter();
        character.setHobby(null);
        characterRepository.saveAndFlush(character);
    }

    @Transactional
    public void dropHealthCard(String sessionId, String playerName) {
        Character character = playerService.getPlayer(sessionId, playerName).getCharacter();
        character.setHealth(null);
        characterRepository.saveAndFlush(character);
    }

    @Transactional
    public void dropLuggageCard(String sessionId, String playerName) {
        Character character = playerService.getPlayer(sessionId, playerName).getCharacter();
        character.setLuggage(null);
        characterRepository.saveAndFlush(character);
    }

    @Transactional
    public void dropPhobiasCard(String sessionId, String playerName) {
        Character character = playerService.getPlayer(sessionId, playerName).getCharacter();
        character.setPhobias(null);
        characterRepository.saveAndFlush(character);
    }

    @Transactional
    public void dropExtraSkillsCard(String sessionId, String playerName) {
        Character character = playerService.getPlayer(sessionId, playerName).getCharacter();
        character.setExtraSkills(null);
        characterRepository.saveAndFlush(character);
    }

    @Transactional
    public void dropHumanQualitiesCard(String sessionId, String playerName) {
        Character character = playerService.getPlayer(sessionId, playerName).getCharacter();
        character.setHumanQualities(null);
        characterRepository.saveAndFlush(character);
    }

    @Transactional
    public void dropBunkerCard(String sessionId, String cardId) {
        Session session = sessionCommonService.getSession(sessionId);
        BunkerCard bunkerCard = session.getBunkerCards()
                .stream()
                .filter(card -> card.getId().equals(cardId))
                .findAny()
                .orElseThrow(() -> new ResourceNotFoundException(CARD_NOT_FOUND));
        session.getBunkerCards().remove(bunkerCard);
        sessionRepository.saveAndFlush(session);
    }

    @Transactional
    public void dropHobbyCardGetSpecialCard(String sessionId, String playerName) {
        Character character = playerService.getPlayer(sessionId, playerName).getCharacter();
        character.setHobby(null);
        character.getSpecialCard().add(specialCardRepository.getRandomSpecialCard());
        characterRepository.saveAndFlush(character);
    }

    @Transactional
    public void dropHealthCardGetSpecialCard(String sessionId, String playerName) {
        Character character = playerService.getPlayer(sessionId, playerName).getCharacter();
        character.setHealth(null);
        character.getSpecialCard().add(specialCardRepository.getRandomSpecialCard());
        characterRepository.saveAndFlush(character);
    }

    @Transactional
    public void dropLuggageCardGetSpecialCard(String sessionId, String playerName) {
        Character character = playerService.getPlayer(sessionId, playerName).getCharacter();
        character.setLuggage(null);
        character.getSpecialCard().add(specialCardRepository.getRandomSpecialCard());
        characterRepository.saveAndFlush(character);
    }

    @Transactional
    public void dropPhobiasCardGetSpecialCard(String sessionId, String playerName) {
        Character character = playerService.getPlayer(sessionId, playerName).getCharacter();
        character.setPhobias(null);
        character.getSpecialCard().add(specialCardRepository.getRandomSpecialCard());
        characterRepository.saveAndFlush(character);
    }

    @Transactional
    public void dropExtraSkillsCardGetSpecialCard(String sessionId, String playerName) {
        Character character = playerService.getPlayer(sessionId, playerName).getCharacter();
        character.setExtraSkills(null);
        character.getSpecialCard().add(specialCardRepository.getRandomSpecialCard());
        characterRepository.saveAndFlush(character);
    }

    @Transactional
    public void dropHumanQualitiesCardGetSpecialCard(String sessionId, String playerName) {
        Character character = playerService.getPlayer(sessionId, playerName).getCharacter();
        character.setHumanQualities(null);
        character.getSpecialCard().add(specialCardRepository.getRandomSpecialCard());
        characterRepository.saveAndFlush(character);
    }
}
