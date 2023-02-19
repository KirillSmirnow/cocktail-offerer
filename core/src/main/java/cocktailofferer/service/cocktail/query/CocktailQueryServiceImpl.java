package cocktailofferer.service.cocktail.query;

import cocktailofferer.service.cocktail.Cocktail;
import cocktailofferer.service.cocktail.CocktailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CocktailQueryServiceImpl implements CocktailQueryService {

    private final CocktailRepository cocktailRepository;

    @Override
    public Cocktail getById(int id) {
        return cocktailRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Cocktail> getAll() {
        return cocktailRepository.findAll(Sort.by("name"));
    }
}
