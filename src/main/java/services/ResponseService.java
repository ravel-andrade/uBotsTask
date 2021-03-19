package services;

import jdk.nashorn.internal.runtime.regexp.joni.Regex;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.*;


public class ResponseService {

	private static final Map<String, String> responses = new HashMap<String, String>();

	public ResponseService() throws IOException, GeneralSecurityException {
		initializeResponses();
	}

	public String getResponse(String userMessage) {
		for (Map.Entry<String, String> entry : responses.entrySet()) {
			if (userMessage.toLowerCase(Locale.ROOT).matches(entry.getValue())) {
				if(entry.getKey().equals("Agenda")){
					try {
						return getEvents();
					} catch (IOException e) {
						return "Alguma coisa deu errado, tente novamente \n\n"+e.getMessage();
					} catch (GeneralSecurityException e) {
						return "Alguma coisa deu errado, tente novamente \n\n"+e.getMessage();
					}
				}
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
		responses.put("ravel", new String("(qual)+(.?)+(seu|teu)+(.?)+(nome)+(.?)+(\\?)"));
		responses.put("21", new String("(qual|quantos)+(.?)+(sua|teu|anos)+(.?)+(idade|você tem)+(.?)+(\\?)"));
		responses.put("DIO", new String("(qual|tem)+(.?)+(seu|teu|algum)+(.?)+(apelido)+(.?)+(\\?)"));
		responses.put("Agenda", new String("(como)+(.?)+(está|esta)+(.?)+(a minha|minha)+(.?)+(agenda)+(\\?)"));

	}

}