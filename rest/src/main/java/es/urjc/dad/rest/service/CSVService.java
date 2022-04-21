package es.urjc.dad.rest.service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.opencsv.CSVWriter;

import org.springframework.stereotype.Service;

@Service
public class CSVService {
    
    private final String filepath = "./file.csv";

    public String generateCSV(List<String[]> stringArray) throws IOException{
        CSVWriter writer = new CSVWriter(new FileWriter(filepath));
        writer.writeAll(stringArray);
        writer.close();
        return filepath;
    }

    public List<String[]> serializeGameData(List<Map<String, Object>> gamedata){
        List<String[]> gameList = new ArrayList<>();
        for (Map<String, Object> gameMap : gamedata){
            String[] serializedGame = new String[5];
            serializedGame[0] = Integer.toString((Integer) gameMap.get("number"));
            serializedGame[1] = (String) gameMap.get("localteam");
            serializedGame[2] = (String) gameMap.get("visitorteam");
            serializedGame[3] = (String) gameMap.get("date");
            serializedGame[4] = (String) gameMap.get("results");
            gameList.add(serializedGame);
        }
        return gameList;
    }  

}
