package es.urjc.dad.leaguesports.control;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import es.urjc.dad.leaguesports.repositories.GameRepository;
import es.urjc.dad.leaguesports.repositories.PlayerRepository;
import es.urjc.dad.leaguesports.repositories.TeamRepository;
import es.urjc.dad.leaguesports.repositories.TournamentRepository;

@Controller
public class DatabaseTestController {
    
    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    GameRepository matchRepository;

    @Autowired
    TournamentRepository tournamentRepository;

    @PostConstruct
    private void initDatabase()
    {
        System.out.println("Initializing DB");
        
        // Tournament tournament1 = new Tournament("T1", new Date(), new Date());
        // Tournament tournament2 = new Tournament("T2", new Date(), new Date());
        
        // Player player1 = new Player("A1", "B1", "C1", "Hombre", 2, "Español");
        // Player player2 = new Player("A2", "B2", "C2", "Hombre", 2, "Español");
        // Player player3 = new Player("A3", "B3", "C3", "Hombre", 2, "Español");
        // Player player4 = new Player("A4", "B4", "C4", "Hombre", 2, "Español");
        
        // Team team1 = new Team("Team1", 5);
        // Team team2 = new Team("Team2", 12);
        // Team team3 = new Team("Team3", 10);
        // Team team4 = new Team("Team4", 8);

        // player1.setTeam(team1);
        // player2.setTeam(team1);
        // player3.setTeam(team2);

        // teamRepository.save(team1);
        // teamRepository.save(team2);
        // teamRepository.save(team3);
        // teamRepository.save(team4);

        // playerRepository.save(player1);
        // playerRepository.save(player2);
        // playerRepository.save(player3);
        // playerRepository.save(player4);

        // Match match1 = new Match(team1, team2);
        // Match match2 = new Match(team2, team3);
        // Match match3 = new Match(team3, team4);

        // match1.setTournament(tournament1);
        // match2.setTournament(tournament2);
        // match3.setTournament(tournament1);

        // tournament1.addMatch(match1);
        // tournament2.addMatch(match2);
        // tournament1.addMatch(match3);
        
        // tournamentRepository.save(tournament1);
        // tournamentRepository.save(tournament2);
        
        // matchRepository.save(match1);
        // matchRepository.save(match2);
        // matchRepository.save(match3);

    }
}
