package com.egartech.lab.auction.dao;

import com.egartech.lab.auction.dao.impl.hibernate.DAOFactory;
import com.egartech.lab.auction.data.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

public abstract class DAOAbstract<T, Id extends Serializable> {
    private Session currentSession;
    private Transaction currentTransaction;
    private EntityManagerFactory emf;
    public EntityManager em;

/*
    public User getUser(String login, String pass) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.where(cb.equal(root.get("login"), login));
        if (pass != null) {
            query.where(cb.equal(root.get("pass"), pass));
        }
//		TypedQuery<User> q = em.createQuery("SELECT user FROM User user WHERE user.login = :login AND user.pass = :password", User.class);
//		q.setParameter("login", login);
//		q.setParameter("password", pass);
        TypedQuery<User> q = em.createQuery(query);
        List<User> result = q.getResultList();
        return !result.isEmpty() ? result.get(0) : null;
    }
    */

    public EntityManager openCurrentSession() {
        emf = DAOFactory.createEMF();
        em = emf.createEntityManager();
        //currentSession = HibernateUtil.startSessionFactory().openSession();
        return em;
    }

    public EntityManager openCurrentSessionwithTransaction(){
        emf = DAOFactory.createEMF();
        em = emf.createEntityManager();
        em.getTransaction().begin();
        //currentTransaction = currentSession.beginTransaction();
        return em;
    }

    public void closeCurrentSession() {
    }

    public void closeCurrentSessionwithTransaction() {
        em.getTransaction().commit();
    }

    public EntityManager getEM() {
        return em;
    }

    public void setEM(EntityManager em) {
        this.em = em;
    }

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }

    public void save(T entity) {
        getEM().persist(entity);
    }

    public void update(T entity) {
        getEM().merge(entity);
    }

    public T findById(Id id, T entity) {
        if(id.getClass() != Integer.class){
            Integer intId = Integer.parseInt((String) id);
            T bet = (T) getEM().find(entity.getClass(), intId);
            return bet;
        }
        T bet = (T) getEM().find(entity.getClass(), id);
        return bet;
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll(T entity) {
        List<T> entityList = (List<T>) getEM().createQuery(
                "SELECT u FROM " + entity.getClass().getSimpleName()
                        + " u").getResultList();
        return entityList;
    }

    public void delete(T entity) {
        getEM().remove(entity);
    }

    public void deleteAll(T t) {
        List<T> entityList = findAll(t);
        for (T entity : entityList) {
            delete(entity);
        }
    }
}
