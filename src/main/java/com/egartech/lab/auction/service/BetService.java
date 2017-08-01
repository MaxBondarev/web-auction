package com.egartech.lab.auction.service;

import com.egartech.lab.auction.dao.impl.hibernate.jpa.BetDAO;
import com.egartech.lab.auction.data.Bet;
import com.egartech.lab.auction.data.Lot;

import java.util.List;

/**
 * Class BetService provides CRUD operations for {@link Bet} objects.
 *
 * @author Max Bondarev.
 */
public class BetService {
    private static BetDAO betDao;

    /**
     * Public {@link Bet} constructor.
     */
    public BetService() {
        betDao = new BetDAO();
    }

    /**
     * Save {@link Bet}.
     * @param entity
     */
    public void save(Bet entity, Lot lot) {
        betDao.getTransaction();
        betDao.save(entity);
        betDao.commitTransaction();
        LotService lc = new LotService();
        lc.update(lot);
    }

    /**
     * Update {@link Bet}.
     * @param entity
     */
    public void update(Bet entity) {
        betDao.getTransaction();
        betDao.update(entity);
        betDao.commitTransaction();
    }

    /**
     * Find {@link Bet} by id.
     * @param id
     */
    public Bet findById(Integer id) {
        betDao.useEntityManager();
        Bet bet = betDao.findById(String.valueOf(id), new Bet());
        return bet;
    }

    /**
     * Delete {@link Bet} by id.
     * @param id
     */
    public void delete(String id) {
        betDao.getTransaction();
        Bet bet = betDao.findById(id, new Bet());
        betDao.delete(bet);
        betDao.commitTransaction();
    }

    /**
     * Find all {@link Bet}s.
     */
    public List<Bet> findAll() {
        betDao.useEntityManager();
        List<Bet> bets = betDao.findAll(new Bet());
        return bets;
    }

    /**
     * Delete all {@link Bet}s.
     */
    public void deleteAll() {
        betDao.getTransaction();
        betDao.deleteAll(new Bet());
        betDao.commitTransaction();
    }
}
