package com.egartech.lab.auction.dao;

import java.util.List;

import com.egartech.lab.auction.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.egartech.lab.auction.data.User;
import org.hibernate.criterion.Restrictions;

public class UserDao implements UserDaoInterface<User, String>  {
    private Session currentSession;

    private Transaction currentTransaction;

    public UserDao() {
    }

    public Session openCurrentSession() {
        currentSession = HibernateUtil.startSessionFactory().openSession();
        return currentSession;
    }

    public Session openCurrentSessionwithTransaction() {
        currentSession = HibernateUtil.startSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void closeCurrentSession() {
        currentSession.close();
    }

    public void closeCurrentSessionwithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }


    public Session getCurrentSession() {
        return currentSession;
    }

    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }

    public void persist(User entity) {
        getCurrentSession().save(entity);
    }

    public void update(User entity) {
        getCurrentSession().update(entity);
    }

    public User findById(String id) {
        User user = (User) getCurrentSession().get(User.class, id);
        return user;
    }

    public User findByLogin(String login) {
        Criteria criteria = currentSession.createCriteria(User.class);
        User user = (User) criteria.add(Restrictions.eq("login", login))
                .uniqueResult();
        return user;
    }
    public void delete(User entity) {
        getCurrentSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        List<User> users = (List<User>) getCurrentSession().createQuery("from User").list();
        return users;
    }

    public void deleteAll() {
        List<User> entityList = findAll();
        for (User entity : entityList) {
            delete(entity);
        }
    }
}
