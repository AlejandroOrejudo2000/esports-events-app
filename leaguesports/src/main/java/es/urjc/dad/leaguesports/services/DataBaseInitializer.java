package es.urjc.dad.leaguesports.services;

import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.urjc.dad.leaguesports.model.Player;
import es.urjc.dad.leaguesports.model.Team;

@Service
public class DataBaseInitializer {
    
    @Autowired private PlayerService playerService;
    @Autowired private TeamService teamService;

    private final int NUM_PLAYERS = 100;
    private final int NUM_TEAMS = 20;

    @PostConstruct
    private void initDatabase()
    {
        for(int i = 0; i < NUM_TEAMS; i++){
            teamService.addTeam(new Team("Team " + i, new Random().nextInt(20)));
        }

        for(int i = 0; i < NUM_PLAYERS; i++) {
            playerService.addPlayer(new Player("Nombre " + i, "Apellido " + i, "Nickname " + i, i % 2 == 0? "Hombre":"Mujer", i % 10 + 20, "España"));
            playerService.setPlayerTeam(i, new Random().nextInt(NUM_TEAMS + 10));
        }       


    }
}
