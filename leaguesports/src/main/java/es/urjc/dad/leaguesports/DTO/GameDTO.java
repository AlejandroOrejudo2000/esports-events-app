package es.urjc.dad.leaguesports.DTO;

import java.util.HashMap;
import java.util.Map;

import es.urjc.dad.leaguesports.model.Game;

public class GameDTO {

    private int number;
    private String localteam;
    private String visitorteam;
    private String date;
    private String results;

    public GameDTO() {
    }

    public GameDTO(Game game, int number){
        this.number = number;
        this.localteam = game.getLocalTeam().getTeamName();
        this.visitorteam = game.getVisitorTeam().getTeamName();
        this.date = game.getGameDate().toString();
        this.results = game.getGameResults().toString();
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getLocalteam() {
        return localteam;
    }

    public void setLocalteam(String localteam) {
        this.localteam = localteam;
    }

    public String getVisitorteam() {
        return visitorteam;
    }

    public void setVisitorteam(String visitorteam) {
        this.visitorteam = visitorteam;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }

    public Map<String, Object> parseToJSON(){
        Map<String, Object> jsonElement = new HashMap<>();
        jsonElement.put("number", number);
        jsonElement.put("localteam", localteam);
        jsonElement.put("visitorteam", visitorteam);
        jsonElement.put("date", date);
        jsonElement.put("results", results);
        return jsonElement;
    }


    
}
