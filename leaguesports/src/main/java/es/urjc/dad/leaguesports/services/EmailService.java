package es.urjc.dad.leaguesports.services;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class EmailService {

    private final String REGISTER_EMAIL_URL = "http://localhost:8080/email/registration";

    public void SendRegisterEmail(String receiverEmail, String username){

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));	

        Map<String, String> httpBody = new HashMap<>();
        httpBody.put("receiver", receiverEmail);
        httpBody.put("username", username);
        
        HttpEntity<Map<String, String>> entity = new HttpEntity<>(httpBody, httpHeaders);

        ResponseEntity<String> response = restTemplate.postForEntity(REGISTER_EMAIL_URL, entity, String.class);
     
    }    
}
