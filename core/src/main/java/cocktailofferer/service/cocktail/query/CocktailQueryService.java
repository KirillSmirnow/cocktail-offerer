package cocktailofferer.service.cocktail.query;

import cocktailofferer.service.cocktail.Cocktail;

import java.util.List;

public interface CocktailQueryService {

    Cocktail getById(int id);

    List<Cocktail> getAll();
}
