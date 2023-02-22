package cocktailofferer.infrastructure.bot.vk;

import api.longpoll.bots.exceptions.VkApiException;
import cocktailofferer.service.bot.BotMessageProcessor;
import cocktailofferer.service.bot.BotMessageProcessorImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VkBotConfiguration {

    @Bean
    public VkBot vkBot(VkBotProperties properties) {
        var botMessageProcessor = vkBotMessageProcessor();
        var bot = new VkBot(properties, botMessageProcessor);
        botMessageProcessor.setBotInfrastructure(bot);
        launchBot(bot);
        return bot;
    }

    @SuppressWarnings({"PMD.DoNotUseThreads", "PMD.AvoidThrowingRawExceptionTypes"})
    private void launchBot(VkBot bot) {
        new Thread(() -> {
            try {
                bot.startPolling();
            } catch (VkApiException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    @Bean
    public BotMessageProcessor vkBotMessageProcessor() {
        return new BotMessageProcessorImpl();
    }
}
