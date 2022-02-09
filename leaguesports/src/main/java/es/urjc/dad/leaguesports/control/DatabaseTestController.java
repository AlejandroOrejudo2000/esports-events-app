package es.urjc.dad.leaguesports.control;

import java.util.ArrayList;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import es.urjc.dad.leaguesports.model.Match;
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
        
        Team team1 = new Team("Team1", 5);
        Team team2 = new Team("Team2", 12);
        teamRepository.save(team1);
        teamRepository.save(team2);
        Match match = new Match(team1, team2);

        match.setTournament(tournament);

        tournament.addMatch(match);
        
        tournamentRepository.save(tournament);
        matchRepository.save(match);



    }
}
