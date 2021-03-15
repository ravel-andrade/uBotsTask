package com.example.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import bot.Bot;

@SpringBootApplication
public class UBotsTask1Application {

	public static void main(String[] args) {
		initializeBot();
	}
	
	private static void initializeBot() {
		ApiContextInitializer.init();
		TelegramBotsApi telegramBotsApi = new TelegramBotsApi();;
		registerBot(telegramBotsApi);
	}

	private static void registerBot(TelegramBotsApi telegramBotsApi) {
		try {
			telegramBotsApi.registerBot(new Bot());
		} catch (TelegramApiException e) {

		}
	}

}