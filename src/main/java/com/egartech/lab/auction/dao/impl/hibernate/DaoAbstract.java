package com.egartech.lab.auction.dao.impl.hibernate;


import com.egartech.lab.auction.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.List;

public abstract class DaoAbstract<T, Id extends Serializable> {
    private Session currentSession;
    private Transaction currentTransaction;

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

    public void save(T entity) {
        getCurrentSession().save(entity);
    }

    public void update(T entity) {
        getCurrentSession().update(entity);
    }

    public T findById(Id id, T entity) {
        if(id.getClass() != Integer.class){
            Integer intId = Integer.parseInt((String) id);
            T bet = (T) getCurrentSession().get(entity.getClass().getName(), intId);
            return bet;
        }
        T bet = (T) getCurrentSession().get(entity.getClass().getName(), id);
        return bet;
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll(T entity) {
        List<T> users = (List<T>) getCurrentSession()
                .createQuery("from " + entity.getClass().getSimpleName()).list();
        return users;
    }

    public void delete(T entity) {
        getCurrentSession().delete(entity);
    }

    public void deleteAll(T t) {
        List<T> entityList = findAll(t);
        for (T entity : entityList) {
            delete(entity);
        }
    }
}
