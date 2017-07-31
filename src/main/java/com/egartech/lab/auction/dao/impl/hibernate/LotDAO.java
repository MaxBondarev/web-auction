package com.egartech.lab.auction.dao.impl.hibernate;

import com.egartech.lab.auction.dao.DAOAbstract;
import com.egartech.lab.auction.data.Bet;
import com.egartech.lab.auction.service.BetService;
import com.egartech.lab.auction.data.Lot;


public class LotDAO extends DAOAbstract<Lot, String> {

    public Integer findIdMaxPriceLotBet(Lot entity) {
        Integer intId = (Integer) getEM().createQuery(
                "select id from Bet where lot_id=:lotid and price=" +
                "(select max(price) from Bet where lot_id=:lotid) ")
                .setParameter("lotid", entity.getId()).getResultList().get(0);
        return intId;
    }

    @Override
    public void update(Lot entity) {
        BetService betService = new BetService();
        Bet bet = betService.findById(findIdMaxPriceLotBet(entity));
        entity.setBet(bet);
        getEM().merge(entity);
    }
}
