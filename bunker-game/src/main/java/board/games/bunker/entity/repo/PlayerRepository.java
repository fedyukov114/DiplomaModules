package board.games.bunker.entity.repo;

import board.games.bunker.entity.Player;
import board.games.bunker.entity.cards.charactercards.CommonCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player,String> {
    Optional<Player> findPlayerByNameAndSessionId(String name, String sessionId);
}
