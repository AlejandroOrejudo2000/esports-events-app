package es.urjc.dad.leaguesports.control;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import es.urjc.dad.leaguesports.model.Team;
import es.urjc.dad.leaguesports.model.Tournament;
import es.urjc.dad.leaguesports.services.TournamentService;

@Controller
public class TournamentController {

	@Autowired
    private TournamentService tournamentService;

    @GetMapping("/torneos")
    public String showTournaments(Model model, Pageable page) {   

        model.addAttribute("tournamentList", tournamentService.getAllTournaments(page));
        return "tournaments";
    }

    @GetMapping("/torneo_{id}")
    public String showTournamentDetails(Model model, @PathVariable long id) {

        Optional<Tournament> tournament = tournamentService.getTournamentById(id);
        List<Team> nonparticipants = tournamentService.getTournamentNonParticipants(id);
        if(tournament.isPresent()) {
            model.addAttribute("tournament", tournament.get());
            model.addAttribute("nonparticipants", nonparticipants);
        }
        return "tournament";
    }

    @PostMapping("/torneo/nuevo")
    public String addTournament(Model model, Tournament tournament){
    
        tournamentService.addTournament(tournament);
        model.addAttribute("id", (int)tournament.getId());
        return "tournamentcreated";
    }

    @PostMapping("/torneo_{id}/añadirparticipante")
    public String addParticipant(Model model, long participantId, @PathVariable long id){
        tournamentService.addParticipant(id, participantId);
        return "redirect:/torneo_{id}";
    }

    @PostMapping("/torneo_{id}/añadirpartido")
    public String addGame(Model model, long localTeamId, long visitorTeamId, @PathVariable long id){
        tournamentService.addGameInTournament(id, localTeamId, visitorTeamId);
        return "redirect:/torneo_{id}";
    }
	
}
