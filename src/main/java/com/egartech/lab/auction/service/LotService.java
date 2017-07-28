package com.egartech.lab.auction.service;

import com.egartech.lab.auction.dao.impl.hibernate.LotDao;
import com.egartech.lab.auction.data.Lot;
import java.util.List;

public class LotService {
    private static LotDao lotDao;

    public LotService() {
        lotDao = new LotDao();
    }

    public void save(Lot entity) {
        lotDao.openCurrentSessionwithTransaction();
        lotDao.save(entity);
        lotDao.closeCurrentSessionwithTransaction();
    }

    public void update(Lot entity) {
        lotDao.openCurrentSessionwithTransaction();
        lotDao.update(entity);
        lotDao.closeCurrentSessionwithTransaction();
    }

    public Lot findById(String id) {
        lotDao.openCurrentSession();
        Lot lot = lotDao.findById(id, new Lot());
        lotDao.closeCurrentSession();
        return lot;
    }

    public void delete(String id) {
        lotDao.openCurrentSessionwithTransaction();
        Lot lot = lotDao.findById(id, new Lot());
        lotDao.delete(lot);
        lotDao.closeCurrentSessionwithTransaction();
    }

    public List<Lot> findAll() {
        lotDao.openCurrentSession();
        List<Lot> lots = lotDao.findAll(new Lot());
        return lots;
    }

    public void deleteAll() {
        lotDao.openCurrentSessionwithTransaction();
        lotDao.deleteAll(new Lot());
        lotDao.closeCurrentSessionwithTransaction();
    }

    public LotDao lotDao() {
        return lotDao;
    }

    public boolean isNameUnique(String name) {
        boolean unique = true;
        List<Lot> lots = findAll();
        for (Lot lot: lots) {
            if (lot.getName().equals(name)) {
                unique = false;
            };
        }
        return unique;
    }
}
