package es.urjc.dad.leaguesports.control;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;

import es.urjc.dad.leaguesports.repositories.TeamRepository;
import es.urjc.dad.leaguesports.model.Team;

@Controller
public class TeamController{

    @Autowired
    private TeamRepository repository;

    @GetMapping("/equipos")
    public String showTeams(Model model, Pageable page) {

        model.addAttribute("teamlist", repository.findAll(page));
        return "teams";
    }

    @GetMapping("/equipo_{id}")
    public String showTeamDetails(Model model, @PathVariable long id){
        Optional<Team> team = repository.findById(id);
        if(team.isPresent()) {
            model.addAttribute("team", team.get());
        }
        return "team";
    }

    @PostMapping("/equipo/nuevo")
    public String addTeam(Model model, Team team){
    
        repository.save(team);
        model.addAttribute("id", (int)team.getId());
        return "teamcreated";
    }
}