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
        lotDao.getTransaction();
        lotDao.save(entity);
        lotDao.commitTransaction();
    }

    /**
     * Update {@link Lot}.
     * @param entity
     */
    public void update(Lot entity) {
        lotDao.getTransaction();
        lotDao.update(entity);
        lotDao.commitTransaction();
    }

    /**
     * Find {@link Lot} by id.
     * @param id
     * @return lot
     */
    public Lot findById(String id) {
        lotDao.useEntityManager();
        Lot lot = lotDao.findById(id, new Lot());
        return lot;
    }

    /**
     * Delete {@link Lot} by id.
     * @param id
     */
    public void delete(String id) {
        lotDao.getTransaction();
        Lot lot = lotDao.findById(id, new Lot());
        lotDao.delete(lot);
        lotDao.commitTransaction();
    }

    /**
     * Find all {@link Lot}s.
     * @return lots
     */
    public List<Lot> findAll() {
        lotDao.useEntityManager();
        List<Lot> lots = lotDao.findAll(new Lot());
        return lots;
    }

    /**
     * Delete all {@link Lot}s.
     */
    public void deleteAll() {
        lotDao.getTransaction();
        lotDao.deleteAll(new Lot());
        lotDao.commitTransaction();
    }

    /**
     * Check is {@link Lot} name unique.
     * @param name
     * @return unique
     */
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
