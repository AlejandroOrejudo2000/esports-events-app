package es.urjc.dad.leaguesports.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.urjc.dad.leaguesports.model.Game;
import es.urjc.dad.leaguesports.model.Team;
import es.urjc.dad.leaguesports.model.Tournament;

public interface GameRepository extends JpaRepository<Game, Long>{
    
    public Optional<Game> findByNumber(long number);

    public List<Game> findByTournamentAndVisitorTeamOrLocalTeam(Tournament tournament, Team visitorTeam, Team localTeam);
    public List<Game> findByTournamentOrderByGameDateAsc(Tournament tournament);
}
