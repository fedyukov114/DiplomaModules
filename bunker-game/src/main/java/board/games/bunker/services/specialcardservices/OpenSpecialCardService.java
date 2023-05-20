package board.games.bunker.services.specialcardservices;

import board.games.bunker.entity.Character;
import board.games.bunker.entity.cards.charactercards.*;
import board.games.bunker.entity.repo.CharacterRepository;
import board.games.bunker.services.PlayerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OpenSpecialCardService {

    private static final String SEPARATOR = " ;";

    private final PlayerService playerService;

    private final CharacterRepository characterRepository;

    public OpenSpecialCardService(PlayerService playerService, CharacterRepository characterRepository) {
        this.playerService = playerService;
        this.characterRepository = characterRepository;
    }

    @Transactional
    public void openBiologyCard() {
        playerService.getAllPlayers().forEach(player -> {
            Character character = player.getCharacter();
            character.setCardToOpen(character.getCardToOpen() + SEPARATOR + Biology.class.getName());
            characterRepository.saveAndFlush(character);
        });
    }

    @Transactional
    public void openExtraSkillsCard() {
        playerService.getAllPlayers().forEach(player -> {
            Character character = player.getCharacter();
            character.setCardToOpen(character.getCardToOpen() + SEPARATOR + ExtraSkills.class.getName());
            characterRepository.saveAndFlush(character);
        });
    }

    @Transactional
    public void openHealthCard() {
        playerService.getAllPlayers().forEach(player -> {
            Character character = player.getCharacter();
            character.setCardToOpen(character.getCardToOpen() + SEPARATOR + Health.class.getName());
            characterRepository.saveAndFlush(character);
        });
    }

    @Transactional
    public void openHobbyCard() {
        playerService.getAllPlayers().forEach(player -> {
            Character character = player.getCharacter();
            character.setCardToOpen(character.getCardToOpen() + SEPARATOR + Hobby.class.getName());
            characterRepository.saveAndFlush(character);
        });
    }

    @Transactional
    public void openHumanQualitiesCard() {
        playerService.getAllPlayers().forEach(player -> {
            Character character = player.getCharacter();
            character.setCardToOpen(character.getCardToOpen() + SEPARATOR + HumanQualities.class.getName());
            characterRepository.saveAndFlush(character);
        });
    }

    @Transactional
    public void openProfessionCard() {
        playerService.getAllPlayers().forEach(player -> {
            Character character = player.getCharacter();
            character.setCardToOpen(character.getCardToOpen() + SEPARATOR + Profession.class.getName());
            characterRepository.saveAndFlush(character);
        });
    }

    @Transactional
    public void openPhobiasCard() {
        playerService.getAllPlayers().forEach(player -> {
            Character character = player.getCharacter();
            character.setCardToOpen(character.getCardToOpen() + SEPARATOR + Phobias.class.getName());
            characterRepository.saveAndFlush(character);
        });
    }

    @Transactional
    public void openLuggageCard() {
        playerService.getAllPlayers().forEach(player -> {
            Character character = player.getCharacter();
            character.setCardToOpen(character.getCardToOpen() + SEPARATOR + Luggage.class.getName());
            characterRepository.saveAndFlush(character);
        });
    }
}
