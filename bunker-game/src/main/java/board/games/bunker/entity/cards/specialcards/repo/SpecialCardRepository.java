package board.games.bunker.entity.cards.specialcards.repo;

import board.games.bunker.entity.cards.specialcards.SpecialCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SpecialCardRepository extends JpaRepository<SpecialCard, String> {

    @Query(value = "select * from special_card order by random() asc limit 1", nativeQuery = true)
    SpecialCard getRandomSpecialCard();

    @Query(value = "select * from special_card order by random() asc limit 2", nativeQuery = true)
    List<SpecialCard> getTwoRandomSpecialCard();
}
