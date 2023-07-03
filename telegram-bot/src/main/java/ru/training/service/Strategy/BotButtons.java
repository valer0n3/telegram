package ru.training.service.Strategy;

import com.vdurmont.emoji.EmojiParser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import ru.training.DtoMuscleGet;
import ru.training.DtoMuscleGetAll;
import ru.training.DtoTrainingProgramGet;
import ru.training.WebClientController;
import ru.training.service.ButtonCreation;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static ru.training.config.TelegramBotConstants.AMOUNT_OF_RAWS_FOR_BUTTONS;

@Component("/training")
@AllArgsConstructor
@Validated
public class BotButtons implements BotActions, ButtonCreation {
    private final WebClientController webClientController;

    public InlineKeyboardMarkup getButtonsAndNameThem(List<DtoMuscleGetAll> listOfMuscles) {
        InlineKeyboardMarkup inlineKeyboardMarkup = ButtonCreation.super.createButtonsInLine(listOfMuscles.size(), AMOUNT_OF_RAWS_FOR_BUTTONS);
        addTextAndCallBackDataToButtons(inlineKeyboardMarkup, listOfMuscles);
        return inlineKeyboardMarkup;
    }

    private void addTextAndCallBackDataToButtons(InlineKeyboardMarkup inlineKeyboardMarkup, List<DtoMuscleGetAll> listOfMuscles) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        inlineKeyboardMarkup.getKeyboard().stream()
                .flatMap(inlineKeyboardButtons -> inlineKeyboardButtons.stream())
                .forEach(inlineKeyboardButton -> {
                    inlineKeyboardButton.setText(listOfMuscles.get(atomicInteger.get()).getMuscleName());
                    inlineKeyboardButton.setCallbackData(String.valueOf(listOfMuscles.get(atomicInteger.get()).getMuscleId()));
                    atomicInteger.getAndIncrement();
                });
    }

    @Override
    public SendMessage generatedMessage(Update update) {
        List<DtoMuscleGetAll> listOfMuscles = webClientController.getAllMuscles();
        listOfMuscles.sort(Comparator.comparing(DtoMuscleGetAll::getMuscleName));
        SendMessage message = new SendMessage();
        message.setChatId(update.getMessage().getChatId());
        message.setText("Выберите название мышцы");
        message.setReplyMarkup(getButtonsAndNameThem(listOfMuscles));
        return message;
    }

    public EditMessageText generateResponseOnButtonClick(Update update) {
        List<DtoMuscleGet> listOfMuscles = webClientController.getMuscle(Long.valueOf(update.getCallbackQuery().getData()));
        EditMessageText editMessageText = new EditMessageText();
        editMessageText.setChatId(update.getCallbackQuery().getMessage().getChatId());
        editMessageText.setMessageId(update.getCallbackQuery().getMessage().getMessageId());
        editMessageText.setText(checkIfTrainingExists(listOfMuscles));
        return editMessageText;
    }

    public String checkIfTrainingExists(List<DtoMuscleGet> listOfMuscles) {
        if (listOfMuscles == null || listOfMuscles.isEmpty() || listOfMuscles.get(0).getListOfTrainingPrograms() == null
                || listOfMuscles.get(0)
                .getListOfTrainingPrograms().isEmpty()) {
            return "Программа тренировок отсутствует";
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
