package es.urjc.dad.leaguesports.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String userName;
    
    @Column
    private String password;

    @Column
    private String email;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;
    
    @OneToMany(mappedBy =  "user")
    private List<Product> products;

    @OneToMany(mappedBy = "user")
    private List<Team> teams;

    public User(String userName, String password, String email) {        
        this.userName = userName;
        this.password = password;
        this.email = email;
        roles = new ArrayList<>();
    }

    protected User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User [" + userName + "]";
    }

    public List<String> getRoles() {
        return roles;
    }

    public void addRole(UserRoles role){
        if(!roles.contains(role.toString()))
            roles.add(role.toString());
    }

    public void removeRole(UserRoles role){
        roles.remove(role.toString());
    }

    public boolean hasRole(UserRoles role){
        return roles.contains(role.toString());
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
	
}
