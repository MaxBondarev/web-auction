package com.egartech.lab.auction.dao.impl.hibernate;

import com.egartech.lab.auction.dao.DAOAbstract;
import org.hibernate.Criteria;
import com.egartech.lab.auction.data.User;
import org.hibernate.criterion.Restrictions;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserDAO extends DAOAbstract<User, String> {

    public User findByLogin(String login) {
        Query q1 = em.createQuery("SELECT u FROM User u WHERE u.login = login");
        User user = (User) q1.getResultList().get(0);
        return user;
    }
}
