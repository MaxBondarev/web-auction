package com.egartech.lab.auction;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {

    static boolean isSessionCreate = false;
    static SessionFactory sessionFactory;

    public static SessionFactory startSessionFactory() {
        if (isSessionCreate) {
            return sessionFactory;
        } else {
            sessionFactory = getSessionFactory();
            isSessionCreate = true;
            return sessionFactory;
        }
    }

    private static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure();
        StandardServiceRegistryBuilder builder
                = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration
                .buildSessionFactory(builder.build());
        return sessionFactory;
    }

    public static void closeSessionFactory() {
        sessionFactory.close();
        isSessionCreate = false;
    }

}
