package es.urjc.dad.leaguesports.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Game {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;

    @ManyToOne
    private Tournament tournament;

    @ManyToOne
    private Team localTeam;

    @ManyToOne
    private Team visitorTeam;

    protected Game() {
    }

    public Game(Team localteam, Team visitorTeam){
        this.localTeam = localteam;
        this.visitorTeam = visitorTeam;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }
    
    public long getId() {
        return Id;
    }

}
