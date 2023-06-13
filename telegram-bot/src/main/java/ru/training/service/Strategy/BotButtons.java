package ru.training.service.Strategy;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.training.DtoMuscleGetAll;
import ru.training.WebClientController;

import java.util.ArrayList;
import java.util.List;

@Component("/buttons")
@AllArgsConstructor
public class BotButtons implements BotActions {
    private final WebClientController webClientController;

    @Override
    public SendMessage generatedMessage(Update update) {
        List<DtoMuscleGetAll> listOfMuscles = webClientController.getAllMuscles();
        System.out.println(listOfMuscles);
        SendMessage message = new SendMessage();
        message.setChatId(update.getMessage().getChatId());
        message.setText("Really?");
        message.setReplyMarkup(createButtonsInLIne(update, listOfMuscles));
        return message;
    }

    private InlineKeyboardMarkup createButtonsInLIne(Update update, List<DtoMuscleGetAll> listOfMuscles) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();
        for (int i = 0, j = 1; i < listOfMuscles.size(); i++, j++) {
            InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
            inlineKeyboardButton.setText(listOfMuscles.get(i).toString());
            inlineKeyboardButton.setCallbackData(listOfMuscles.get(i).toString());
            rowInLine.add(inlineKeyboardButton);
            if (j % 4 == 0) {
                rowsInLine.add(rowInLine);
                rowInLine = new ArrayList<>();
            }
        }
        rowsInLine.add(rowInLine);
        inlineKeyboardMarkup.setKeyboard(rowsInLine);
        return inlineKeyboardMarkup;
    }

    public EditMessageText generateResponseOnButtonClick(Update update) {
        String callBackDate = update.getCallbackQuery().getData();
        int messageId = update.getCallbackQuery().getMessage().getMessageId();
        long chatId = update.getCallbackQuery().getMessage().getChatId();
        //edit message example:
/*        if (callBackDate.equals("YES_BUTTON")) {
            System.out.println("YES");
            EditMessageText editMessageText = new EditMessageText();
            editMessageText.setChatId(chatId);
            editMessageText.setText("You pressed yes");
            editMessageText.setMessageId(messageId);
            return editMessageText;
        } else {
            System.out.println("NO");
        }*/
        return null;
    }
}
