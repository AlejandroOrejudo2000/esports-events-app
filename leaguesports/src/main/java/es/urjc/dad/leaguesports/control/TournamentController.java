package es.urjc.dad.leaguesports.control;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import es.urjc.dad.leaguesports.model.Tournament;
import es.urjc.dad.leaguesports.repositories.TournamentRepository;

@Controller
public class TournamentController {

	@Autowired
    private TournamentRepository repository;

    @GetMapping("/torneos")
    public String showTournaments(Model model, Pageable page) {   

        model.addAttribute("tournamentList", repository.findAll(page));
        return "tournaments";
    }

    @GetMapping("/torneo_{id}")
    public String showTournamentDetails(Model model, @PathVariable long id) {

        Optional<Tournament> tournament = repository.findById(id);
        if(tournament.isPresent()) {
            model.addAttribute("tournament", tournament.get());
        }
        return "tournament";
    }

    @PostMapping("/torneo/nuevo")
    public String addTournament(Model model, Tournament tournament){

        System.out.println(tournament);        
        repository.save(tournament);
        model.addAttribute("id", (int)tournament.getId());
        return "tournamentcreated";
    }
	
}
