package com.egartech.lab.auction.data;


public class User {
    private String login;
    private String password;

    public User(String login, String password) {
        setLogin(login);
        setPassword(password);
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
