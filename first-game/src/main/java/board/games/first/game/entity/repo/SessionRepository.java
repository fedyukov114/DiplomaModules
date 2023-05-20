package board.games.first.game.entity.repo;

import board.games.first.game.entity.session.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session, String> {

    boolean existsSessionById(String id);
}
