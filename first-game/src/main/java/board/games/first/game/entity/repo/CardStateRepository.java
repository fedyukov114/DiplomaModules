package board.games.first.game.entity.repo;

import board.games.first.game.entity.CardState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardStateRepository extends JpaRepository<CardState, String> {
    List<CardState> findByCardIdIn(List<String> cardIds);
}
