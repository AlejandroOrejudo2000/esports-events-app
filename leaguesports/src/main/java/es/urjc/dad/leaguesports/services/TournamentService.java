package es.urjc.dad.leaguesports.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.urjc.dad.leaguesports.model.Game;
import es.urjc.dad.leaguesports.model.Team;
import es.urjc.dad.leaguesports.model.Tournament;
import es.urjc.dad.leaguesports.model.User;
import es.urjc.dad.leaguesports.repositories.GameRepository;
import es.urjc.dad.leaguesports.repositories.TournamentRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class TournamentService {
    
    @Autowired private TournamentRepository tournamentRepository;
    @Autowired private GameRepository gameRepository;
    @Autowired private EmailService emailService;
    @Autowired private TeamService teamService;

    public Tournament createTournament(String tournamentName, String startDateString, String endDateString) throws ParseException{
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date startDate = formatter.parse(startDateString);
        Date endDate = formatter.parse(endDateString);

        if(startDate.compareTo(endDate) >= 0){
            Date aux = endDate;
            endDate = startDate;
            startDate = aux;
        }

        Tournament tournament = new Tournament(tournamentName, startDate, endDate);
        tournamentRepository.save(tournament);
        return tournament;        
    }

    public Page<Tournament> getAllTournaments(Pageable page){

        Page<Tournament> tournaments = tournamentRepository.findAll(page);
        return tournaments;
    }

    public Optional<Tournament> getTournamentById(long id){

        Optional<Tournament> tournament = tournamentRepository.findById(id);
        return tournament;
    }

    public Optional<Game> getGameByNumber(long number){

        Optional<Game> game = gameRepository.findByNumber(number);
        return game;
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

            User user = team.get().getUser();
            emailService.SendEventEmail(user.getEmail(), user.getUserName(), t.getName(), tournamentId);
        }
    }

    public void removeParticipant(long tournamentId, long teamId)
    {
        Optional<Tournament> tournament = getTournamentById(tournamentId);
        Optional<Team> team = teamService.getTeamById(teamId);
        if(tournament.isPresent() && team.isPresent()) {
            Tournament t = tournament.get();
            t.removeParticipant(team.get());
            List<Game> removedGames = gameRepository.findByTournamentAndVisitorTeamOrLocalTeam(t, team.get(), team.get());
            for (Game game : removedGames) {
                t.removeGame(game);
            }  
            // Removeall no funciona (?)          
            tournamentRepository.save(t);
        }
    }

    public void addGameInTournament(long tournamentId, long localTeamId, long visitorTeamId, String dateString) throws ParseException{

        if(localTeamId == visitorTeamId) return;
        Optional<Tournament> tournament = getTournamentById(tournamentId);
        Optional<Team> localTeam = teamService.getTeamById(localTeamId);
        Optional<Team> visitorTeam = teamService.getTeamById(visitorTeamId);
        if(tournament.isPresent() && localTeam.isPresent() && visitorTeam.isPresent()) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            Date date = formatter.parse(dateString);
            Tournament t = tournament.get();
            Game game = new Game(t, localTeam.get(), visitorTeam.get(), date);
            t.addGame(game);
            tournamentRepository.save(t);
        }
    }

    public void removeGameInTournament(long tournamentId, long gameNumber){
        Optional<Tournament> tournament = getTournamentById(tournamentId);
        Optional<Game> game = getGameByNumber(gameNumber);
        if(tournament.isPresent() && game.isPresent()){
            Tournament t = tournament.get();
            t.removeGame(game.get());
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

    public List<Game> getGamesOrderByDate(long tournamentId) {
        Optional<Tournament> tournament = getTournamentById(tournamentId);
        if(tournament.isPresent()){
            return gameRepository.findByTournamentOrderByGameDateAsc(tournament.get());
        }
        else{
            return new ArrayList<Game>();
        }        
    }

}
