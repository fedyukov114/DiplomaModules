package board.games.bunker.services.specialcardservices;

import static board.games.bunker.enums.PlayerStatus.PLAYING;

import board.games.bunker.entity.Character;
import board.games.bunker.entity.cards.charactercards.*;
import board.games.bunker.entity.repo.CharacterRepository;
import board.games.bunker.entity.session.Session;
import board.games.bunker.services.SessionCommonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class MixingSpecialCardService {

    private final SessionCommonService sessionCommonService;

    private final CharacterRepository characterRepository;

    public MixingSpecialCardService(SessionCommonService sessionCommonService,
            CharacterRepository characterRepository) {
        this.sessionCommonService = sessionCommonService;
        this.characterRepository = characterRepository;
    }

    @Transactional
    public void changeBiologyCardBetweenPlayers(String sessionId) {
        Session session = sessionCommonService.getSession(sessionId);
        List<Biology> biologyCards = session.getPlayers()
                .stream()
                .filter(player -> player.getStatus().equals(PLAYING))
                .map(player -> player.getCharacter().getBiology())
                .collect(Collectors.toList());
        if (biologyCards.size() <= 1) {
            return;
        }
        session.getPlayers().forEach(player -> {
            Random rand = new Random();
            int elemntPosition = rand.nextInt(biologyCards.size());
            Character character = player.getCharacter();
            character.setBiology(biologyCards.get(elemntPosition));
            biologyCards.remove(elemntPosition);
            characterRepository.saveAndFlush(character);
        });
    }

    @Transactional
    public void changeExtraSkillsCardBetweenPlayers(String sessionId) {
        Session session = sessionCommonService.getSession(sessionId);
        List<ExtraSkills> extraSkillsCards = session.getPlayers()
                .stream()
                .filter(player -> player.getStatus().equals(PLAYING))
                .map(player -> player.getCharacter().getExtraSkills())
                .collect(Collectors.toList());
        if (extraSkillsCards.size() <= 1) {
            return;
        }
        session.getPlayers().forEach(player -> {
            Random rand = new Random();
            int elemntPosition = rand.nextInt(extraSkillsCards.size());
            Character character = player.getCharacter();
            character.setExtraSkills(extraSkillsCards.get(elemntPosition));
            extraSkillsCards.remove(elemntPosition);
            characterRepository.saveAndFlush(character);
        });
    }

    @Transactional
    public void changeHealthCardBetweenPlayers(String sessionId) {
        Session session = sessionCommonService.getSession(sessionId);
        List<Health> healthCards = session.getPlayers()
                .stream()
                .filter(player -> player.getStatus().equals(PLAYING))
                .map(player -> player.getCharacter().getHealth())
                .collect(Collectors.toList());
        if (healthCards.size() <= 1) {
            return;
        }
        session.getPlayers().forEach(player -> {
            Random rand = new Random();
            int elemntPosition = rand.nextInt(healthCards.size());
            Character character = player.getCharacter();
            character.setHealth(healthCards.get(elemntPosition));
            healthCards.remove(elemntPosition);
            characterRepository.saveAndFlush(character);
        });
    }

    @Transactional
    public void changeHobbyCardBetweenPlayers(String sessionId) {
        Session session = sessionCommonService.getSession(sessionId);
        List<Hobby> hobbyCards = session.getPlayers()
                .stream()
                .filter(player -> player.getStatus().equals(PLAYING))
                .map(player -> player.getCharacter().getHobby())
                .collect(Collectors.toList());
        if (hobbyCards.size() <= 1) {
            return;
        }
        session.getPlayers().forEach(player -> {
            Random rand = new Random();
            int elemntPosition = rand.nextInt(hobbyCards.size());
            Character character = player.getCharacter();
            character.setHobby(hobbyCards.get(elemntPosition));
            hobbyCards.remove(elemntPosition);
            characterRepository.saveAndFlush(character);
        });
    }

    @Transactional
    public void changeHumanQualitiesCardBetweenPlayers(String sessionId) {
        Session session = sessionCommonService.getSession(sessionId);
        List<Hobby> hobbyCards = session.getPlayers()
                .stream()
                .filter(player -> player.getStatus().equals(PLAYING))
                .map(player -> player.getCharacter().getHobby())
                .collect(Collectors.toList());
        if (hobbyCards.size() <= 1) {
            return;
        }
        session.getPlayers().forEach(player -> {
            Random rand = new Random();
            int elemntPosition = rand.nextInt(hobbyCards.size());
            Character character = player.getCharacter();
            character.setHobby(hobbyCards.get(elemntPosition));
            hobbyCards.remove(elemntPosition);
            characterRepository.saveAndFlush(character);
        });
    }

    @Transactional
    public void changeProfessionCardBetweenPlayers(String sessionId) {
        Session session = sessionCommonService.getSession(sessionId);
        List<Profession> professionCards = session.getPlayers()
                .stream()
                .filter(player -> player.getStatus().equals(PLAYING))
                .map(player -> player.getCharacter().getProfession())
                .collect(Collectors.toList());
        if (professionCards.size() <= 1) {
            return;
        }
        session.getPlayers().forEach(player -> {
            Random rand = new Random();
            int elemntPosition = rand.nextInt(professionCards.size());
            Character character = player.getCharacter();
            character.setProfession(professionCards.get(elemntPosition));
            professionCards.remove(elemntPosition);
            characterRepository.saveAndFlush(character);
        });
    }

    @Transactional
    public void changePhobiasCardBetweenPlayers(String sessionId) {
        Session session = sessionCommonService.getSession(sessionId);
        List<Phobias> phobiaCards = session.getPlayers()
                .stream()
                .filter(player -> player.getStatus().equals(PLAYING))
                .map(player -> player.getCharacter().getPhobias())
                .collect(Collectors.toList());
        if (phobiaCards.size() <= 1) {
            return;
        }
        session.getPlayers().forEach(player -> {
            Random rand = new Random();
            int elemntPosition = rand.nextInt(phobiaCards.size());
            Character character = player.getCharacter();
            character.setPhobias(phobiaCards.get(elemntPosition));
            phobiaCards.remove(elemntPosition);
            characterRepository.saveAndFlush(character);
        });
    }

    @Transactional
    public void changeLuggageCardBetweenPlayers(String sessionId) {
        Session session = sessionCommonService.getSession(sessionId);
        List<Luggage> luggageCards = session.getPlayers()
                .stream()
                .filter(player -> player.getStatus().equals(PLAYING))
                .map(player -> player.getCharacter().getLuggage())
                .collect(Collectors.toList());
        if (luggageCards.size() <= 1) {
            return;
        }
        session.getPlayers().forEach(player -> {
            Random rand = new Random();
            int elemntPosition = rand.nextInt(luggageCards.size());
            Character character = player.getCharacter();
            character.setLuggage(luggageCards.get(elemntPosition));
            luggageCards.remove(elemntPosition);
            characterRepository.saveAndFlush(character);
        });
    }
}
