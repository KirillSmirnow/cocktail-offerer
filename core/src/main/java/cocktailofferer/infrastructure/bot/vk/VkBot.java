package cocktailofferer.infrastructure.bot.vk;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.model.events.messages.MessageNew;
import api.longpoll.bots.model.objects.additional.Keyboard;
import api.longpoll.bots.model.objects.additional.buttons.Button.Color;
import api.longpoll.bots.model.objects.additional.buttons.TextButton;
import api.longpoll.bots.model.objects.additional.buttons.TextButton.Action;
import cocktailofferer.service.bot.BotInfrastructure;
import cocktailofferer.service.bot.BotMessageProcessor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.util.List;

import static java.util.Collections.emptyList;

@RequiredArgsConstructor
public class VkBot extends LongPollBot implements BotInfrastructure {

    private final VkBotProperties properties;
    private final BotMessageProcessor botMessageProcessor;

    @Override
    public void onMessageNew(MessageNew messageNew) {
        var message = messageNew.getMessage();
        var text = message.getText();
        if (text == null) {
            return;
        }
        var chatId = Integer.toString(message.getPeerId());
        botMessageProcessor.processTextMessage(chatId, text);
    }

    @Override
    @SneakyThrows
    public void sendTextWithNextButton(String chatId, String text) {
        vk.messages.send()
                .setPeerId(Integer.parseInt(chatId))
                .setMessage(text)
                .setKeyboard(new Keyboard(List.of(
                        List.of(
                                new TextButton(Color.PRIMARY, new Action("Next"))
                        )
                )))
                .execute();
    }

    @Override
    @SneakyThrows
    public void sendTextWithoutButtons(String chatId, String text) {
        vk.messages.send()
                .setPeerId(Integer.parseInt(chatId))
                .setMessage(text)
                .setKeyboard(new Keyboard(emptyList()))
                .execute();
    }

    @Override
    public String getPermittedChatId() {
        return properties.getPermittedChatId();
    }

    @Override
    public String getAccessToken() {
        return properties.getToken();
    }
}
