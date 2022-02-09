package es.urjc.dad.leaguesports.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.urjc.dad.leaguesports.model.Tournament;

public interface TournamentRepository extends JpaRepository<Tournament, Long>{
    
}
