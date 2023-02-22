package cocktailofferer.service.bot;

public interface BotMessageProcessor {

    void processTextMessage(String chatId, String text);

    void setBotInfrastructure(BotInfrastructure botInfrastructure);
}
