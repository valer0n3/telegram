package ru.training.service.Strategy;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface BotActions {
    SendMessage generatedMessage(Update update);
}
