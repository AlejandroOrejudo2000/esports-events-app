package es.urjc.dad.leaguesports.services;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class EmailService {

    private final String REGISTER_EMAIL_URL = "http://localhost:8080/email/registration";
    private final String EVENT_EMAIL_URL = "http://localhost:8080/email/event";


    public void SendRegisterEmail(String receiverEmail, String username){

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = getDefaultJSONheaders();

        Map<String, Object> httpBody = new HashMap<>();
        httpBody.put("receiver", receiverEmail);
        httpBody.put("username", username);
        
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(httpBody, httpHeaders);
        restTemplate.postForEntity(REGISTER_EMAIL_URL, entity, String.class);     
    }    

    public void SendEventEmail(String receiverEmail, String username, String tournamentName, long tournamentId){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = getDefaultJSONheaders();

        Map<String, Object> httpBody = new HashMap<>();
        httpBody.put("receiver", receiverEmail);
        httpBody.put("username", username);
        httpBody.put("eventName", tournamentName);
        httpBody.put("eventId", tournamentId);
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(httpBody, httpHeaders);
        restTemplate.postForEntity(EVENT_EMAIL_URL, entity, String.class);    
    }

    public HttpHeaders getDefaultJSONheaders(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return httpHeaders;
    }
}
