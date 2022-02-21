package es.urjc.dad.leaguesports.services;

import java.util.Date;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.urjc.dad.leaguesports.model.Player;
import es.urjc.dad.leaguesports.model.Team;
import es.urjc.dad.leaguesports.model.Tournament;
import es.urjc.dad.leaguesports.model.Product;

@Service
public class DataBaseInitializer {
    
    @Autowired private PlayerService playerService;
    @Autowired private TeamService teamService;
    @Autowired private TournamentService tournamentService;
    @Autowired private ProductService productService;

    private final int NUM_PLAYERS = 100;
    private final int NUM_TEAMS = 20;
    private final int NUM_TOURNAMENTS = 40;
    private final int NUM_PRODUCTS = 10;

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
        
        for(int i = 0; i < NUM_TOURNAMENTS; i++){
            tournamentService.addTournament(new Tournament("Torneo " + i, new Date(), new Date()));
        }

        for(int i = 0; i < NUM_PRODUCTS; i++) {
        	productService.addProduct(new Product("Producto prueba " + i, i * 10));
        }     

    }
}
