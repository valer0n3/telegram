package ru.training.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.training.config.BotConfig;
import ru.training.service.Strategy.BotActions;
import ru.training.service.Strategy.BotButtons;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class TelegramBot extends TelegramLongPollingBot {
    private final BotConfig botConfig;
    @Autowired
    private Map<String, BotActions> mapBotAction;

    @Autowired
    public TelegramBot(@Value("${bot.token}") String botToken, BotConfig botConfig) {
        super(botToken);
        this.botConfig = botConfig;
        List<BotCommand> listOfBotCommands = new ArrayList<>();
        listOfBotCommands.add(new BotCommand("/start", "do start"));
        listOfBotCommands.add(new BotCommand("/smile", "bot will send a text with a smile"));
        listOfBotCommands.add(new BotCommand("/muscle", "bot will provide a training program for a muscle"));
        listOfBotCommands.add(new BotCommand("/buttons", "bot will provide all muscles"));
        try {
            execute(new SetMyCommands(listOfBotCommands, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException telegramApiException) {
            log.error("Couldn't create bot's menu: {}", telegramApiException.getMessage());
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String code = update.getMessage().getText();
            BotActions botActions = mapBotAction.get(code);
            if (botActions == null) {
                sendNotSupportedMessage(update);
                throw new UnsupportedOperationException(code + " command not supported");
            }
            SendMessage message = botActions.generatedMessage(update);
            sendMessageToTelegram(message);
        } else if (update.hasCallbackQuery()) {
            BotButtons botButtons = (BotButtons) mapBotAction.get("/buttons");
            SendMessage message = botButtons.generateResponseOnButtonClick(update);
            sendMessageToTelegram(message);
        }
    }

    private void sendNotSupportedMessage(Update update) {
        SendMessage message = new SendMessage();
        message.setChatId(update.getMessage().getChatId());
        message.setText("This command is not supported!");
        try {
            execute(message);
        } catch (TelegramApiException telegramApiException) {
            log.error("Message was not sent: {}", telegramApiException.getMessage());
        }
    }

    private void sendMessageToTelegram(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException telegramApiException) {
            log.error("Message was not sent: {}", telegramApiException.getMessage());
        }
    }

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }
}
