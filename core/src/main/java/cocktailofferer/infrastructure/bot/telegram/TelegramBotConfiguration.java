package cocktailofferer.infrastructure.bot.telegram;

import cocktailofferer.service.bot.BotMessageProcessor;
import cocktailofferer.service.bot.BotMessageProcessorImpl;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
public class TelegramBotConfiguration {

    @Bean
    @SneakyThrows
    public TelegramBot telegramBot(TelegramBotProperties properties) {
        var botMessageProcessor = telegramBotMessageProcessor();
        var bot = new TelegramBot(properties, botMessageProcessor);
        botMessageProcessor.setBotInfrastructure(bot);
        var telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(bot);
        return bot;
    }

    @Bean
    public BotMessageProcessor telegramBotMessageProcessor() {
        return new BotMessageProcessorImpl();
    }
}
