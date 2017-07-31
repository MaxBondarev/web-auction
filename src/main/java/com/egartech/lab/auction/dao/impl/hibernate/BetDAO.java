package com.egartech.lab.auction.dao.impl.hibernate;

import com.egartech.lab.auction.dao.DAOAbstract;
import com.egartech.lab.auction.data.Bet;

import javax.persistence.EntityManager;

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

public class BetDAO extends DAOAbstract<Bet, String> {

}
