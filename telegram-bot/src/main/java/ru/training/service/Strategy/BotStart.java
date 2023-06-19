package ru.training.service.Strategy;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component("/start")
public class BotStart implements BotActions {
    @Override
    public SendMessage generatedMessage(Update update) {
        String name = "Привет, " + update.getMessage().getChat().getFirstName() + " " +
                update.getMessage().getChat().getLastName() +
                "\nБот Muscle Trainer позволяет получить тренировочную программу по выбранной мышце." +
                " Для продолжения работы выберите /training из основного меню";
//get username:
//      update.getMessage().getChat().getUserName();*/
        SendMessage message = new SendMessage();
        message.setChatId(update.getMessage().getChatId());
        message.setText(name);
        return message;
    }
}
