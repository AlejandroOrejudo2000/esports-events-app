package es.urjc.dad.leaguesports.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.GenerationType;

@Entity
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;

    @Column
    private String name;

    @Temporal(TemporalType.DATE)
    private Date starting_date;

    @Temporal(TemporalType.DATE)
    private Date finishing_date;

    @ManyToOne
    private Team winner;

    @OneToMany(mappedBy = "tournament", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Game> games;

    @ManyToMany
    private List<Team> participants;

    protected Tournament() {
    }

    public Tournament(String name, Date starting_date, Date finishing_date) {
        this.name = name;
        this.starting_date = starting_date;
        this.finishing_date = finishing_date;
        games = new ArrayList<Game>();
    }

    public void addGame(Game game)  {
        games.add(game);
    }

    public void removeGame(Game game) {
        games.remove(game);
    }

    public void addParticipant(Team team){
        participants.add(team);
    }

    public void removeParticipant(Team team)
    {
        participants.remove(team);
    }
    
    public long getId() {
        return Id;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public List<Team> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Team> participants) {
        this.participants = participants;
    }

    // TODO: Añadir funcionalidad para convertir strings (input) de fechas a tipo Date.

}
