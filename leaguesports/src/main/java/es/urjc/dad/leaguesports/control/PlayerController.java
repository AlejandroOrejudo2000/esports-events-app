package es.urjc.dad.leaguesports.control;

import java.security.Principal;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import es.urjc.dad.leaguesports.model.Player;
import es.urjc.dad.leaguesports.model.Team;
import es.urjc.dad.leaguesports.services.PlayerService;
import es.urjc.dad.leaguesports.services.TeamService;

@Controller
public class PlayerController extends BaseController{

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
    public String showPlayerDetails(Model model, HttpServletRequest request, @PathVariable long id) {

        Player player = playerService.getPlayerById(id);
        if(player != null) {
            Boolean isOwner = false;
            Principal userPrincipal = request.getUserPrincipal();            
            model.addAttribute("player", player);
            if (userPrincipal != null){
                Team team = player.getTeam();
                if (team != null && team.getUser().getUserName().equals(userPrincipal.getName())){
                    isOwner = true;
                }
                model.addAttribute("teams", teamService.getUserTeams(userPrincipal.getName()));
            } 
            model.addAttribute("isOwner", isOwner);            
        }      
        return "player";    
    }

    @GetMapping("/private/jugador/crear")
    public String newPlayer(){
        return "createplayer";
    }

    @PostMapping("/private/jugador/nuevo")
    public String addPlayer(Model model, Player player){
      
        playerService.addPlayer(player);
        model.addAttribute("id", (int)player.getId());
        return "playercreated";
    }

    @GetMapping("/private/jugador/{id}/borrar")
    public String deleteTeam(@PathVariable long id){
        playerService.removePlayer(id);
        return "redirect:/jugadores";
    }

    @PostMapping("/private/jugador/{id}/nuevo/equipo")
    public String setTeam(@PathVariable long id, long teamId){

        playerService.setPlayerTeam(id, teamId);
        return "redirect:/jugador/{id}";
    }

    @GetMapping("/private/jugador/{id}/borrar/equipo")
    public String removeTeam(@PathVariable long id) {
        playerService.removePlayerTeam(id);
        return "redirect:/jugador/{id}";
    }

    @GetMapping("/private/jugador/{id}/actualizar")
    public String editPlayer(Model model, @PathVariable long id){
        Player player = playerService.getPlayerById(id);
        if(player != null) {
            model.addAttribute("player", player);
        }
        return "updateplayer";
    }

    @PostMapping("/private/jugador/{id}/modificado")
    public String updatePlayer(@PathVariable long id, Player player){

        playerService.updatePlayer(id, player);
        return "redirect:/jugador/{id}";
    }
    
}
