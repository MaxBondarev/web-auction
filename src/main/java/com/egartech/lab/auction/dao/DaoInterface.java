package com.egartech.lab.auction.dao;

import java.io.Serializable;
import java.util.List;

/**
 * DaoInterface provides interface for DAO operations.
 *
 * @author Max Bondarev.
 */
public interface DaoInterface<T, Id extends Serializable>  {

    public void save(T entity);

    public void update(T entity);

    public T findById(Id id);

    public void delete(T entity);

    public List<T> findAll();

    public void deleteAll();
}
