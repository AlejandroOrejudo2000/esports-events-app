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
import es.urjc.dad.leaguesports.repositories.PlayerRepository;

@Controller
public class PlayerController {

    @Autowired
    private PlayerRepository repository;

    @GetMapping("/jugadores")
    public String showPlayers(Model model, Pageable page) {   

        model.addAttribute("playerlist", repository.findAll(page));
        return "players";
    }

    @GetMapping("/jugador_{id}")
    public String showPlayerDetails(Model model, @PathVariable long id) {

        Optional<Player> player = repository.findById(id);
        if(player.isPresent()) {
            model.addAttribute("player", player.get());
        }
        return "player";
    }

    @PostMapping("/jugador/nuevo")
    public String addPlayer(Model model, Player player){

        System.out.println(player);        
        repository.save(player);
        model.addAttribute("id", (int)player.getId());
        return "playercreated";
    }
}
