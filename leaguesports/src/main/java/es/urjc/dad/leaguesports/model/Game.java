package es.urjc.dad.leaguesports.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;


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

    protected Game() {
    }

    public Game(Tournament tournament, Team localteam, Team visitorTeam){
        this.tournament = tournament;
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
        return number;
    }

}
