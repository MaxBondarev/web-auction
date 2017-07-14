package com.egartech.lab.auction.service;


import com.egartech.lab.auction.dao.LotDao;
import com.egartech.lab.auction.data.Lot;

import java.util.List;

public class LotService {
    private static LotDao lotDao;

    public LotService() {
        lotDao = new LotDao();
    }

    public void persist(Lot entity) {
        lotDao.openCurrentSessionwithTransaction();
        lotDao.persist(entity);
        lotDao.closeCurrentSessionwithTransaction();
    }

    public void update(Lot entity) {
        lotDao.openCurrentSessionwithTransaction();
        lotDao.update(entity);
        lotDao.closeCurrentSessionwithTransaction();
    }

    public Lot findById(String id) {
        lotDao.openCurrentSession();
        Lot book = lotDao.findById(id);
        lotDao.closeCurrentSession();
        return book;
    }

    public void delete(String id) {
        lotDao.openCurrentSessionwithTransaction();
        Lot book = lotDao.findById(id);
        lotDao.delete(book);
        lotDao.closeCurrentSessionwithTransaction();
    }

    public List<Lot> findAll() {
        lotDao.openCurrentSession();
        List<Lot> books = lotDao.findAll();
        lotDao.closeCurrentSession();
        return books;
    }

    public void deleteAll() {
        lotDao.openCurrentSessionwithTransaction();
        lotDao.deleteAll();
        lotDao.closeCurrentSessionwithTransaction();
    }

    public LotDao lotDao() {
        return lotDao;
    }
}
