package board.games.first.game.entity.repo;

import board.games.first.game.entity.Player;
import board.games.first.game.enums.PlayerStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, String> {
    Optional<Player> findPlayerByNameAndSessionId(String name,String sessionId);
}
