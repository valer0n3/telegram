package ru.training.service;

import com.vdurmont.emoji.EmojiParser;
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

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class TelegramBot extends TelegramLongPollingBot {
    private final BotConfig botConfig;

    @Autowired
    public TelegramBot(@Value("${bot.token}") String botToken, BotConfig botConfig) {
        super(botToken);
        this.botConfig = botConfig;
        List<BotCommand> listOfBotCommands = new ArrayList<>();
        listOfBotCommands.add(new BotCommand("/start", "do start"));
        listOfBotCommands.add(new BotCommand("/testsmile", "bot will send a text with a smile"));
        listOfBotCommands.add(new BotCommand("/muscle", "bot will provide a training program for a muscle"));
        try {
            execute(new SetMyCommands(listOfBotCommands, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException telegramApiException) {
            log.error("Couldn't create bot's menu: {}", telegramApiException.getMessage());
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            switch (update.getMessage().getText()) {
                case "/start":
                    startCommandRecieved(update);
                    break;
                case "/testsmile":
                    sendSmile(update);
                    break;
                case "/muscle":
                    recieveMuscleType(update);
                    break;
                default:
                    getTrainingProgram(update);
                    break;
            }
        }
    }

    private void getTrainingProgram(Update update) {
        System.out.println(update.getMessage().getText());

    }

    private void recieveMuscleType(Update update) {
        String text = "Введите название мышцы";
        SendMessage message = new SendMessage();
        message.setChatId(update.getMessage().getChatId());
        message.setText(text);
        try {
            execute(message);
        } catch (TelegramApiException telegramApiException) {
            log.error("Message was not sent: {}", telegramApiException.getMessage());
        }
    }

    private void sendSmile(Update update) {
        String textWithSmile = EmojiParser.parseToUnicode("Text with smile " + ":football:");
        SendMessage message = new SendMessage();
        message.setChatId(update.getMessage().getChatId());
        message.setText(textWithSmile);
        try {
            execute(message);
        } catch (TelegramApiException telegramApiException) {
            log.error("Message was not sent: {}", telegramApiException.getMessage());
        }
    }

    private void startCommandRecieved(Update update) {
        long chatId = update.getMessage().getChatId();
        long messageId = update.getUpdateId();
        String name = update.getMessage().getChat().getFirstName() + "/n" +
                update.getMessage().getChat().getLastName() + "/n" +
                update.getMessage().getChat().getUserName();
        sendMessage(chatId, name);
        System.out.println(String.format("chatId: %d, n messageId: %d. /n name = %s", chatId, messageId, name));
    }

    private void sendMessage(long chatId, String name) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(name);
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

/*    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }*/
}
