package es.urjc.dad.leaguesports.services;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.urjc.dad.leaguesports.model.Player;
import es.urjc.dad.leaguesports.model.Team;
import es.urjc.dad.leaguesports.repositories.PlayerRepository;

@Service
@CacheConfig
public class PlayerService {

    @Autowired private PlayerRepository playerRepository;
    @Autowired private TeamService teamService;

    @Cacheable(cacheNames = "players", key = "#page")
    public Page<Player> getAllPlayers(Pageable page){

        Page<Player> players = playerRepository.findAll(page);
        return players;
    }

    public Player getPlayerById(long id){

        Optional<Player> player = playerRepository.findById(id);
        if(player.isPresent())
            return player.get();
        else
            return null;
    }

    public void addPlayer(Player player) {

        playerRepository.save(player);
    }

    @CacheEvict(cacheNames = "players")
    public void removePlayer(long id) {

        Optional<Player> player = playerRepository.findById(id);
        if(player.isPresent()){
            Player p = player.get();
            playerRepository.delete(p);
        }            
    }

    public Player setPlayerTeam(long player_id, long team_id){

        Optional<Player> player = playerRepository.findById(player_id);
        Optional<Team> team = teamService.getTeamById(team_id);
        if(player.isPresent() && team.isPresent()){
            Player p = player.get();
            Team t = team.get();
            p.setTeam(t);
            playerRepository.save(p);
            return player.get();
        } 
        else
            return null;
               
    }

    public Player removePlayerTeam(long player_id){

        Optional<Player> player = playerRepository.findById(player_id);
        if(player.isPresent()){
            Player p = player.get();
            p.setTeam(null);
            playerRepository.save(p);
            return player.get();
        } 
        else
            return null;             
    }

    @Cacheable(cacheNames = "players", key = "#page")
    public Player updatePlayer(long id, Player updatedPlayer) {

        updatedPlayer.setId(id);
        playerRepository.save(updatedPlayer);
        return updatedPlayer;
    }   

    public boolean hasAnPlayer(){
        return playerRepository.count() > 0;
    }

}
