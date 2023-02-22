package cocktailofferer.infrastructure.bot.telegram;

import cocktailofferer.service.bot.BotInfrastructure;
import cocktailofferer.service.bot.BotMessageProcessor;
import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.List;

public class TelegramBot extends TelegramLongPollingBot implements BotInfrastructure {

    private final TelegramBotProperties properties;
    private final BotMessageProcessor botMessageProcessor;

    public TelegramBot(TelegramBotProperties properties, BotMessageProcessor botMessageProcessor) {
        super(properties.getToken());
        this.properties = properties;
        this.botMessageProcessor = botMessageProcessor;
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
        botMessageProcessor.processTextMessage(chatId, text);
    }

    @Override
    @SneakyThrows
    public void sendTextWithNextButton(String chatId, String text) {
        var sendMessage = new SendMessage(chatId, text);
        sendMessage.enableHtml(true);
        sendMessage.setReplyMarkup(new ReplyKeyboardMarkup(List.of(
                new KeyboardRow(List.of(
                        new KeyboardButton("Next")
                ))
        )));
        execute(sendMessage);
    }

    @Override
    @SneakyThrows
    public void sendTextWithoutButtons(String chatId, String text) {
        var sendMessage = new SendMessage(chatId, text);
        sendMessage.setReplyMarkup(new ReplyKeyboardRemove(true));
        execute(sendMessage);
    }

    @Override
    public String getPermittedChatId() {
        return properties.getPermittedChatId();
    }

    @Override
    public String getBotUsername() {
        return properties.getUsername();
    }
}
