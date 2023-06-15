package ru.training.service.Strategy;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.training.DtoMuscleGet;
import ru.training.DtoMuscleGetAll;
import ru.training.WebClientController;

import java.util.ArrayList;
import java.util.List;

import static ru.training.config.TelegramBotConstants.AMOUNT_OF_RAWS_FOR_BUTTONS;

@Component("/buttons")
@AllArgsConstructor
public class BotButtons implements BotActions {
    private final WebClientController webClientController;

    @Override
    public SendMessage generatedMessage(Update update) {
        List<DtoMuscleGetAll> listOfMuscles = webClientController.getAllMuscles();
        SendMessage message = new SendMessage();
        message.setChatId(update.getMessage().getChatId());
        message.setText("Выберите название мышцы");
        message.setReplyMarkup(createButtonsInLIne(update, listOfMuscles));
        return message;
    }

    private InlineKeyboardMarkup createButtonsInLIne(Update update, List<DtoMuscleGetAll> listOfMuscles) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();
        for (int i = 0, j = 1; i < listOfMuscles.size(); i++, j++) {
            InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
            inlineKeyboardButton.setText(listOfMuscles.get(i).getMuscleName());
            inlineKeyboardButton.setCallbackData(listOfMuscles.get(i).getMuscleName());//TODO change to ID
            rowInLine.add(inlineKeyboardButton);
            if (j % AMOUNT_OF_RAWS_FOR_BUTTONS == 0) {
                rowsInLine.add(rowInLine);
                rowInLine = new ArrayList<>();
            }
        }
        rowsInLine.add(rowInLine);
        inlineKeyboardMarkup.setKeyboard(rowsInLine);
        return inlineKeyboardMarkup;
    }
//TODO Liquibase, Flyway
    public SendMessage generateResponseOnButtonClick(Update update) {
        List<DtoMuscleGet> listOfMuscles = webClientController.getMuscle(update.getCallbackQuery().getData());
        SendMessage message = new SendMessage();
        message.setChatId(update.getCallbackQuery().getMessage().getChatId());
        message.setText(listOfMuscles.toString());
        return message;
    }
}
