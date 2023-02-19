package cocktailofferer.infrastructure.restcontroller;

import cocktailofferer.service.cocktail.Cocktail;
import cocktailofferer.service.cocktail.query.CocktailQueryService;
import cocktailofferer.service.cocktail.update.CocktailCommandService;
import cocktailofferer.service.cocktail.update.CocktailCreation;
import cocktailofferer.service.cocktail.update.CocktailUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cocktails")
@RequiredArgsConstructor
public class CocktailController {

    private final CocktailQueryService cocktailQueryService;
    private final CocktailCommandService cocktailCommandService;

    @GetMapping
    public List<Cocktail> getAll() {
        return cocktailQueryService.getAll();
    }

    @PostMapping
    public Cocktail create(@RequestBody CocktailCreation creation) {
        var id = cocktailCommandService.create(creation);
        return cocktailQueryService.getById(id.getValue());
    }

    @PutMapping("/{id}")
    public Cocktail update(@PathVariable int id, @RequestBody CocktailUpdate update) {
        cocktailCommandService.update(id, update);
        return cocktailQueryService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        cocktailCommandService.delete(id);
    }
}
