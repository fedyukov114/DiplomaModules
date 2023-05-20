package board.games.bunker.services;

import static board.games.bunker.params.ErrorMessage.PLAYER_NOT_FOUND;

import board.games.bunker.entity.Character;
import board.games.bunker.entity.Player;
import board.games.bunker.entity.repo.PlayerRepository;
import board.games.bunker.enums.PlayerRole;
import board.games.bunker.enums.PlayerStatus;
import board.games.bunker.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Transactional(readOnly = true)
    public Player getPlayer(String sessionId, String name) {
        return playerRepository.findPlayerByNameAndSessionId(name, sessionId)
                .orElseThrow(() -> new ResourceNotFoundException(PLAYER_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    @Transactional
    public void savePlayer(String sessionId, String playerName, PlayerRole role) {
        Player player = new Player();
        player.setName(playerName);
        player.setSessionId(sessionId);
        player.setRole(role);

        playerRepository.save(player);
    }

    @Transactional
    public void savePlayer(Player player) {
        playerRepository.save(player);
    }

    @Transactional
    public void updatePlayerStatus(PlayerStatus status, String sessionId, String playerName) {
        Player player = getPlayer(sessionId, playerName);
        player.setStatus(status);
        playerRepository.saveAndFlush(player);
    }

    @Transactional(readOnly = true)
    public Character getPlayerCards(String sessionId, String name) {
        return playerRepository.findPlayerByNameAndSessionId(name, sessionId)
                .orElseThrow(() -> new ResourceNotFoundException(PLAYER_NOT_FOUND))
                .getCharacter();
    }
}
