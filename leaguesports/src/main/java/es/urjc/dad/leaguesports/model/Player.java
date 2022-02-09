package es.urjc.dad.leaguesports.model;

//#region external dependences
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
//#endregion

@Entity
public class Player {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String nickName;

    @Column
    private String genre;

    @Column
    private int age;

    @Column
    private String nationality;

    @ManyToOne
    private Team team;

    public Player(String firstName, String lastName, String nickName, String genre, int age, String nationality) {        
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.genre = genre;
        this.age = age;
        this.nationality = nationality;
    }

    protected Player() {
    }

    //#region Getters & Setters

    public long getId() {
        return Id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }    
    //#endregion

    @Override
    public String toString() {
        return "Player [" + nickName + " (" + firstName + " " + lastName + ")]";
    }
}
