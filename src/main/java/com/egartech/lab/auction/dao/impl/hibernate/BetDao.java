package com.egartech.lab.auction.dao.impl.hibernate;

import java.util.List;
import com.egartech.lab.auction.HibernateUtil;
import com.egartech.lab.auction.dao.DaoInterface;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.egartech.lab.auction.data.Bet;

/**
 * An ordered collection (also known as a <i>sequence</i>).  The user of this
 * interface has precise control over where in the list each element is
 * inserted.  The user can access elements by their integer index (position in
 * the list), and search for elements in the list.<p>
 *
 * Unlike sets, lists typically allow duplicate elements.  More formally,
 * lists typically allow pairs of elements <tt>e1</tt> and <tt>e2</tt>
 * such that <tt>e1.equals(e2)</tt>, and they typically allow multiple
 * null elements if they allow null elements at all.  It is not inconceivable
 * that someone might wish to implement a list that prohibits duplicates, by
 * throwing runtime exceptions when the user attempts to insert them, but we
 * expect this usage to be rare.<p>
 *
 *
 * <p>This interface is a member of the
 * <a href="{@docRoot}/../technotes/guides/collections/index.html">
 * Java Collections Framework</a>.
 *
 * @param <E> the type of elements in this list
 *
 * @author  Max Bondarev
 * @author  Neal Gafter
 * @see

 * @since 1.2
 */

public class BetDao implements DaoInterface<Bet, String> {
    private Session currentSession;
    private Transaction currentTransaction;

    public BetDao() {
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

    public void save(Bet entity) {
        getCurrentSession().save(entity);
    }

    public void update(Bet entity) {
        getCurrentSession().update(entity);
    }

    public Bet findById(String s) {
        return null;
    }

    public Bet findById(Integer id) {
        Bet bet = (Bet) getCurrentSession().get(Bet.class, id);
        return bet;
    }

    public void delete(Bet entity) {
        getCurrentSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    public List<Bet> findAll() {
        List<Bet> bets = (List<Bet>) getCurrentSession()
                .createQuery("from Bet").list();
        return bets;
    }

    public void deleteAll() {
        List<Bet> entityList = findAll();
        for (Bet entity : entityList) {
            delete(entity);
        }
    }
}
