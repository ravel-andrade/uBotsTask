package bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import services.ResponseService;

import java.io.IOException;
import java.security.GeneralSecurityException;



public class Bot extends TelegramLongPollingBot {
	private ResponseService service;
	private String botName;
	private String token;


	public Bot(ResponseService service, String botName, String token) {
		this.service = service;
		this.botName = botName;
		this.token = token;
	}

	@Override
	public void onUpdateReceived(Update update) {

		if (update.hasMessage()) {
			Message message = update.getMessage();
			if (message.hasText()) {
				String responseText = null;

				try {
					responseText = service.getResponse(message.getText());
				} catch (GeneralSecurityException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

				SendMessage response = new SendMessage();
				response.setChatId(message.getChatId().toString());
				response.setText(responseText);
				sendResponse(response);
			}
		}
	}

	public void sendResponse(SendMessage message) {
		try {
			execute(message);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getBotUsername() {
		return this.botName;
	}

	@Override
	public String getBotToken() {
		return this.token;
	}

}