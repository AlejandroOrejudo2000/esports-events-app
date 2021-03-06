package es.urjc.dad.leaguesports.control;

import java.security.Principal;
import java.text.ParseException;
import java.util.List;
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

import es.urjc.dad.leaguesports.model.Team;
import es.urjc.dad.leaguesports.model.Tournament;
import es.urjc.dad.leaguesports.model.User;
import es.urjc.dad.leaguesports.services.EmailService;
import es.urjc.dad.leaguesports.services.TournamentService;
import es.urjc.dad.leaguesports.services.UserService;

@Controller
public class TournamentController extends BaseController{

	@Autowired
    private TournamentService tournamentService;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @GetMapping("/torneos")
    public String showTournaments(Model model, Pageable page) {   

        Page<Tournament> tournamentpage = tournamentService.getAllTournaments(page);

        model.addAttribute("tournamentList", tournamentpage);
        model.addAttribute("hasprevious", tournamentpage.hasPrevious());
        model.addAttribute("hasnext", tournamentpage.hasNext());
        model.addAttribute("previous", tournamentpage.getNumber() - 1);
        model.addAttribute("next", tournamentpage.getNumber() + 1);

        return "tournaments";
    }

    @GetMapping("/torneo/{id}")
    public String showTournamentDetails(Model model, @PathVariable long id) {

        Optional<Tournament> tournament = tournamentService.getTournamentById(id);
        List<Team> nonparticipants = tournamentService.getTournamentNonParticipants(id);
        if(tournament.isPresent()) {
            Tournament t = tournament.get();
            model.addAttribute("tournament", t);
            model.addAttribute("nonparticipants", nonparticipants);
            model.addAttribute("games", tournamentService.getGamesOrderByDate(id));
        }
        return "tournament";
    }

    @GetMapping("/private/torneo/{id}/csv")
    public String receiveTournamentTable(Model model, HttpServletRequest request, @PathVariable long id) {
        Optional<Tournament> tournament = tournamentService.getTournamentById(id);
        if(tournament.isPresent()) {
            Principal userPrincipal = request.getUserPrincipal();
            if(userPrincipal != null){
                Optional<User> u = userService.getUserbyName(userPrincipal.getName().toString());
                if(u.isPresent()){
                    String email = u.get().getEmail();
                    emailService.SendGameTableEmail(email, tournament.get());
                }
            }
        }
        return "redirect:/torneos";
    }

    @GetMapping("/private/torneo/crear")
    public String newTournament(){
        return "createtournament";
    }

    @PostMapping("/private/torneo/nuevo")
    public String addTournament(Model model, String tournamentName, String startDate, String endDate){   

        try{
            Tournament t = tournamentService.createTournament(tournamentName, startDate, endDate);
            model.addAttribute("id", (int)t.getId());
            return "tournamentcreated";
        }
        catch(ParseException e){
            return "redirect:/torneos";
        }          
    }

    @PostMapping("/private/torneo/{id}/nuevo/equipo")
    public String addParticipant(long participantId, @PathVariable long id){
        tournamentService.addParticipant(id, participantId);
        return "redirect:/torneo/{id}";
    }

    @PostMapping("/private/torneo/{id}/nuevo/partido")
    public String addGame(long localTeamId, long visitorTeamId, String gameDate, @PathVariable long id){

        try{
            tournamentService.addGameInTournament(id, localTeamId, visitorTeamId, gameDate);
            return "redirect:/torneo/{id}";
        }
        catch(ParseException e){
            System.out.println(e.getMessage());
            return "redirect:/torneos";
        } 
    }

    @GetMapping("/private/torneo/{id}/borrar/equipo/{teamid}")
    public String removeParticipant(@PathVariable long id, @PathVariable long teamid) {
        tournamentService.removeParticipant(id, teamid);
        return "redirect:/torneo/{id}";
    }

    @GetMapping("/private/torneo/{id}/borrar/partido/{gameid}")
    public String removeGame(@PathVariable long id, @PathVariable long gameid) {
        tournamentService.removeGameInTournament(id, gameid);
        return "redirect:/torneo/{id}";
    }

    @GetMapping("/private/torneo/{id}/borrar")
    public String removeTournament(@PathVariable long id){
        tournamentService.removeTournament(id);
        return "redirect:/torneos";

    }
	
}
