package board.games.bunker.entity.repo;

import board.games.bunker.entity.Votes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VoteRepository extends JpaRepository<Votes, String> {

    Optional<Votes> findVotesByPlayerNameAndSessionId(String name, String sessionId);

    List<Votes> getAllBySessionId(String sessionId);
}