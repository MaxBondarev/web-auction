package com.egartech.lab.auction.dao.impl.hibernate.jpa;

import com.egartech.lab.auction.dao.DAOAbstract;
import com.egartech.lab.auction.data.User;
import javax.persistence.Query;

/**
 * Class UserDAO is a JPA {@link User} DAO.
 *
 * @author Max Bondarev.
 */
public class UserDAO extends DAOAbstract<User, String> {

    /**
     * Find {@link User} by login.
     * @param login
     * @return user with login or user = null
     */
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
