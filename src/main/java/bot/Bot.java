package bot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import services.ResponseService;
@Component
public class Bot extends TelegramLongPollingBot {
    @Value("${telegram.token}")
	private String token;
	private ResponseService service = new ResponseService();

	@Override
	public void onUpdateReceived(Update update) {

		if (update.hasMessage()) {
			Message message = update.getMessage();
			if (message.hasText()) {
				String responseText = service.getResponse(message.getText());
				SendMessage response = new SendMessage();
				response.setChatId(message.getChatId()).setText("(?i)"+responseText);
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
		return null;
	}

	@Override
	public String getBotToken() {
		return token;
	}

}