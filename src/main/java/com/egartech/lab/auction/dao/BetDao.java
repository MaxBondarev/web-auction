package com.egartech.lab.auction.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.egartech.lab.auction.data.Bet;

public class BetDao implements BetDaoInterface<Bet, String>  {
    private Session currentSession;

    private Transaction currentTransaction;

    public BetDao() {
    }

    public Session openCurrentSession() {
        currentSession = getSessionFactory().openSession();
        return currentSession;
    }

    public Session openCurrentSessionwithTransaction() {
        currentSession = getSessionFactory().openSession();
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

    private static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
        return sessionFactory;
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

    public void persist(Bet entity) {
        getCurrentSession().save(entity);
    }

    public void update(Bet entity) {
        getCurrentSession().update(entity);
    }

    public Bet findById(String id) {
        Bet bet = (Bet) getCurrentSession().get(Bet.class, id);
        return bet;
    }

    public void delete(Bet entity) {
        getCurrentSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    public List<Bet> findAll() {
        List<Bet> bets = (List<Bet>) getCurrentSession().createQuery("from Bet").list();
        return bets;
    }

    public void deleteAll() {
        List<Bet> entityList = findAll();
        for (Bet entity : entityList) {
            delete(entity);
        }
    }
}
