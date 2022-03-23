package es.urjc.dad.leaguesports.services;

import java.util.Date;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import es.urjc.dad.leaguesports.model.Player;
import es.urjc.dad.leaguesports.model.Team;
import es.urjc.dad.leaguesports.model.Tournament;
import es.urjc.dad.leaguesports.model.User;
import es.urjc.dad.leaguesports.model.UserRoles;
import es.urjc.dad.leaguesports.model.Product;

@Service
public class DataBaseInitializer {
    
    @Autowired private PlayerService playerService;
    @Autowired private TeamService teamService;
    @Autowired private TournamentService tournamentService;
    @Autowired private ProductService productService;
    @Autowired private UserService userService;
    @Autowired private PasswordEncoder encoder;

    private final int NUM_PLAYERS = 100;
    private final int NUM_USERS = 5;
    private final int TEAMS_PER_USER = 20;
    private final int NUM_TOURNAMENTS = 40;
    private final int NUM_PRODUCTS = 10;
    
    private final String DEFAULT_EMAIL = "lolesports.web@gmail.com";

    private Team[] teams = new Team[NUM_USERS * TEAMS_PER_USER];

    @PostConstruct
    private void initDatabase()
    {

        for(int i = 0; i < NUM_USERS; i++) {
            User user = new User("User"+i, encoder.encode("pass"), DEFAULT_EMAIL);
            user.addRole(UserRoles.User);
        	userService.addUser(user);
            for (int j = 0; j < TEAMS_PER_USER; j++) {
                Team team = new Team("Team " + (j + i * TEAMS_PER_USER), new Random().nextInt(20));
                team.setUser(user);
                teamService.addTeam(team);
                teams[j+i*TEAMS_PER_USER] = team;
            }
        } 

        for(int i = 0; i < NUM_PLAYERS; i++) {
            Player player = new Player("Nombre " + i, "Apellido " + i, "Nickname " + i, i % 2 == 0? "Hombre":"Mujer", i % 10 + 20, "España");
            player.setTeam(teams[new Random().nextInt(NUM_USERS * TEAMS_PER_USER)]);
            playerService.addPlayer(player);
        }      
        
        for(int i = 0; i < NUM_TOURNAMENTS; i++){
            tournamentService.addTournament(new Tournament("Torneo " + i, new Date(), new Date()));
        }

        for(int i = 0; i < NUM_PRODUCTS; i++) {
        	productService.addProduct(new Product("Producto prueba " + i, i * 10));
        }     

        

    }
}
