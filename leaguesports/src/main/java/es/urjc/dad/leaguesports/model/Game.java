package es.urjc.dad.leaguesports.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@IdClass(GameId.class)

public class Game {
    
    @Id
    @ManyToOne
    @MapsId("tournament")
    private Tournament tournament;

    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    @MapsId("number")    
    private long number;

    @ManyToOne
    private Team localTeam;

    @ManyToOne
    private Team visitorTeam;

    @Temporal(TemporalType.DATE)
    private Date gameDate;

    @Enumerated(EnumType.STRING)
    private GameResults gameResults;


    protected Game() {
    }

    public Game(Tournament tournament, Team localteam, Team visitorTeam, Date date){
        this.tournament = tournament;
        this.localTeam = localteam;
        this.visitorTeam = visitorTeam;
        this.gameDate = date;
        gameResults = GameResults.None;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }
    
    public long getId() {
        return number;
    }

    public Date getGameDate() {
        return gameDate;
    }

    public void setGameDate(Date gameDate) {
        this.gameDate = gameDate;
    }

    public GameResults getGameResults() {
        return gameResults;
    }

    public void setGameResults(GameResults gameResults) {
        this.gameResults = gameResults;
    }
    

}
