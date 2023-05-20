package board.games.bunker.services.specialcardservices;

import board.games.bunker.entity.Character;
import board.games.bunker.entity.cards.charactercards.*;
import board.games.bunker.entity.repo.CharacterRepository;
import board.games.bunker.services.PlayerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ChangeWithPlayerSpecialCardService {

    private final PlayerService playerService;

    private final CharacterRepository characterRepository;

    public ChangeWithPlayerSpecialCardService(PlayerService playerService, CharacterRepository characterRepository) {
        this.playerService = playerService;
        this.characterRepository = characterRepository;
    }

    @Transactional
    public void changeHobbyCardBetweenTwoPlayers(String sessionId, String playerName, String playerToChangeWithName) {
        Character character = playerService.getPlayer(sessionId, playerName).getCharacter();
        Character characterToChangeWith = playerService.getPlayer(sessionId, playerToChangeWithName).getCharacter();
        Hobby firstCharacterHobby = character.getHobby();
        character.setHobby(characterToChangeWith.getHobby());
        characterToChangeWith.setHobby(firstCharacterHobby);
        characterRepository.saveAndFlush(character);
        characterRepository.saveAndFlush(characterToChangeWith);
    }

    @Transactional
    public void changeBiologyCardBetweenTwoPlayers(String sessionId, String playerName, String playerToChangeWithName) {
        Character character = playerService.getPlayer(sessionId, playerName).getCharacter();
        Character characterToChangeWith = playerService.getPlayer(sessionId, playerToChangeWithName).getCharacter();
        Biology firstCharacterBiology = character.getBiology();
        character.setBiology(characterToChangeWith.getBiology());
        characterToChangeWith.setBiology(firstCharacterBiology);
        characterRepository.saveAndFlush(character);
        characterRepository.saveAndFlush(characterToChangeWith);
    }

    @Transactional
    public void
            changeExtraSkillsCardBetweenTwoPlayers(String sessionId, String playerName, String playerToChangeWithName) {
        Character character = playerService.getPlayer(sessionId, playerName).getCharacter();
        Character characterToChangeWith = playerService.getPlayer(sessionId, playerToChangeWithName).getCharacter();
        ExtraSkills firstCharacterExtraSkills = character.getExtraSkills();
        character.setExtraSkills(characterToChangeWith.getExtraSkills());
        characterToChangeWith.setExtraSkills(firstCharacterExtraSkills);
        characterRepository.saveAndFlush(character);
        characterRepository.saveAndFlush(characterToChangeWith);
    }

    @Transactional
    public void changeHealthCardBetweenTwoPlayers(String sessionId, String playerName, String playerToChangeWithName) {
        Character character = playerService.getPlayer(sessionId, playerName).getCharacter();
        Character characterToChangeWith = playerService.getPlayer(sessionId, playerToChangeWithName).getCharacter();
        Health firstCharacterHealth = character.getHealth();
        character.setHealth(characterToChangeWith.getHealth());
        characterToChangeWith.setHealth(firstCharacterHealth);
        characterRepository.saveAndFlush(character);
        characterRepository.saveAndFlush(characterToChangeWith);
    }

    @Transactional
    public void changeLuggageCardBetweenTwoPlayers(String sessionId, String playerName, String playerToChangeWithName) {
        Character character = playerService.getPlayer(sessionId, playerName).getCharacter();
        Character characterToChangeWith = playerService.getPlayer(sessionId, playerToChangeWithName).getCharacter();
        Luggage firstCharacterLuggage = character.getLuggage();
        character.setLuggage(characterToChangeWith.getLuggage());
        characterToChangeWith.setLuggage(firstCharacterLuggage);
        characterRepository.saveAndFlush(character);
        characterRepository.saveAndFlush(characterToChangeWith);
    }

    @Transactional
    public void changePhobiasCardBetweenTwoPlayers(String sessionId, String playerName, String playerToChangeWithName) {
        Character character = playerService.getPlayer(sessionId, playerName).getCharacter();
        Character characterToChangeWith = playerService.getPlayer(sessionId, playerToChangeWithName).getCharacter();
        Phobias firstCharacterPhobia = character.getPhobias();
        character.setPhobias(characterToChangeWith.getPhobias());
        characterToChangeWith.setPhobias(firstCharacterPhobia);
        characterRepository.saveAndFlush(character);
        characterRepository.saveAndFlush(characterToChangeWith);
    }

    @Transactional
    public void
            changeProfessionCardBetweenTwoPlayers(String sessionId, String playerName, String playerToChangeWithName) {
        Character character = playerService.getPlayer(sessionId, playerName).getCharacter();
        Character characterToChangeWith = playerService.getPlayer(sessionId, playerToChangeWithName).getCharacter();
        Profession firstCharacterProfession = character.getProfession();
        character.setProfession(characterToChangeWith.getProfession());
        characterToChangeWith.setProfession(firstCharacterProfession);
        characterRepository.saveAndFlush(character);
        characterRepository.saveAndFlush(characterToChangeWith);
    }

    @Transactional
    public void changeHumanQualitiesCardBetweenTwoPlayers(String sessionId,
            String playerName,
            String playerToChangeWithName) {
        Character character = playerService.getPlayer(sessionId, playerName).getCharacter();
        Character characterToChangeWith = playerService.getPlayer(sessionId, playerToChangeWithName).getCharacter();
        HumanQualities firstCharacterHumanQualities = character.getHumanQualities();
        character.setHumanQualities(characterToChangeWith.getHumanQualities());
        characterToChangeWith.setHumanQualities(firstCharacterHumanQualities);
        characterRepository.saveAndFlush(character);
        characterRepository.saveAndFlush(characterToChangeWith);
    }
}
