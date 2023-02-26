package cocktailofferer.service.cocktail.query;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;

import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class CocktailQueryServiceTest {

    @Autowired
    private CocktailQueryService cocktailQueryService;

    @Test
    public void whenGetCocktailByNonexistentId_thenException() {
        assertThrows(Exception.class, () -> cocktailQueryService.getById(33));
    }

    @TestConfiguration
    @ComponentScan
    public static class Configuration {
    }
}
