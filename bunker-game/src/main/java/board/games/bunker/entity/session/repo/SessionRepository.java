package board.games.bunker.entity.session.repo;

import board.games.bunker.entity.session.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session, String> {

    boolean existsSessionById(String id);
}
