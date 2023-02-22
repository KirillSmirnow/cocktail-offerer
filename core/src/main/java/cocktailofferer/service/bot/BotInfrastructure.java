package cocktailofferer.service.bot;

public interface BotInfrastructure {

    String getPermittedChatId();

    void sendTextWithNextButton(String chatId, String text);

    void sendTextWithoutButtons(String chatId, String text);
}
