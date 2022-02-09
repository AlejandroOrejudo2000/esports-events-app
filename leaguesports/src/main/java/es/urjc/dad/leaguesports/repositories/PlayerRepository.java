package es.urjc.dad.leaguesports.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.urjc.dad.leaguesports.model.Player;

public interface PlayerRepository extends JpaRepository<Player, Long>{
    
}
