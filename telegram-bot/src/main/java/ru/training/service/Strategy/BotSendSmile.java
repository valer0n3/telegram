package ru.training.service.Strategy;

import com.vdurmont.emoji.EmojiParser;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component("/smile")
public class BotSendSmile implements BotActions {
    @Override
    public SendMessage generatedMessage(Update update) {
        String textWithSmile = EmojiParser.parseToUnicode("Text with smile " + ":football:");
        SendMessage message = new SendMessage();
        message.setChatId(update.getMessage().getChatId());
        message.setText(textWithSmile);
        return message;
    }
}
