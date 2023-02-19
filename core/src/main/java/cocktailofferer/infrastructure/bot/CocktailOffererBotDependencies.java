package cocktailofferer.infrastructure.bot;

import cocktailofferer.service.cocktail.query.CocktailQueryService;
import cocktailofferer.service.cocktail.update.CocktailCommandService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Getter
public class CocktailOffererBotDependencies {
    private final CocktailQueryService cocktailQueryService;
    private final CocktailCommandService cocktailCommandService;
}
