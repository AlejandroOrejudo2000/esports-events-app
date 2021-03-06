package es.urjc.dad.leaguesports.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.springframework.core.env.Environment;

import es.urjc.dad.leaguesports.DTO.GameDTO;
import es.urjc.dad.leaguesports.model.Game;
import es.urjc.dad.leaguesports.model.Product;
import es.urjc.dad.leaguesports.model.Tournament;


@Service
public class EmailService {

    @Autowired Environment environment; 

    private final String REGISTER_EMAIL_URL = "/user/registration";
    private final String EVENT_EMAIL_URL = "/tournament/inscription";
    private final String GAMETABLE_EMAIL_URL = "/tournament/schedule";
    private final String PRODUCT_EMAIL_URL = "/product/purchase";

    public void SendRegisterEmail(String receiverEmail, String username){

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = getDefaultJSONheaders();

        Map<String, Object> httpBody = new HashMap<>();
        httpBody.put("receiver", receiverEmail);
        httpBody.put("username", username);       

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(httpBody, httpHeaders);
        restTemplate.postForEntity(getRestUrl(REGISTER_EMAIL_URL), entity, String.class);     
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
        restTemplate.postForEntity(getRestUrl(EVENT_EMAIL_URL), entity, String.class);    
    }

    public void sendProductEmail(String receiverEmail, Product product){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = getDefaultJSONheaders();

        Map<String, Object> httpBody = new HashMap<>();
        httpBody.put("receiver", receiverEmail);
        httpBody.put("productName", product.getProductName());
        httpBody.put("productPrice", product.getPrice());
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(httpBody, httpHeaders);
        restTemplate.postForEntity(getRestUrl(PRODUCT_EMAIL_URL), entity, String.class);    
    }

    public void SendGameTableEmail(String receiverEmail, Tournament tournament) {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = getDefaultJSONheaders();
        Map<String, Object> httpBody = new HashMap<>();
        httpBody.put("receiver", receiverEmail);
        List<Map<String, Object>> jsongames = new ArrayList<>();        
        List<Game> games = tournament.getGames();
        for (int i = 0; i < games.size(); i++){
            GameDTO game = new GameDTO(games.get(i), i);
            jsongames.add(game.parseToJSON());
        }
        httpBody.put("games", jsongames);
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(httpBody, httpHeaders);
        restTemplate.postForEntity(getRestUrl(GAMETABLE_EMAIL_URL), entity, String.class);           
    }

    public HttpHeaders getDefaultJSONheaders(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return httpHeaders;
    }

    private String getRestUrl(String url){
        return "http://" + environment.getProperty("restservice.host") + ":" + environment.getProperty("restservice.port") + url;
    }

}
