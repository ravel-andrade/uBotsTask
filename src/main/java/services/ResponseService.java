package services;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.*;


public class ResponseService {

	private static final Map<String, List<String>> responses = new HashMap<String, List<String>>();

	public ResponseService() throws IOException, GeneralSecurityException {
		initializeResponses();
	}

	public String getResponse(String userMessage) throws GeneralSecurityException, IOException {
		String messageText = userMessage.toLowerCase();

		for (Map.Entry<String, List<String>> entry : responses.entrySet()) {

			if (entry.getValue().contains(messageText)) {
				return entry.getKey();
			}
		}
		return "Não sei responder essa pergunta :P";
	}

	private String getEvents() throws IOException, GeneralSecurityException {
		List<String> events = CalendarConnector.getWeekEvents();
		String response = "Eventos da semana: \n\n";
		for(String event : events){
			response= response + event + "\n";
		}
		return response;
	}

	public void initializeResponses() throws IOException, GeneralSecurityException {
		responses.put("ravel", Arrays.asList("qual seu nome?", "como se chama?"));
		responses.put("21",  Arrays.asList("qual sua idade?", "quantos anos você tem?"));
		responses.put("Não tenho apelido ;(",  Arrays.asList("qual seu apelido", "tem algum apelido?"));
		responses.put(getEvents(),  Arrays.asList("agenda"));

	}

}