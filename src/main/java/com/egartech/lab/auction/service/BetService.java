package com.egartech.lab.auction.service;


import com.egartech.lab.auction.dao.impl.hibernate.BetDao;
import com.egartech.lab.auction.data.Bet;
import com.egartech.lab.auction.data.Lot;

import java.util.List;

public class BetService {
    private static BetDao betDao;

    public BetService() {
        betDao = new BetDao();
    }

    public void save(Bet entity, Lot lot) {
        betDao.openCurrentSessionwithTransaction();
        betDao.save(entity);
        betDao.closeCurrentSessionwithTransaction();
        LotService lc = new LotService();
        lc.update(lot);
    }

    public void update(Bet entity) {
        betDao.openCurrentSessionwithTransaction();
        betDao.update(entity);
        betDao.closeCurrentSessionwithTransaction();
    }

    public Bet findById(Integer id) {
        betDao.openCurrentSession();
        Bet bet = betDao.findById(id);
        return bet;
    }

    public void delete(String id) {
        betDao.openCurrentSessionwithTransaction();
        Bet bet = betDao.findById(id);
        betDao.delete(bet);
        betDao.closeCurrentSessionwithTransaction();
    }

    public List<Bet> findAll() {
        betDao.openCurrentSession();
        List<Bet> bets = betDao.findAll();
        betDao.closeCurrentSession();
        return bets;
    }

    public void deleteAll() {
        betDao.openCurrentSessionwithTransaction();
        betDao.deleteAll();
        betDao.closeCurrentSessionwithTransaction();
    }

    public BetDao betDao() {
        return betDao;
    }
    
}
