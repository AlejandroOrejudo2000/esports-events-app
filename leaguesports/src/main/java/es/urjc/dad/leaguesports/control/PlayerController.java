package es.urjc.dad.leaguesports.control;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import es.urjc.dad.leaguesports.model.Player;
import es.urjc.dad.leaguesports.services.PlayerService;
import es.urjc.dad.leaguesports.services.TeamService;

@Controller
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private TeamService teamService;

    @GetMapping("/jugadores")
    public String showPlayers(Model model, Pageable page) {   

        Page<Player> playerPage = playerService.getAllPlayers(page);
        model.addAttribute("playerlist", playerPage);
        model.addAttribute("hasprevious", playerPage.hasPrevious());
        model.addAttribute("hasnext", playerPage.hasNext());
        model.addAttribute("previous", playerPage.getNumber() - 1);
        model.addAttribute("next", playerPage.getNumber() + 1);
        return "players";
    }

    @GetMapping("/jugador/{id}")
    public String showPlayerDetails(Model model, @PathVariable long id) {

        Optional<Player> player = playerService.getPlayerById(id);
        if(player.isPresent()) {
            model.addAttribute("player", player.get());   
            model.addAttribute("teams", teamService.getAllTeams());                            
        }      
        return "player";    
    }

    @PostMapping("/jugador/nuevo")
    public String addPlayer(Model model, Player player){
      
        playerService.addPlayer(player);
        model.addAttribute("id", (int)player.getId());
        return "playercreated";
    }

    @GetMapping("/jugador/{id}/borrar")
    public String deleteTeam(@PathVariable long id){
        playerService.removePlayer(id);
        return "redirect:/jugadores";
    }

    @PostMapping("/jugador/{id}/equipo")
    public String setTeam(@PathVariable long id, long teamId){

        playerService.setPlayerTeam(id, teamId);
        return "redirect:/jugador/{id}";
    }

    @GetMapping("/jugador/{id}/eliminarequipo")
    public String removeTeam(@PathVariable long id) {
        playerService.removePlayerTeam(id);
        return "redirect:/jugador/{id}";
    }

    @GetMapping("/jugador/{id}/editar")
    public String editPlayer(Model model, @PathVariable long id){
        Optional<Player> player = playerService.getPlayerById(id);
        if(player.isPresent()) {
            model.addAttribute("player", player.get());
        }
        return "updatePlayer";
    }

    @PostMapping("/jugador/{id}/actualizar")
    public String updatePlayer(@PathVariable long id, Player player){

        playerService.updatePlayer(id, player);
        return "redirect:/jugador/{id}";
    }
    
}
