package ru.training.service.Strategy;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.training.WebClientController;

import java.util.List;

@Component("/muscle")
@AllArgsConstructor
public class BotGetMuscle implements BotActions {
    private final WebClientController webClientController;

    @Override
    public SendMessage generatedMessage(Update update) {
        System.out.println(update.getMessage().getText());
        List<Object> listOfMuscles = webClientController.getMuscle(update.getMessage().getText());
        SendMessage message = new SendMessage();
        message.setChatId(update.getMessage().getChatId());
        message.setText(listOfMuscles.toString());
        return message;
    }
}
