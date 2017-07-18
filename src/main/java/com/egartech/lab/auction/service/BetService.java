package com.egartech.lab.auction.service;


import com.egartech.lab.auction.dao.BetDao;
import com.egartech.lab.auction.data.Bet;

import java.util.List;

public class BetService {
    private static BetDao betDao;

    public BetService() {
        betDao = new BetDao();
    }

    public void persist(Bet entity) {
        betDao.openCurrentSessionwithTransaction();
        betDao.persist(entity);
        betDao.closeCurrentSessionwithTransaction();
    }

    public void update(Bet entity) {
        betDao.openCurrentSessionwithTransaction();
        betDao.update(entity);
        betDao.closeCurrentSessionwithTransaction();
    }

    public Bet findById(String id) {
        betDao.openCurrentSession();
        Bet bet = betDao.findById(id);
        betDao.closeCurrentSession();
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
