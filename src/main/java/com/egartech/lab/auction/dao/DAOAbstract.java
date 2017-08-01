package com.egartech.lab.auction.dao;

import com.egartech.lab.auction.dao.impl.hibernate.jpa.DAOFactory;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.Serializable;
import java.util.List;

/**
 * Class DAOAbstract contains implementation of general JPA DAO operations.
 *
 * @author Max Bondarev.
 */
public abstract class DAOAbstract<T, Id extends Serializable> {
    
    private EntityManagerFactory emf;
    public EntityManager em;

    /**
     * Get EntityManager for using
     */
    public EntityManager useEntityManager() {
        emf = DAOFactory.getEMF();
        em = emf.createEntityManager();
        return em;
    }

    /**
     * Get Transaction of EntityManager
     */
    public EntityManager getTransaction(){
        emf = DAOFactory.getEMF();
        em = emf.createEntityManager();
        em.getTransaction().begin();
        return em;
    }

    /**
     * Commit Transaction of EntityManager
     */
    public void commitTransaction() {
        em.getTransaction().commit();
    }

    public EntityManager getEM() {
        return em;
    }

    /**
     * Set em
     * @param em
     *      instance EntityManager
     */
    public void setEM(EntityManager em) {
        this.em = em;
    }

    /**
     * @see DaoInterface
     */
    public void save(T entity) {
        getEM().persist(entity);
    }

    /**
     * @see DaoInterface
     */
    public void update(T entity) {
        getEM().merge(entity);
    }

    /**
     * @see DaoInterface
     */
    public T findById(Id id, T entity) {
        if(id.getClass() != Integer.class){
            Integer intId = Integer.parseInt((String) id);
            T bet = (T) getEM().find(entity.getClass(), intId);
            return bet;
        }
        T bet = (T) getEM().find(entity.getClass(), id);
        return bet;
    }

    /**
     * @see DaoInterface
     */
    @SuppressWarnings("unchecked")
    public List<T> findAll(T entity) {
        List<T> entityList = (List<T>) getEM().createQuery(
                "SELECT u FROM " + entity.getClass().getSimpleName()
                        + " u").getResultList();
        return entityList;
    }

    /**
     * @see DaoInterface
     */
    public void delete(T entity) {
        getEM().remove(entity);
    }

    /**
     * @see DaoInterface
     */
    public void deleteAll(T t) {
        List<T> entityList = findAll(t);
        for (T entity : entityList) {
            delete(entity);
        }
    }
}
