package board.games.bunker.entity.cards.repo;

import board.games.bunker.entity.cards.Catastrophe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CatastropheRepository extends JpaRepository<Catastrophe, String> {
    @Query(value ="select * from catastrophe order by random() asc limit 1",nativeQuery = true)
    Catastrophe getRandomCatastrophe();
}
