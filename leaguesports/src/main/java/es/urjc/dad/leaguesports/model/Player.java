package es.urjc.dad.leaguesports.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Player {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

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

    @JsonIgnore
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

    public long getId() {
        return id;
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
        this.id = id;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    } 
    
    public Team getTeam(){
        return this.team;
    }   

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "Player (" + id + ") [" + nickName + " (" + firstName + " " + lastName + ")]";
    }
}
