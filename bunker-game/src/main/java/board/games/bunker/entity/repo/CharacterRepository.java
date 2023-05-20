package board.games.bunker.entity.repo;

import board.games.bunker.entity.Character;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<Character,String> {
}
