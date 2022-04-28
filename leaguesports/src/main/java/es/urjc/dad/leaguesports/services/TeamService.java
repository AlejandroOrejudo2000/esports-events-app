package es.urjc.dad.leaguesports.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.urjc.dad.leaguesports.model.Team;
import es.urjc.dad.leaguesports.model.User;
import es.urjc.dad.leaguesports.repositories.TeamRepository;

@Service
public class TeamService {
    
    @Autowired private TeamRepository teamRepository;
    @Autowired private UserService userService;

    public List<Team> getAllTeams(){
        List<Team> teams = teamRepository.findAll();
        return teams;
    }

    public Page<Team> getAllTeams(Pageable page){

        Page<Team> teams = teamRepository.findAll(page);
        return teams;
    }

    public List<Team> getUserTeams(String userName){
        List<Team> teams = teamRepository.findAll();
        List<Team> userTeams = new ArrayList<>();
        Optional<User> u = userService.getUserbyName(userName);
        if(u.isPresent()){
            for (Team team : teams) {
                if(team.getUser().equals(u.get()))
                    userTeams.add(team);
            }
        }        
        return userTeams;
    }

    public Optional<Team> getTeamById(long id){

        Optional<Team> team = teamRepository.findById(id);
        return team;
    }

    public void addTeam(Team team){

        teamRepository.save(team);
    }
    
    public void removeTeam(long id) {

        Optional<Team> team = teamRepository.findById(id);
        if(team.isPresent()){
            Team t = team.get();
            teamRepository.delete(t);
        }    
    }

    public void updateTeam(long id, Team updatedTeam) {

        Optional<Team> team = teamRepository.findById(id);
        if(team.isPresent()){
            updatedTeam.setId(id);
            updatedTeam.setUser(team.get().getUser());
            teamRepository.save(updatedTeam);
        }       
    }

    public boolean hasAnyTeam(){
        return teamRepository.count() > 0;
    }
}
