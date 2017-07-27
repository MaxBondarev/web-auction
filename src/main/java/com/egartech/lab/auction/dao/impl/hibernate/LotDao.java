package com.egartech.lab.auction.dao.impl.hibernate;

import java.util.List;
import com.egartech.lab.auction.HibernateUtil;
import com.egartech.lab.auction.dao.DaoInterface;
import com.egartech.lab.auction.data.Bet;
import com.egartech.lab.auction.service.BetService;
import org.hibernate.*;
import com.egartech.lab.auction.data.Lot;


public class LotDao extends DaoAbstract<Lot, String>  {


    public Integer findIdMaxPriceLotBet(Lot entity) {
        Integer intId = (Integer) getCurrentSession().createQuery(
                "select id from Bet where lot_id=:lotid and price=" +
                "(select max(price) from Bet where lot_id=:lotid) ")
                .setParameter("lotid", entity.getId()).list().get(0);
        return intId;
    }

    @Override
    public void update(Lot entity) {
        BetService betService = new BetService();
        Bet bet = betService.findById(findIdMaxPriceLotBet(entity));
        entity.setBet(bet);
        getCurrentSession().update(entity);
    }

    public Lot findById(String id) {
        Lot lot = (Lot) getCurrentSession().get(Lot.class,
                Integer.parseInt(id));
        return lot;
    }


    @SuppressWarnings("unchecked")
    public List<Lot> findAll() {
        List<Lot> lots = (List<Lot>) getCurrentSession()
                .createQuery("from Lot").list();
        return lots;
    }

    public void deleteAll() {
        List<Lot> entityList = findAll();
        for (Lot entity : entityList) {
            delete(entity);
        }
    }
}
