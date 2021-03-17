package com.example.demo;

import bot.Properties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import bot.Bot;

import javax.annotation.Nonnull;


@SpringBootApplication
@Controller
public class UBotsTask1Application {

	public static void main(String[] args) {
		SpringApplication.run(UBotsTask1Application.class, args);
		initializeBot();

	}

	private static void initializeBot() {
		ApiContextInitializer.init();
		TelegramBotsApi telegramBotsApi = new TelegramBotsApi();;
		registerBot(telegramBotsApi);
	}

	private static void registerBot(TelegramBotsApi telegramBotsApi) {
		try {
			telegramBotsApi.registerBot(new Bot(new Properties()));
		} catch (TelegramApiException e) {

		}
	}

}