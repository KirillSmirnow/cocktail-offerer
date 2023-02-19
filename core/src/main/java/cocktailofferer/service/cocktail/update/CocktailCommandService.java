package cocktailofferer.service.cocktail.update;

import cocktailofferer.service.Id;

public interface CocktailCommandService {

    Id<Integer> create(CocktailCreation creation);

    void update(int id, CocktailUpdate update);

    void delete(int id);

    Id<Integer> selectCocktailToCook();

    void resetCookSelection();
}
