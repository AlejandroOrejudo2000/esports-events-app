package es.urjc.dad.leaguesports.control;

import java.security.Principal;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;

import es.urjc.dad.leaguesports.services.TeamService;
import es.urjc.dad.leaguesports.services.UserService;
import es.urjc.dad.leaguesports.model.Team;
import es.urjc.dad.leaguesports.model.User;

@Controller
public class TeamController extends BaseController{

    @Autowired private TeamService teamService;
    @Autowired private UserService userService;

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
    public String showTeamDetails(Model model, HttpServletRequest request, @PathVariable long id){
        
        Optional<Team> team = teamService.getTeamById(id);
        if(team.isPresent()) {
            Boolean isOwner = false;
            model.addAttribute("team", team.get());
            Principal userPrincipal = request.getUserPrincipal();
            if (userPrincipal != null){
                User u = team.get().getUser();
                if(u != null && u.getUserName().equals(userPrincipal.getName())){
                    isOwner = true;
                }
            }
            model.addAttribute("isOwner", isOwner);
        }
        return "team";
    }

    @GetMapping("/private/equipo/crear")
    public String newTeam(){
        return "createteam";
    }

    @PostMapping("/private/equipo/nuevo")
    public String addTeam(Model model, Team team){
    
        String username = model.getAttribute("user_name").toString();
        Optional<User> u = userService.getUserbyName(username);
        if(u.isPresent()){
            team.setUser(u.get());
        }
        teamService.addTeam(team);
        model.addAttribute("id", (int)team.getId());
        return "teamcreated";
    }

    @GetMapping("/private/equipo/{id}/borrar")
    public String deleteTeam(@PathVariable long id){
        teamService.removeTeam(id);
        return "redirect:/equipos";
    }

    @GetMapping("/private/equipo/{id}/actualizar")
    public String editPlayer(Model model, @PathVariable long id){
        Optional<Team> team = teamService.getTeamById(id);
        if(team.isPresent()) {
            model.addAttribute("team", team.get());
        }
        return "updateteam";
    }

    @PostMapping("/private/equipo/{id}/modificado")
    public String updateTeam(@PathVariable long id, Team team){

        teamService.updateTeam(id, team);
        return "redirect:/equipo/{id}";
    }
}