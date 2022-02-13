package es.urjc.dad.leaguesports.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
    private List<Match> matches;

    protected Tournament() {
    }

    public Tournament(String name, Date starting_date, Date finishing_date) {
        this.name = name;
        this.starting_date = starting_date;
        this.finishing_date = finishing_date;
        matches = new ArrayList<Match>();
    }

    public void addMatch(Match match)
    {
        matches.add(match);
    }

    public void removeMatch(Match match)
    {
        matches.remove(match);
    }
    
    public long getId() {
        return Id;
    }

    // TODO: Añadir funcionalidad para convertir strings (input) de fechas a tipo Date.

}
