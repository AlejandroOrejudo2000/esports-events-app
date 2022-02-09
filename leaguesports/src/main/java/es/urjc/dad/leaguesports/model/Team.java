package es.urjc.dad.leaguesports.model;

//#region External dependences
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
//#endregion

@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;

    @Column
    private String teamName;

    @Column
    private int years;

    @OneToMany(mappedBy =  "team")
    private List<Player> players;


    public Team(String teamName, int years) {
        this.teamName = teamName;
        this.years = years;
    }

    protected Team() {
    }

    //#region Getters & Setters
    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
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
    //#endregion

    @Override
    public String toString() {
        return "Team [" + teamName + "]";
    }
}
