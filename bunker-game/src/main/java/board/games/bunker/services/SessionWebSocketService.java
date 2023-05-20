package board.games.bunker.services;

import static board.games.bunker.enums.PlayerRole.USER;
import static board.games.bunker.enums.PlayerStatus.KICKED_OUT;
import static board.games.bunker.enums.PlayerStatus.PLAYING;
import static board.games.bunker.enums.SessionState.IN_PROGRESS;

import board.games.bunker.MessageCreator;
import board.games.bunker.dto.response.KickedPlayerDTO;
import board.games.bunker.dto.response.PlayerCardsDTO;
import board.games.bunker.dto.response.StartGameResultDTO;
import board.games.bunker.entity.Character;
import board.games.bunker.entity.Message;
import board.games.bunker.entity.Player;
import board.games.bunker.entity.Votes;
import board.games.bunker.entity.cards.BunkerCard;
import board.games.bunker.entity.cards.Catastrophe;
import board.games.bunker.entity.cards.charactercards.repo.CommonCardRepository;
import board.games.bunker.entity.cards.repo.BunkerCardRepository;
import board.games.bunker.entity.cards.repo.CatastropheRepository;
import board.games.bunker.entity.cards.specialcards.repo.SpecialCardRepository;
import board.games.bunker.entity.repo.CharacterRepository;
import board.games.bunker.entity.repo.MessageRepository;
import board.games.bunker.entity.repo.PlayerRepository;
import board.games.bunker.entity.repo.VoteRepository;
import board.games.bunker.entity.session.Session;
import board.games.bunker.entity.session.repo.SessionRepository;
import board.games.bunker.mapper.PlayerMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
public class SessionWebSocketService {

    private final MessageRepository messageRepository;

    private final PlayerService playerService;

    private final BunkerCardRepository bunkerCardRepository;

    private final CatastropheRepository catastropheRepository;

    private final SessionRepository sessionRepository;

    private final SessionCommonService sessionCommonService;

    private final CharacterRepository characterRepository;

    private final PlayerRepository playerRepository;

    private final CommonCardRepository commonCardRepository;

    private final SpecialCardRepository specialCardRepository;

    private final VoteRepository voteRepository;

    public SessionWebSocketService(MessageRepository messageDAO,
            PlayerService playerService,
            BunkerCardRepository bunkerCardRepository,
            CatastropheRepository catastropheRepository,
            SessionRepository sessionRepository,
            SessionCommonService sessionCommonService,
            CharacterRepository characterRepository,
            PlayerRepository playerRepository,
            CommonCardRepository commonCardRepository,
            SpecialCardRepository specialCardRepository,
            VoteRepository voteRepository) {
        this.messageRepository = messageDAO;
        this.playerService = playerService;
        this.bunkerCardRepository = bunkerCardRepository;
        this.catastropheRepository = catastropheRepository;
        this.sessionRepository = sessionRepository;
        this.sessionCommonService = sessionCommonService;
        this.characterRepository = characterRepository;
        this.playerRepository = playerRepository;
        this.commonCardRepository = commonCardRepository;
        this.specialCardRepository = specialCardRepository;
        this.voteRepository = voteRepository;
    }

    @Transactional
    public Player addPlayerToSession(String sessionId, String playerName) {
        Session session = sessionCommonService.getSession(sessionId);
        playerService.savePlayer(sessionId, playerName, USER);
        Player player = playerService.getPlayer(sessionId, playerName);

         Message message = MessageCreator.createAddPlayerMessage(playerName);
         messageRepository.save(message);
         session.getPlayers().add(player);
         session.getMessages().add(message);

         sessionRepository.save(session);

        return player;
    }

    @Transactional
    public StartGameResultDTO startGame(String sessionId, String nextPlayer) {
        Session session = sessionCommonService.getSession(sessionId);
        List<BunkerCard> bunkerCards = bunkerCardRepository.getFiveRandomBunkerCards();
        session.setCurrentPlayer(nextPlayer);
        session.setBunkerCards(bunkerCards);
        session.setСatastrophe(catastropheRepository.getRandomCatastrophe());
        session.setState(IN_PROGRESS);
        this.setCharactersCards(session);
        Message message = MessageCreator.createStartGameMessage();
        messageRepository.save(message);
        session.getMessages().add(message);
        sessionRepository.saveAndFlush(session);
        StartGameResultDTO result = new StartGameResultDTO(IN_PROGRESS.toString(), nextPlayer);
        result.getBunkerCards().addAll(session.getBunkerCards());
        result.setCatastrophe(session.getСatastrophe());
        return result;
    }

    private void setCharactersCards(Session session) {
        List<Player> players = session.getPlayers();
        players.forEach(player -> {
            Character character = new Character();
            character.setId(UUID.randomUUID().toString());
            character.setBiology(commonCardRepository.getRandomBiology());
            character.setExtraSkills(commonCardRepository.getRandomExtraSkills());
            character.setHealth(commonCardRepository.getRandomHealth());
            character.setHobby(commonCardRepository.getRandomHobby());
            character.setHumanQualities(commonCardRepository.getRandomHumanQualities());
            character.setProfession(commonCardRepository.getRandomProfession());
            character.setPhobias(commonCardRepository.getRandomPhobias());
            character.setLuggage(commonCardRepository.getRandomLuggae());
            character.getSpecialCard().addAll(specialCardRepository.getTwoRandomSpecialCard());
            characterRepository.saveAndFlush(character);
            player.setCharacter(character);
            playerRepository.saveAndFlush(player);
            Votes votes = new Votes();
            votes.setPlayerName(player.getName());
            votes.setSessionId(session.getId());
            votes.setVotesNumber(0);
            voteRepository.saveAndFlush(votes);
        });
    }

    @Transactional
    public String getNextPlayer(String sessionId, String previousPLayer) {
        List<Player> players = sessionCommonService.getSession(sessionId)
                .getPlayers()
                .stream()
                .sorted(Comparator.comparing(Player::getId))
                .toList();
        int count = players.size();

        ArrayDeque<Player> deque = new ArrayDeque<>();
        int idx = 0;

        for (int i = 0; i < count; i++) {
            Player player = players.get(i);

            if (previousPLayer.equals(player.getName())) {
                idx = i;
                break;
            }

            deque.addLast(player);
        }

        for (int i = count - 1; i > idx; i--) {
            deque.addFirst(players.get(i));
        }

        Player nextPlayer = null;

        while (!deque.isEmpty()) {
            Player player = deque.pop();

            if (player.getStatus() == PLAYING) {
                nextPlayer = player;
                break;
            }
        }

        String nextPlayerName = nextPlayer.getName();
        Session session = sessionCommonService.getSession(sessionId);
        session.setCurrentPlayer(nextPlayerName);
        sessionRepository.saveAndFlush(session);
        return nextPlayerName;
    }

    @Transactional
    public KickedPlayerDTO kickOutPlayer(String sessionId, String playerName) {
        playerService.updatePlayerStatus(KICKED_OUT, sessionId, playerName);

        return PlayerMapper
                .surrenderPlayerToDTO(playerName, KICKED_OUT, playerService.getPlayerCards(sessionId, playerName));
    }

    @Transactional
    public PlayerCardsDTO getPlayerCards(String sessionId, String playerName) {
        final Character playerCards = playerService.getPlayerCards(sessionId, playerName);

        return PlayerMapper.characterToPlayerCardsDTO(playerCards);
    }
}
