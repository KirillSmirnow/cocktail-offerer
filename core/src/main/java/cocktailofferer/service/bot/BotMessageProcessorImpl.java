package cocktailofferer.service.bot;

import cocktailofferer.service.cocktail.query.CocktailQueryService;
import cocktailofferer.service.cocktail.update.CocktailCommandService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

public class BotMessageProcessorImpl implements BotMessageProcessor {

    @Autowired
    private CocktailCommandService cocktailCommandService;

    @Autowired
    private CocktailQueryService cocktailQueryService;

    @Setter
    private BotInfrastructure botInfrastructure;

    @Override
    public void processTextMessage(String chatId, String text) {
        if (!isChatPermitted(chatId)) {
            return;
        }
        if (text.equals("/start")) {
            processStartCommand(chatId);
        } else if (text.equals("Next")) {
            try {
                processNextCommand(chatId);
            } catch (Exception e) {
                replyWithTerminationMessage(chatId);
            }
        }
    }

    private boolean isChatPermitted(String chatId) {
        var permittedChatId = botInfrastructure.getPermittedChatId();
        return chatId.equals(permittedChatId);
    }

    private void processStartCommand(String chatId) {
        cocktailCommandService.resetCookSelection();
        botInfrastructure.sendTextWithNextButton(chatId, "Click on Next button to get your next cocktail");
    }

    private void processNextCommand(String chatId) {
        var nextCocktailId = cocktailCommandService.selectCocktailToCook();
        var nextCocktail = cocktailQueryService.getById(nextCocktailId.getValue());
        botInfrastructure.sendTextWithNextButton(chatId, "Your next cocktail is <b>" + nextCocktail.getName() + "</b>");
    }

    private void replyWithTerminationMessage(String chatId) {
        botInfrastructure.sendTextWithoutButtons(chatId, "All available cocktails have been cooked!");
    }
}
