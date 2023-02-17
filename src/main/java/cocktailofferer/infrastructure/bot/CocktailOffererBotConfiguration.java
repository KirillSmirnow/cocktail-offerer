package cocktailofferer.infrastructure.bot;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
public class CocktailOffererBotConfiguration {

    @Bean
    @SneakyThrows
    public CocktailOffererBot cocktailOffererBot(
            CocktailOffererBotProperties properties,
            CocktailOffererBotDependencies dependencies
    ) {
        var telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        var bot = new CocktailOffererBot(properties, dependencies);
        telegramBotsApi.registerBot(bot);
        return bot;
    }
}
