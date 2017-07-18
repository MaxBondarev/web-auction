package com.egartech.lab.auction.data;



import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "users", schema = "auctiondb", catalog = "")
public class User {
    private int id;
    private String login;
    private String password;
    private Set<Bet> userBets;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Basic
    @Column(name = "login")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @OneToMany//(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = true)
    public Set<Bet> getUserBets() {
        return userBets;
    }

    public void setUserBets(Set<Bet> bets) {
        this.userBets = userBets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User that = (User) o;

        if (id != that.id) return false;
        if (login != null ? !login.equals(that.login) : that.login != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

}
