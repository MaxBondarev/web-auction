package com.egartech.lab.auction.dao.impl.hibernate;

import com.egartech.lab.auction.dao.DAOAbstract;
import com.egartech.lab.auction.dao.DAOAbstractFactory;
import com.egartech.lab.auction.dao.impl.hibernate.BetDAO;
import com.egartech.lab.auction.dao.impl.hibernate.LotDAO;
import com.egartech.lab.auction.dao.impl.hibernate.UserDAO;
import com.egartech.lab.auction.data.Lot;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.junit.BeforeClass;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DAOFactory extends DAOAbstractFactory {

    private static final String PROJECT_NAME = "web-auction";
    public static EntityManagerFactory entityManagerFactory;
    /**
     * Protected constructor.
     */
    public DAOFactory() {}

    public static EntityManagerFactory createEMF() {
        if (entityManagerFactory == null) {
            entityManagerFactory = DAOFactory.getEntityManagerFactory();
        }
        return entityManagerFactory;
    }
    @BeforeClass
    private static EntityManagerFactory getEntityManagerFactory() {
        entityManagerFactory = Persistence.createEntityManagerFactory(PROJECT_NAME);
        return entityManagerFactory;
    }
    /**
     * @see UserDAO
     */
    /*
    @Override
    public UserDAO getUserDAO() {
        return new UserDAO(entityManagerFactory.createEntityManager());
    }

    @Override
    public BetDAO getBetDAO() {
        return new BetDAO(entityManagerFactory.createEntityManager());
    }

    @Override
    public LotDAO getLotDAO() {
        return new LotDAO(entityManagerFactory.createEntityManager());
    }

    @Override
    public DAOAbstract<Lot, String> DAOAbstract() {
        return null;
    }
    */

}