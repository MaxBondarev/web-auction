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
     * Public constructor.
     */
    public DAOFactory() {}

    /**
     * Get EntityManagerFactory if does not exist.
     * @return entityManagerFactory instance
     */
    public static EntityManagerFactory getEMF() {
        if (entityManagerFactory == null) {
            entityManagerFactory = DAOFactory.createEntityManagerFactory();
        }
        return entityManagerFactory;
    }

    /**
     * Create EntityManagerFactory.
     * @return entityManagerFactory instance
     */
    @BeforeClass
    private static EntityManagerFactory createEntityManagerFactory() {
        entityManagerFactory = Persistence.createEntityManagerFactory(PROJECT_NAME);
        return entityManagerFactory;
    }
}