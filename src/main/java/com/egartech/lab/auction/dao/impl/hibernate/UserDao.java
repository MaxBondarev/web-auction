package com.egartech.lab.auction.dao.impl.hibernate;

import java.util.List;
import com.egartech.lab.auction.HibernateUtil;
import com.egartech.lab.auction.dao.DaoInterface;
import com.egartech.lab.auction.data.Bet;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.egartech.lab.auction.data.User;
import org.hibernate.criterion.Restrictions;

public class UserDao extends DaoAbstract<User, String>  {

    public User findById(String id) {
        User user = (User) getCurrentSession().get(User.class, id);
        return user;
    }

    public User findByLogin(String login) {
        Criteria criteria = getCurrentSession().createCriteria(User.class);
        User user = (User) criteria.add(Restrictions.eq("login", login))
                .uniqueResult();
        return user;
    }

    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        List<User> users = (List<User>) getCurrentSession()
                .createQuery("from User").list();
        return users;
    }

    public void deleteAll() {
        List<User> entityList = findAll();
        for (User entity : entityList) {
            delete(entity);
        }
    }
}
