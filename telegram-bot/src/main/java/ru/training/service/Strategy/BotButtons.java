package ru.training.service.Strategy;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.training.DtoMuscleGet;
import ru.training.DtoMuscleGetAll;
import ru.training.DtoTrainingProgramGet;
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

    public SendMessage generateResponseOnButtonClick(Update update) {
        List<DtoMuscleGet> listOfMuscles = webClientController.getMuscle(update.getCallbackQuery().getData());
        String returnedText = checkIfTrainingExists(listOfMuscles);
        SendMessage message = new SendMessage();
        message.setChatId(update.getCallbackQuery().getMessage().getChatId());
        message.setText(returnedText);
        return message;
    }

    private String checkIfTrainingExists(List<DtoMuscleGet> listOfMuscles) {
        if (!listOfMuscles.get(0).getListOfTrainingPrograms().isEmpty()) {
            return String.format("Программа тренировок для мышцы: %s отсутствует", listOfMuscles.get(0).getMuscleName());
        } else {
            StringBuilder stringBuilder = new StringBuilder(String
                    .format("Список тренировок для мышцы: %s", listOfMuscles.get(0).getMuscleName()));
            for (DtoTrainingProgramGet dtoTrainingProgramGet : listOfMuscles.get(0).getListOfTrainingPrograms()) {
                stringBuilder.append("/n" + dtoTrainingProgramGet.getTrainingProgramName() + "" +
                        "/n" + dtoTrainingProgramGet.getTrainingProgramDescription());
            }
            return stringBuilder.toString();
        }
    }
}
