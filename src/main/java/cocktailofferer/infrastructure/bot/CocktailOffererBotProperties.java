package cocktailofferer.infrastructure.bot;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("cocktail-offerer.bot")
public class CocktailOffererBotProperties {
    private String username;
    private String token;
    private String permittedChatId;
}
