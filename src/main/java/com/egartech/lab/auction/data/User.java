package com.egartech.lab.auction.data;

import javax.persistence.*;
import java.util.Set;

/**
 * Class User contains user login and password.
 *
 * @author Max Bondarev.
 */
@Entity
@Table(name = "users", schema = "new_schema")
public class User {
    private int id;
    private String login;
    private String password;
    private Set<Bet> userBets;

    public User(){
    }

    public User(String login, String password){
        setLogin(login);
        setPassword(password);
    }
    /**
     * id - User id from database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    /**
     * id - User id from database.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * login - User's login.
     */
    @Basic
    @Column(name = "login")
    public String getLogin() {
        return login;
    }

    /**
     * login - User's login.
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * password - User's password.
     */
    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    /**
     * password - User's password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * userBets - Set of user's {@link Bet}.
     */
    @OneToMany
    @JoinColumn(name = "user_id", nullable = true)
    public Set<Bet> getUserBets() {
        return userBets;
    }

    /**
     * userBets - Set of user's {@link Bet}.
     */
    public void setUserBets(Set<Bet> bets) {
        this.userBets = userBets;
    }
}
