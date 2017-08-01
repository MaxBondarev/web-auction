package com.egartech.lab.auction.dao;

import com.egartech.lab.auction.dao.impl.hibernate.DAOFactory;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.Serializable;
import java.util.List;

public abstract class DAOAbstract<T, Id extends Serializable> {
    
    private EntityManagerFactory emf;
    public EntityManager em;

    public EntityManager openCurrentSession() {
        emf = DAOFactory.createEMF();
        em = emf.createEntityManager();
        return em;
    }

    public EntityManager openCurrentSessionwithTransaction(){
        emf = DAOFactory.createEMF();
        em = emf.createEntityManager();
        em.getTransaction().begin();
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
