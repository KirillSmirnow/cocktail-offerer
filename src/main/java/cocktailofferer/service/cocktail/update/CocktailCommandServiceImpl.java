package cocktailofferer.service.cocktail.update;

import cocktailofferer.service.Id;
import cocktailofferer.service.cocktail.Cocktail;
import cocktailofferer.service.cocktail.CocktailRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ThreadLocalRandom;

@Service
@Transactional
@RequiredArgsConstructor
public class CocktailCommandServiceImpl implements CocktailCommandService {

    private final CocktailRepository cocktailRepository;
    private final EntityManager entityManager;

    @Override
    public Id<Integer> create(CocktailCreation creation) {
        var cocktail = new Cocktail(creation.getName());
        cocktail.setAvailable(creation.isAvailable());
        cocktail = cocktailRepository.save(cocktail);
        return new Id<>(cocktail.getId());
    }

    @Override
    public void update(int id, CocktailUpdate update) {
        var cocktail = cocktailRepository.findById(id).orElseThrow();
        cocktail.setName(update.getName());
        cocktail.setAvailable(update.isAvailable());
    }

    @Override
    public void delete(int id) {
        cocktailRepository.deleteById(id);
    }

    @Override
    public Id<Integer> selectCocktailToCook() {
        var notCookedCocktailsCount = (int) (long) entityManager.createQuery(
                        "SELECT count(c) FROM Cocktail c WHERE c.available AND NOT c.cooked", Long.class
                )
                .getSingleResult();
        var cocktailIndex = ThreadLocalRandom.current().nextInt(notCookedCocktailsCount);
        var cocktail = entityManager.createQuery(
                        "SELECT c FROM Cocktail c WHERE c.available AND NOT c.cooked", Cocktail.class
                )
                .setFirstResult(cocktailIndex)
                .setMaxResults(1)
                .getSingleResult();
        cocktail.setCooked(true);
        return new Id<>(cocktail.getId());
    }

    @Override
    public void resetCookSelection() {
        cocktailRepository.resetCookSelection();
    }
}
