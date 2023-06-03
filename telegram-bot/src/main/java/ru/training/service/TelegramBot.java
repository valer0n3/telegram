package ru.training.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.training.config.BotConfig;

@Component
@AllArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {
    private final BotConfig botConfig;

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            //   long chatId = update.getMessage().getChatId();
            switch (update.getMessage().getText()) {
                case "/start":
                    startCommandRecieved(update);
                    break;
            }
            //  System.out.println(update.getMessage().getText());
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
        } catch (TelegramApiException e) {
            e.getMessage();
        }
    }

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }
}
