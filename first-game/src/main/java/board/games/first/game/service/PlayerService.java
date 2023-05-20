package board.games.first.game.service;

import board.games.first.game.entity.Player;
import board.games.first.game.entity.repo.PlayerRepository;
import board.games.first.game.enums.PlayerRole;
import board.games.first.game.enums.PlayerStatus;
import board.games.first.game.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static board.games.first.game.params.ErrorMessage.PLAYER_NOT_FOUND;
import static board.games.first.game.params.InitialGameValue.INITIAL_PLAYER_POSITION;

@Service
public class PlayerService{
    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Transactional(readOnly = true)
    public Player getPlayer(String sessionId, String name) {
        return playerRepository.findPlayerByNameAndSessionId(name, sessionId)
                .orElseThrow(() -> new ResourceNotFoundException(PLAYER_NOT_FOUND));
    }

    @Transactional
    public void savePlayer(String sessionId, String playerName, PlayerRole role) {
        Player player = new Player();
        player.setName(playerName);
        player.setSessionId(sessionId);
        player.setPosition(INITIAL_PLAYER_POSITION);
        player.setRole(role);

        playerRepository.save(player);
    }

    @Transactional
    public void updatePlayerPosition(int newPosition, String sessionId, String playerName) {
        Player player = getPlayer(sessionId,playerName);
        player.setPosition(newPosition);
        playerRepository.saveAndFlush(player);
    }

    @Transactional
    public void updatePlayerBalance(Long newBalance, String sessionId, String playerName) {
        Player player = getPlayer(sessionId,playerName);
        player.setBalance(newBalance);
        playerRepository.saveAndFlush(player);
    }

    @Transactional
    public void updatePlayerStatus(PlayerStatus status, String sessionId, String playerName) {
        Player player = getPlayer(sessionId,playerName);
        player.setStatus(status);
        playerRepository.saveAndFlush(player);
    }

}
