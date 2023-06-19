package ru.training.service.Strategy;

import com.vdurmont.emoji.EmojiParser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
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

    public EditMessageText generateResponseOnButtonClick(Update update) {
        List<DtoMuscleGet> listOfMuscles = webClientController.getMuscle(Long.valueOf(update.getCallbackQuery().getData()));
        EditMessageText editMessageText = new EditMessageText();
        editMessageText.setChatId(update.getCallbackQuery().getMessage().getChatId());
        editMessageText.setMessageId(update.getCallbackQuery().getMessage().getMessageId());
        editMessageText.setText(checkIfTrainingExists(listOfMuscles));
        return editMessageText;

      /*  System.out.println("YES");

        editMessageText.setChatId(chatId);
        editMessageText.setText("You pressed yes");
        editMessageText.setMessageId(messageId);
        return editMessageText;*/
    }

    private String checkIfTrainingExists(List<DtoMuscleGet> listOfMuscles) {
        if (listOfMuscles.get(0).getListOfTrainingPrograms().isEmpty()) {
            return String.format("Программа тренировок для мышцы: %s отсутствует", listOfMuscles.get(0).getMuscleName());
        } else {
            StringBuilder stringBuilder = new StringBuilder(String
                    .format("Список тренировок для мышцы: %s %s", listOfMuscles.get(0).getMuscleName(),
                            EmojiParser.parseToUnicode(":muscle:")));
            for (DtoTrainingProgramGet dtoTrainingProgramGet : listOfMuscles.get(0).getListOfTrainingPrograms()) {
                stringBuilder.append("\n---------------------\n" + dtoTrainingProgramGet.getTrainingProgramName() + ":" +
                        "\n" + dtoTrainingProgramGet.getTrainingProgramDescription());
            }
            return stringBuilder.toString();
        }
    }
}
