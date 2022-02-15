package es.urjc.dad.leaguesports.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.urjc.dad.leaguesports.model.Tournament;
import es.urjc.dad.leaguesports.repositories.MatchRepository;
import es.urjc.dad.leaguesports.repositories.TournamentRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class TournamentService {
    
    @Autowired private TournamentRepository tournamentRepository;
    @Autowired private MatchRepository matchRepository;

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


}
