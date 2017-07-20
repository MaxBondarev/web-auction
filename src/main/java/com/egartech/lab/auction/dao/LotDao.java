package com.egartech.lab.auction.dao;

import java.util.List;
import java.util.Map;

import com.egartech.lab.auction.data.Bet;
import com.egartech.lab.auction.service.BetService;
import com.egartech.lab.auction.service.LotService;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import com.egartech.lab.auction.data.Lot;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metamodel.domain.Entity;

import javax.persistence.*;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.metamodel.Metamodel;

public class LotDao implements LotDaoInterface<Lot, String>  {
    private Session currentSession;

    private Transaction currentTransaction;

    EntityManager em = new EntityManager() {
        public void persist(Object entity) {

        }

        public <T> T merge(T entity) {
            return null;
        }

        public void remove(Object entity) {

        }

        public <T> T find(Class<T> entityClass, Object primaryKey) {
            return null;
        }

        public <T> T find(Class<T> entityClass, Object primaryKey, Map<String, Object> properties) {
            return null;
        }

        public <T> T find(Class<T> entityClass, Object primaryKey, LockModeType lockMode) {
            return null;
        }

        public <T> T find(Class<T> entityClass, Object primaryKey, LockModeType lockMode, Map<String, Object> properties) {
            return null;
        }

        public <T> T getReference(Class<T> entityClass, Object primaryKey) {
            return null;
        }

        public void flush() {

        }

        public void setFlushMode(FlushModeType flushMode) {

        }

        public FlushModeType getFlushMode() {
            return null;
        }

        public void lock(Object entity, LockModeType lockMode) {

        }

        public void lock(Object entity, LockModeType lockMode, Map<String, Object> properties) {

        }

        public void refresh(Object entity) {

        }

        public void refresh(Object entity, Map<String, Object> properties) {

        }

        public void refresh(Object entity, LockModeType lockMode) {

        }

        public void refresh(Object entity, LockModeType lockMode, Map<String, Object> properties) {

        }

        public void clear() {

        }

        public void detach(Object entity) {

        }

        public boolean contains(Object entity) {
            return false;
        }

        public LockModeType getLockMode(Object entity) {
            return null;
        }

        public void setProperty(String propertyName, Object value) {

        }

        public Map<String, Object> getProperties() {
            return null;
        }

        public Query createQuery(String qlString) {
            return null;
        }

        public <T> TypedQuery<T> createQuery(CriteriaQuery<T> criteriaQuery) {
            return null;
        }

        public Query createQuery(CriteriaUpdate updateQuery) {
            return null;
        }

        public Query createQuery(CriteriaDelete deleteQuery) {
            return null;
        }

        public <T> TypedQuery<T> createQuery(String qlString, Class<T> resultClass) {
            return null;
        }

        public Query createNamedQuery(String name) {
            return null;
        }

        public <T> TypedQuery<T> createNamedQuery(String name, Class<T> resultClass) {
            return null;
        }

        public Query createNativeQuery(String sqlString) {
            return null;
        }

        public Query createNativeQuery(String sqlString, Class resultClass) {
            return null;
        }

        public Query createNativeQuery(String sqlString, String resultSetMapping) {
            return null;
        }

        public StoredProcedureQuery createNamedStoredProcedureQuery(String name) {
            return null;
        }

        public StoredProcedureQuery createStoredProcedureQuery(String procedureName) {
            return null;
        }

        public StoredProcedureQuery createStoredProcedureQuery(String procedureName, Class... resultClasses) {
            return null;
        }

        public StoredProcedureQuery createStoredProcedureQuery(String procedureName, String... resultSetMappings) {
            return null;
        }

        public void joinTransaction() {

        }

        public boolean isJoinedToTransaction() {
            return false;
        }

        public <T> T unwrap(Class<T> cls) {
            return null;
        }

        public Object getDelegate() {
            return null;
        }

        public void close() {

        }

        public boolean isOpen() {
            return false;
        }

        public EntityTransaction getTransaction() {
            return null;
        }

        public EntityManagerFactory getEntityManagerFactory() {
            return null;
        }

        public CriteriaBuilder getCriteriaBuilder() {
            return null;
        }

        public Metamodel getMetamodel() {
            return null;
        }

        public <T> EntityGraph<T> createEntityGraph(Class<T> rootType) {
            return null;
        }

        public EntityGraph<?> createEntityGraph(String graphName) {
            return null;
        }

        public EntityGraph<?> getEntityGraph(String graphName) {
            return null;
        }

        public <T> List<EntityGraph<? super T>> getEntityGraphs(Class<T> entityClass) {
            return null;
        }
    };

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


    public Integer findIdMaxPriceLotBet(Lot entity){
        System.out.println("findIdMaxPriceLotBet ");
        /*
        Integer intId = (Integer) em.createQuery("select b.id from new_schema.bets b where b.price=(SELECT MAX(b.price) FROM new_schema.bets b " +
                "where b.lot_id like :lotid)").getFirstResult();

        Integer intId = (Integer) em.createQuery("SELECT MAX(b.price) FROM new_schema.bets b " +
                "where b.lot_id like :lotid").getSingleResult();*/

        //Double intId = (Double) getCurrentSession().createQuery("select max(price) from Bet where lot_id=:lotid ")
        //Integer intId = (Integer) getCurrentSession().createQuery("select id from Bet where lot_id=:lotid and price=100 ")
        Integer intId = (Integer) getCurrentSession().createQuery("select id from Bet where lot_id=:lotid and price=(select max(price) from Bet where lot_id=:lotid) ")
        //Integer intId = (Integer) getCurrentSession().createQuery("select id, max(price) from Bet where lot_id=:lotid ")
                .setParameter("lotid", entity.getId()).list().get(0);

        //System.out.println("ejbql = " + getCurrentSession().createQuery("select id from Bet").getFirstResult().toString());
        System.out.println("intId = " + intId.toString());
        return intId;
        //return 3;
    }

    public void update(Lot entity) {
        System.out.println("update lot dao ");
        //EntityManager em = new En;

        //currentSession.createQuery("select ");
        /*
        Criteria criteria = currentSession
                .createCriteria(Bet.class).add(Restrictions.eq("lot_id", entity.getId()))
                .setProjection(Projections.max("price"));
        Bet maxPrice = (Bet)criteria.uniqueResult();
        System.out.println("maxPrice: " + maxPrice);
        entity.setBet(maxPrice);
        */


        BetService betService = new BetService();
        Bet bet = betService.findById(findIdMaxPriceLotBet(entity));
        System.out.println("update lot dao bet " + bet);
        entity.setBet(bet);
        System.out.println("update lot dao before entity update");
        getCurrentSession().update(entity);
        System.out.println("update lot dao after entity update");

    }

    public Lot findById(String id) {
        Lot lot = (Lot) getCurrentSession().get(Lot.class, Integer.parseInt(id));
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
