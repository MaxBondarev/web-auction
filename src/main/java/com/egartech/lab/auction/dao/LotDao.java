package com.egartech.lab.auction.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import com.egartech.lab.auction.data.Lot;

public class LotDao implements LotDaoInterface<Lot, String>  {
    private Session currentSession;

    private Transaction currentTransaction;

    public LotDao() {
    }

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

    public void persist(Lot entity) {
        getCurrentSession().save(entity);
    }

    public void update(Lot entity) {
        getCurrentSession().update(entity);
    }

    public Lot findById(String id) {
        Lot lot = (Lot) getCurrentSession().get(Lot.class, id);
        return lot;
    }

    public void delete(Lot entity) {
        getCurrentSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    public List<Lot> findAll() {
        List<Lot> lots = (List<Lot>) getCurrentSession().createQuery("from Lot").list();
        return lots;
    }

    public void deleteAll() {
        List<Lot> entityList = findAll();
        for (Lot entity : entityList) {
            delete(entity);
        }
    }
}
