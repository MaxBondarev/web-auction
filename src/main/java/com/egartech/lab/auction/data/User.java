package com.egartech.lab.auction.data;


public class User {
    private String login;
    private String password;

    public void User(String login, String password) {
        getLogin(login);
        getPassword(password);
    }

    public String getLogin(String login) {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword(String password) {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
