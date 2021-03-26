package services;

import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import services.datastructure.EventData;
import services.datastructure.ResponseData;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.*;


public class ResponseService {
	List<ResponseData> responseData = new ArrayList<ResponseData>();

	public ResponseService()  {
		initializeResponses();
	}

	public String getResponse(String userMessage) {
		for (ResponseData entry : responseData) {
			if (userMessage.toLowerCase(Locale.ROOT).matches(entry.getRegex())){
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
		List<EventData> events = CalendarConnector.getWeekEvents();
		String response = "Eventos da semana: \n\n";
		for(EventData event : events){
			System.out.println(event.getTitle());
			response= response +" "+ event.getTitle() + event.getDate() + "\n";
		}
		return response;
	}

	public void initializeResponses()  { // transformar em uma estrutura de dados própria @TODO
		responseData.add(new ResponseData("ravel","(qual)+(.?)+(seu|teu)+(.?)+(nome)+(.?)+(\\?)"));
		responseData.add(new ResponseData("21","(qual|quantos)+(.?)+(sua|teu|anos)+(.?)+(idade|você tem)+(.?)+(\\?)"));
		responseData.add(new ResponseData("DIO", "(qual|tem)+(.?)+(seu|teu|algum)+(.?)+(apelido)+(.?)+(\\?)"));
		responseData.add(new ResponseData("Agenda", "(como)+(.?)+(está|esta)+(.?)+(a minha|minha)+(.?)+(agenda)+(\\?)"));

	}

}