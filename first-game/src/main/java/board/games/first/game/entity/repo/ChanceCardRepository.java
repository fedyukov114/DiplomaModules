package board.games.first.game.entity.repo;

import board.games.first.game.entity.ChanceCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChanceCardRepository extends JpaRepository<ChanceCard, String> {
}
