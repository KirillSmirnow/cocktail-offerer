package cocktailofferer.infrastructure.bot;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.List;

@Slf4j
public class CocktailOffererBot extends TelegramLongPollingBot {

    private final CocktailOffererBotProperties properties;
    private final CocktailOffererBotDependencies dependencies;

    public CocktailOffererBot(CocktailOffererBotProperties properties, CocktailOffererBotDependencies dependencies) {
        super(properties.getToken());
        this.properties = properties;
        this.dependencies = dependencies;
    }

    @Override
    public void onUpdateReceived(Update update) {
        var message = update.getMessage();
        if (message == null) {
            return;
        }
        var text = message.getText();
        if (text == null) {
            return;
        }
        var chatId = Long.toString(message.getChatId());
        if (!chatId.equals(properties.getPermittedChatId())) {
            log.info("Unpermitted chat {}", chatId);
            return;
        }
        processTextMessage(chatId, text);
    }

    private void processTextMessage(String chatId, String text) {
        if (text.equals("/start")) {
            dependencies.getCocktailCommandService().resetCookSelection();
            sendTextWithNextButton(chatId, "Click on Next button to get your next cocktail");
        } else if (text.equals("Next")) {
            try {
                var nextCocktailId = dependencies.getCocktailCommandService().selectCocktailToCook();
                var nextCocktail = dependencies.getCocktailQueryService().getById(nextCocktailId.getValue());
                sendTextWithNextButton(chatId, "Your next cocktail is <b>" + nextCocktail.getName() + "</b>");
            } catch (Exception e) {
                sendTextWithoutButtons(chatId, "All available cocktails have been cooked!");
            }
        }
    }

    @SneakyThrows
    private void sendTextWithNextButton(String chatId, String text) {
        var sendMessage = new SendMessage(chatId, text);
        sendMessage.enableHtml(true);
        sendMessage.setReplyMarkup(new ReplyKeyboardMarkup(List.of(
                new KeyboardRow(List.of(
                        new KeyboardButton("Next")
                ))
        )));
        execute(sendMessage);
    }

    @SneakyThrows
    private void sendTextWithoutButtons(String chatId, String text) {
        var sendMessage = new SendMessage(chatId, text);
        sendMessage.setReplyMarkup(new ReplyKeyboardRemove(true));
        execute(sendMessage);
    }

    @Override
    public String getBotUsername() {
        return properties.getUsername();
    }
}
