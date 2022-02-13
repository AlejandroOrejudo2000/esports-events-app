package es.urjc.dad.leaguesports.control;

import java.util.ArrayList;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import es.urjc.dad.leaguesports.model.Match;
import es.urjc.dad.leaguesports.model.Player;
import es.urjc.dad.leaguesports.model.Team;
import es.urjc.dad.leaguesports.model.Tournament;
import es.urjc.dad.leaguesports.repositories.MatchRepository;
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
    MatchRepository matchRepository;

    @Autowired
    TournamentRepository tournamentRepository;

    @PostConstruct
    private void initDatabase()
    {
        System.out.println("Initializing DB");
        Tournament tournament = new Tournament("T1", new Date(), new Date());
        
        Player player1 = new Player("A1", "B1", "C1", "Hombre", 2, "Español");
        Player player2 = new Player("A2", "B2", "C2", "Hombre", 2, "Español");
        Player player3 = new Player("A3", "B3", "C3", "Hombre", 2, "Español");
        Player player4 = new Player("A4", "B4", "C4", "Hombre", 2, "Español");
        
        Team team1 = new Team("Team1", 5);
        Team team2 = new Team("Team2", 12);

        player1.setTeam(team1);
        player2.setTeam(team1);
        player3.setTeam(team2);

        teamRepository.save(team1);
        teamRepository.save(team2);

        playerRepository.save(player1);
        playerRepository.save(player2);
        playerRepository.save(player3);
        playerRepository.save(player4);

        Match match = new Match(team1, team2);

        match.setTournament(tournament);

        tournament.addMatch(match);
        
        tournamentRepository.save(tournament);
        matchRepository.save(match);

    }
}
