package board.games.bunker.entity.cards.repo;

import board.games.bunker.entity.cards.BunkerCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BunkerCardRepository extends JpaRepository<BunkerCard, String> {
    @Query(value ="select * from bunker_card order by random() asc limit 5",nativeQuery = true)
    List<BunkerCard> getFiveRandomBunkerCards();

    @Query(value ="select * from bunker_card order by random() asc limit 1",nativeQuery = true)
    BunkerCard getRandomBunkerCard();

}
