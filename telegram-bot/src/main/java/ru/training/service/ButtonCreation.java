package ru.training.service;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.training.DtoMuscleGetAll;

import java.util.ArrayList;
import java.util.List;

import static ru.training.config.TelegramBotConstants.AMOUNT_OF_RAWS_FOR_BUTTONS;

public interface ButtonCreation {
    default InlineKeyboardMarkup createButtonsInLine(Update update, List<DtoMuscleGetAll> listOfMuscles) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();
        for (int i = 0, j = 1; i < listOfMuscles.size(); i++, j++) {
            InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
            inlineKeyboardButton.setText(listOfMuscles.get(i).getMuscleName());
            inlineKeyboardButton.setCallbackData(String.valueOf(listOfMuscles.get(i).getMuscleId()));
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
}
