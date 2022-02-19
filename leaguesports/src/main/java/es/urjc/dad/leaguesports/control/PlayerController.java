package es.urjc.dad.leaguesports.control;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import es.urjc.dad.leaguesports.model.Player;
import es.urjc.dad.leaguesports.services.PlayerService;

@Controller
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping("/jugadores")
    public String showPlayers(Model model, Pageable page) {   

        model.addAttribute("playerlist", playerService.getAllPlayers(page));
        return "players";
    }

    @GetMapping("/jugador/{id}")
    public String showPlayerDetails(Model model, @PathVariable long id) {

        Optional<Player> player = playerService.getPlayerById(id);
        if(player.isPresent()) {
            model.addAttribute("player", player.get());    
            return "player";                    
        }
        else{
            return "playernotfound";
        }        
    }

    @PostMapping("/jugador/nuevo")
    public String addPlayer(Model model, Player player){
      
        playerService.addPlayer(player);
        model.addAttribute("id", (int)player.getId());
        return "playercreated";
    }
}
