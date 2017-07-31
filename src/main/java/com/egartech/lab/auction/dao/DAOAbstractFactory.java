package com.egartech.lab.auction.dao;

import com.egartech.lab.auction.dao.impl.hibernate.BetDAO;
import com.egartech.lab.auction.dao.impl.hibernate.DAOFactory;
import com.egartech.lab.auction.dao.impl.hibernate.LotDAO;
import com.egartech.lab.auction.dao.impl.hibernate.UserDAO;
import com.egartech.lab.auction.data.Lot;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class DAOAbstractFactory {

    private static DAOAbstractFactory instance;
    private static final String PROJECT_NAME = "web-auction";
    static EntityManagerFactory entityManagerFactory;
    /**
     * Protected constructor.
     */
    protected DAOAbstractFactory() {}


    
    /**
     * Clone singleton fix.
     */
    @Override
    protected final Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
/*    /**
     * Returns user DAO.
     *
     * @return {@link UserDAO}
     */
    //public abstract UserDAO getUserDAO();

    /**
     * Returns user DAO.
     *
     * @return {@link UserDAO}
     */
    //public abstract BetDAO getBetDAO();

    /**
     * Returns user DAO.
     *
     * @return {@link UserDAO}
     */
    //public abstract LotDAO getLotDAO();

    //public abstract DAOAbstract<Lot, String> DAOAbstract();

}
