package ru.training.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Component
@Getter
@Setter
public class BotConfig {
    private String botName;
    private String token;

    public BotConfig() throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("telegram-bot\\src\\main\\resources\\bot.properties");
        properties.load(fileInputStream);
        botName = properties.getProperty("bot.name");
        token = properties.getProperty("bot.token");
    }
}
