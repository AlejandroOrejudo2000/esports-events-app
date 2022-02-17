package es.urjc.dad.leaguesports.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.urjc.dad.leaguesports.model.Game;
import es.urjc.dad.leaguesports.model.Team;
import es.urjc.dad.leaguesports.model.Tournament;
import es.urjc.dad.leaguesports.repositories.GameRepository;
import es.urjc.dad.leaguesports.repositories.TournamentRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class TournamentService {
    
    @Autowired private TournamentRepository tournamentRepository;
    @Autowired private GameRepository gameRepository;

    @Autowired private TeamService teamService;

    public Page<Tournament> getAllTournaments(Pageable page){

        Page<Tournament> tournaments = tournamentRepository.findAll(page);
        return tournaments;
    }

    public Optional<Tournament> getTournamentById(long id){

        Optional<Tournament> Tournament = tournamentRepository.findById(id);
        return Tournament;
    }

    public void addTournament(Tournament tournament){

        tournamentRepository.save(tournament);
    }

    public void removeTournament(long id){

        Optional<Tournament> tournament = tournamentRepository.findById(id);
        if(tournament.isPresent()){
            Tournament t = tournament.get();
            tournamentRepository.delete(t);
        }  
    }

    public void addParticipant(long tournamentId, long teamId)
    {
        Optional<Tournament> tournament = getTournamentById(tournamentId);
        Optional<Team> team = teamService.getTeamById(teamId);
        if(tournament.isPresent() && team.isPresent()) {
            Tournament t = tournament.get();
            t.addParticipant(team.get());
            tournamentRepository.save(t);
        }
    }

    public void addGameInTournament(long tournamentId, long localTeamId, long visitorTeamId)
    {
        if(localTeamId == visitorTeamId) return;
        Optional<Tournament> tournament = getTournamentById(tournamentId);
        Optional<Team> localTeam = teamService.getTeamById(localTeamId);
        Optional<Team> visitorTeam = teamService.getTeamById(visitorTeamId);
        if(tournament.isPresent() && localTeam.isPresent() && visitorTeam.isPresent()) {
            Tournament t = tournament.get();
            Game game = new Game(t, localTeam.get(), visitorTeam.get());
            t.addGame(game);
            tournamentRepository.save(t);
        }
    }

    public List<Team> getTournamentParticipants(long tournamentId){
        Optional<Tournament> tournament = getTournamentById(tournamentId);
        if(tournament.isPresent()){
            Tournament t = tournament.get();
            return t.getParticipants();
        }
        else{
            return new ArrayList<Team>();
        }
    }

    public List<Team> getTournamentNonParticipants(long tournamentId){
        Optional<Tournament> tournament = getTournamentById(tournamentId);
        List<Team> allTeams = teamService.getAllTeams();
        if(tournament.isPresent()){
            Tournament t = tournament.get();
            allTeams.removeAll(t.getParticipants());
        }
        return allTeams;
    }

}
