package services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;

public class ResponseService {

	private static final Map<String, List<String>> responses = new HashMap<String, List<String>>();

	public ResponseService() {
		initializeResponses();
	}

	public String getResponse(String userMessage) {
		userMessage = userMessage.toLowerCase();
		for (Map.Entry<String, List<String>> entry : responses.entrySet()) {
			if (entry.getValue().contains(userMessage)) {
				return entry.getKey();
			}
		}
		return "Não sei responder essa pergunta :P";
	}
	
	public void initializeResponses() {
		responses.put("ravel",  Lists.newArrayList("qual seu nome?", "como se chama?"));
		responses.put("21",  Lists.newArrayList("qual sua idade?", "quantos anos você tem?"));
	}

}