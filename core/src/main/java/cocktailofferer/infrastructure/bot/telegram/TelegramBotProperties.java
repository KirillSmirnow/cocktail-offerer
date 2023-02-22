package cocktailofferer.infrastructure.bot.telegram;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("cocktail-offerer.bot.telegram")
public class TelegramBotProperties {
    private String username;
    private String token;
    private String permittedChatId;
}
