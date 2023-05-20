package board.games.first.game.entity.repo;

import board.games.first.game.entity.CompanyCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyCardRepository extends JpaRepository<CompanyCard, String> {
}
