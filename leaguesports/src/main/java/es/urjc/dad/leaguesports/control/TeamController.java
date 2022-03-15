package es.urjc.dad.leaguesports.control;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;

import es.urjc.dad.leaguesports.services.TeamService;
import es.urjc.dad.leaguesports.model.Team;

@Controller
public class TeamController extends BaseController{

    @Autowired
    private TeamService teamService;

    @GetMapping("/equipos")
    public String showTeams(Model model, Pageable page) {

        Page<Team> teampage = teamService.getAllTeams(page);

        model.addAttribute("teamlist", teampage);
        model.addAttribute("hasprevious", teampage.hasPrevious());
        model.addAttribute("hasnext", teampage.hasNext());
        model.addAttribute("previous", teampage.getNumber() - 1);
        model.addAttribute("next", teampage.getNumber() + 1);

        return "teams";
    }

    @GetMapping("/equipo/{id}")
    public String showTeamDetails(Model model, @PathVariable long id){
        Optional<Team> team = teamService.getTeamById(id);
        if(team.isPresent()) {
            model.addAttribute("team", team.get());
        }
        return "team";
    }

    @GetMapping("/nuevoequipo")
    public String newTeam(){
        return "createteam";
    }

    @PostMapping("/equipo/nuevo")
    public String addTeam(Model model, Team team){
    
        teamService.addTeam(team);
        model.addAttribute("id", (int)team.getId());
        return "teamcreated";
    }

    @GetMapping("/equipo/{id}/borrar")
    public String deleteTeam(@PathVariable long id){
        teamService.removeTeam(id);
        return "redirect:/equipos";
    }

    @GetMapping("/equipo/{id}/editar")
    public String editPlayer(Model model, @PathVariable long id){
        Optional<Team> team = teamService.getTeamById(id);
        if(team.isPresent()) {
            model.addAttribute("team", team.get());
        }
        return "updateteam";
    }

    @PostMapping("/equipo/{id}/actualizar")
    public String updateTeam(@PathVariable long id, Team team){

        teamService.updateTeam(id, team);
        return "redirect:/equipo/{id}";
    }
}