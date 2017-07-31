package com.egartech.lab.auction.dao.impl.hibernate;

import com.egartech.lab.auction.dao.DAOAbstract;
import com.egartech.lab.auction.data.User;

import javax.persistence.Query;

public class UserDAO extends DAOAbstract<User, String> {

    public User findByLogin(String login) {

        System.out.println("########################");
        System.out.println(login);
        Query query = em.createQuery("SELECT u FROM User u where u.login = :login");
        query.setParameter("login", login);
        //System.out.println(query.toString());
                        User user = (User) query.getSingleResult();
        System.out.println("user = " + user + ", password = " + user.getPassword());
            //System.out.println(user.getLogin());

        return user;
    }


}
