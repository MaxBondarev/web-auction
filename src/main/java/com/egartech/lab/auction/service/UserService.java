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
        userDao.openCurrentSessionwithTransaction();
        userDao.save(entity);
        userDao.closeCurrentSessionwithTransaction();
    }

    /**
     * Update {@link User}.
     * @param entity
     */
    public void update(User entity) {
        userDao.openCurrentSessionwithTransaction();
        userDao.update(entity);
        userDao.closeCurrentSessionwithTransaction();
    }

    /**
     * Find {@link User} by id.
     * @param id
     */
    public User findById(String id) {
        userDao.openCurrentSession();
        User user = userDao.findById(id, new User());
        userDao.closeCurrentSession();
        return user;
    }

    /**
     * Find {@link User} by login.
     * @param login
     */
    public User findByLogin(String login) {
        userDao.openCurrentSession();
        User user = userDao.findByLogin(login);
        userDao.closeCurrentSession();
        return user;
    }

    /**
     * Delete {@link User} by id.
     * @param id
     */
    public void delete(String id) {
        userDao.openCurrentSessionwithTransaction();
        User user = userDao.findById(id, new User());
        userDao.delete(user);
        userDao.closeCurrentSessionwithTransaction();
    }

    /**
     * Find all {@link User}s.
     */
    public List<User> findAll() {
        userDao.openCurrentSession();
        List<User> users = userDao.findAll(new User());
        userDao.closeCurrentSession();
        return users;
    }

    /**
     * Delete all {@link User}s.
     */
    public void deleteAll() {
        userDao.openCurrentSessionwithTransaction();
        userDao.deleteAll(new User());
        userDao.closeCurrentSessionwithTransaction();
    }
}
