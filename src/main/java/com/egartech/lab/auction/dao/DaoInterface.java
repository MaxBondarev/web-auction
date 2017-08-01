package com.egartech.lab.auction.dao;

import java.io.Serializable;
import java.util.List;

/**
 * DaoInterface provides interface for DAO operations.
 *
 * @author Max Bondarev.
 */
public interface DaoInterface<T, Id extends Serializable>  {

    /**
     * Save entity
     * @param entity
     */
    public void save(T entity);

    /**
     * Update entity
     * @param entity
     */
    public void update(T entity);

    /**
     * Find entity by id
     * @param id
     */
    public T findById(Id id);

    /**
     * Delete entity
     * @param entity
     */
    public void delete(T entity);

    /**
     * Find all entities
     */
    public List<T> findAll();

    /**
     * Find all entities
     */
    public void deleteAll();
}
