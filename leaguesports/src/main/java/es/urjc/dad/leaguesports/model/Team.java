package es.urjc.dad.leaguesports.model;

import java.util.List;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import javax.persistence.PreRemove;

@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String teamName;

    @Column
    private int years;

    @OneToMany(mappedBy =  "team")
    private List<Player> players;

    @ManyToMany(mappedBy = "participants")
    private List<Tournament> tournaments;   


    public Team(String teamName, int years) {
        this.teamName = teamName;
        this.years = years;
        players = new ArrayList<Player>();
    }

    protected Team() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void addPlayer(Player player){
        players.add(player);
    }
    @Override
    public String toString() {
        return "Team [" + teamName + "]";
    }

    @PreRemove
    public void OnDeleteSetNull(){
        for (Player player : players) {
            player.setTeam(null);
        }
        for(Tournament tournament : tournaments){
            tournament.removeParticipant(this);  
            for (Game game : tournament.getGames()) {
                if(game.getVisitorTeam() == this || game.getLocalTeam() == this)
                    tournament.removeGame(game);
            }          
        }
    }
}
