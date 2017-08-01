package com.egartech.lab.auction.dao.impl.hibernate;

import com.egartech.lab.auction.dao.DAOAbstract;
import com.egartech.lab.auction.data.User;

import javax.persistence.Query;

public class UserDAO extends DAOAbstract<User, String> {

    public User findByLogin(String login) {
        User user = null;
        Query query = em.createQuery("SELECT u FROM User u where u.login = :login");
        query.setParameter("login", login);
        if (query.getResultList().size() > 0) {
            user = (User) query.getResultList().get(0);
        }
        return user;
    }
}
