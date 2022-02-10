package es.urjc.dad.leaguesports.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;

    @Column
    private String userName;
    
    @OneToMany(mappedBy =  "user")
    private List<Product> products;

    public User(String userName) {        
        this.userName = userName;
    }

    protected User() {
    }

    //#region Getters & Setters

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    //#endregion

    @Override
    public String toString() {
        return "User [" + userName + "]";
    }
	
}
