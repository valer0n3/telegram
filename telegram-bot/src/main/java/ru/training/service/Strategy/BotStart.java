package ru.training.service.Strategy;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component("/start")
public class BotStart implements BotActions {
    @Override
    public SendMessage generatedMessage(Update update) {
        String name = update.getMessage().getChat().getFirstName() + "/n" +
                update.getMessage().getChat().getLastName() + "/n" +
                update.getMessage().getChat().getUserName();
        SendMessage message = new SendMessage();
        message.setChatId(update.getMessage().getChatId());
        message.setText(name);
        return message;
    }
}
