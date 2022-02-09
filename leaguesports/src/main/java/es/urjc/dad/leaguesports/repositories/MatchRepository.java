package es.urjc.dad.leaguesports.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.urjc.dad.leaguesports.model.Match;

public interface MatchRepository extends JpaRepository<Match, Long>{
    
}
