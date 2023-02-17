package cocktailofferer.service.cocktail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CocktailRepository extends JpaRepository<Cocktail, Integer> {

    @Modifying
    @Query("UPDATE Cocktail c SET c.cooked = false")
    void resetCookSelection();
}
