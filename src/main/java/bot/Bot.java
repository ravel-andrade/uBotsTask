package bot;

import com.example.demo.UBotsTask1Application;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import services.ResponseService;

import javax.annotation.Nonnull;

@SpringBootApplication
@Component
public class Bot extends TelegramLongPollingBot{
	private ResponseService service = new ResponseService();

	private final Properties properties;

	@Autowired
	public Bot(Properties properties) {
		this.properties = properties;
	}




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
		return properties.getBotName();
	}

	@Override
	public String getBotToken() {
		return properties.getToken();
	}

}