package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.telegram.telegrambots.meta.TelegramBotsApi;

import bot.Bot;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.BotSession;
import org.telegram.telegrambots.meta.generics.LongPollingBot;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import services.ResponseService;

import java.io.IOException;
import java.security.GeneralSecurityException;


@SpringBootApplication
public class UBotsTask1Application {



    public static void main(String[] args) {
        SpringApplication.run(UBotsTask1Application.class, args);

    }


    @Bean
    public Bot bot(ResponseService responseService,
                   @Value("${telegram.botname}") String botName,
                   @Value("${telegram.token}") String token) {
        return new Bot(responseService, botName, token);
    }

    @Bean
    public ResponseService responseService() throws IOException, GeneralSecurityException {
        return new ResponseService();
    }

    @Bean
    public BotSession botSession(LongPollingBot bot) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            return telegramBotsApi.registerBot(bot);

    }

}