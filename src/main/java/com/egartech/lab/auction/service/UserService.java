package com.egartech.lab.auction.service;

import com.egartech.lab.auction.dao.impl.hibernate.jpa.UserDAO;
import com.egartech.lab.auction.data.User;

import java.util.List;

/**
 * Class BetService provides CRUD operations for {@link User} objects.
 *
 * @author Max Bondarev.
 */
public class UserService {
    private static UserDAO userDao;

    /**
     * Public {@link User} constructor.
     */
    public UserService() {
        userDao = new UserDAO();
    }

    /**
     * Save {@link User}.
     * @param entity
     */
    public void save(User entity) {
        userDao.getTransaction();
        userDao.save(entity);
        userDao.commitTransaction();
    }

    /**
     * Update {@link User}.
     * @param entity
     */
    public void update(User entity) {
        userDao.getTransaction();
        userDao.update(entity);
        userDao.commitTransaction();
    }

    /**
     * Find {@link User} by id.
     * @param id
     * @return user
     */
    public User findById(String id) {
        userDao.useEntityManager();
        User user = userDao.findById(id, new User());
        return user;
    }

    /**
     * Find {@link User} by login.
     * @param login
     * @return user
     */
    public User findByLogin(String login) {
        userDao.useEntityManager();
        User user = userDao.findByLogin(login);
        return user;
    }

    /**
     * Delete {@link User} by id.
     * @param id
     */
    public void delete(String id) {
        userDao.getTransaction();
        User user = userDao.findById(id, new User());
        userDao.delete(user);
        userDao.commitTransaction();
    }

    /**
     * Find all {@link User}s.
     * @return users
     */
    public List<User> findAll() {
        userDao.useEntityManager();
        List<User> users = userDao.findAll(new User());
        return users;
    }

    /**
     * Delete all {@link User}s.
     */
    public void deleteAll() {
        userDao.getTransaction();
        userDao.deleteAll(new User());
        userDao.commitTransaction();
    }
}
