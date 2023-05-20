package board.games.bunker.entity.cards.charactercards.repo;

import board.games.bunker.entity.cards.charactercards.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommonCardRepository extends JpaRepository<CommonCard, String> {

    @Query(value = "select * from common_card where common_card.dtype='Biology' order by random() asc limit 1",
            nativeQuery = true)
    Biology getRandomBiology();

    @Query(value = "select * from common_card  where common_card.dtype='ExtraSkills' order by random() asc limit 1",
            nativeQuery = true)
    ExtraSkills getRandomExtraSkills();

    @Query(value = "select * from common_card  where common_card.dtype='Health' order by random() asc limit 1",
            nativeQuery = true)
    Health getRandomHealth();

    @Query(value = "select * from common_card  where common_card.dtype='Hobby' order by random() asc limit 1",
            nativeQuery = true)
    Hobby getRandomHobby();

    @Query(value = "select * from common_card  where common_card.dtype='HumanQualities' order by random() asc limit 1",
            nativeQuery = true)
    HumanQualities getRandomHumanQualities();

    @Query(value = "select * from common_card  where common_card.dtype='Luggage' order by random() asc limit 1",
            nativeQuery = true)
    Luggage getRandomLuggae();

    @Query(value = "select * from common_card  where common_card.dtype='Phobias' order by random() asc limit 1",
            nativeQuery = true)
    Phobias getRandomPhobias();

    @Query(value = "select * from common_card  where common_card.dtype='Profession' order by random() asc limit 1",
            nativeQuery = true)
    Profession getRandomProfession();
}
