package board.games.first.game.entity.repo;

import board.games.first.game.entity.CommonCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommonCardRepository extends JpaRepository<CommonCard, String> {
}
