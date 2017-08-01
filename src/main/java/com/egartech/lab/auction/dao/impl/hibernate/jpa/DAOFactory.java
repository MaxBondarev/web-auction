package com.egartech.lab.auction.dao.impl.hibernate.jpa;

import org.junit.BeforeClass;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Class DAOFactory is a JPA DAO factory.
 *
 * @author Max Bondarev.
 */
public class DAOFactory {

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
}