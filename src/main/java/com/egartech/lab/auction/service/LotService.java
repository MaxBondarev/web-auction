package com.egartech.lab.auction.service;

import com.egartech.lab.auction.dao.impl.hibernate.jpa.LotDAO;
import com.egartech.lab.auction.data.Lot;
import java.util.List;

/**
 * Class BetService provides CRUD operations for {@link Lot} objects.
 *
 * @author Max Bondarev.
 */
public class LotService {
    private static LotDAO lotDao;

    /**
     * Public {@link Lot} constructor.
     */
    public LotService() {
        lotDao = new LotDAO();
    }

    /**
     * Save {@link Lot}.
     * @param entity
     */
    public void save(Lot entity) {
        lotDao.openCurrentSessionwithTransaction();
        lotDao.save(entity);
        lotDao.closeCurrentSessionwithTransaction();
    }

    /**
     * Update {@link Lot}.
     * @param entity
     */
    public void update(Lot entity) {
        lotDao.openCurrentSessionwithTransaction();
        lotDao.update(entity);
        lotDao.closeCurrentSessionwithTransaction();
    }

    /**
     * Find {@link Lot} by id.
     * @param id
     */
    public Lot findById(String id) {
        lotDao.openCurrentSession();
        Lot lot = lotDao.findById(id, new Lot());
        lotDao.closeCurrentSession();
        return lot;
    }

    /**
     * Delete {@link Lot} by id.
     * @param id
     */
    public void delete(String id) {
        lotDao.openCurrentSessionwithTransaction();
        Lot lot = lotDao.findById(id, new Lot());
        lotDao.delete(lot);
        lotDao.closeCurrentSessionwithTransaction();
    }

    /**
     * Find all {@link Lot}s.
     */
    public List<Lot> findAll() {
        lotDao.openCurrentSession();
        List<Lot> lots = lotDao.findAll(new Lot());
        return lots;
    }

    /**
     * Delete all {@link Lot}s.
     */
    public void deleteAll() {
        lotDao.openCurrentSessionwithTransaction();
        lotDao.deleteAll(new Lot());
        lotDao.closeCurrentSessionwithTransaction();
    }
}
