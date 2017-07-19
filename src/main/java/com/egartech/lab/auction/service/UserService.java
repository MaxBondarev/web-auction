package com.egartech.lab.auction.service;


import com.egartech.lab.auction.dao.UserDao;
import com.egartech.lab.auction.data.User;

import java.util.List;

public class UserService {
    private static UserDao userDao;

    public UserService() {
        userDao = new UserDao();
    }

    public void persist(User entity) {
        userDao.openCurrentSessionwithTransaction();
        userDao.persist(entity);
        userDao.closeCurrentSessionwithTransaction();
    }

    public void update(User entity) {
        userDao.openCurrentSessionwithTransaction();
        userDao.update(entity);
        userDao.closeCurrentSessionwithTransaction();
    }

    public User findById(String id) {
        userDao.openCurrentSession();
        User user = userDao.findById(id);
        userDao.closeCurrentSession();
        return user;
    }

    public User findByLogin(String login) {
        userDao.openCurrentSession();
        System.out.println("Session");
        User user = userDao.findByLogin(login);
        System.out.println("login");
        userDao.closeCurrentSession();
        System.out.println("CloseSession");
        return user;
    }

    public void delete(String id) {
        userDao.openCurrentSessionwithTransaction();
        User user = userDao.findById(id);
        userDao.delete(user);
        userDao.closeCurrentSessionwithTransaction();
    }

    public List<User> findAll() {
        userDao.openCurrentSession();
        List<User> users = userDao.findAll();
        userDao.closeCurrentSession();
        return users;
    }

    public void deleteAll() {
        userDao.openCurrentSessionwithTransaction();
        userDao.deleteAll();
        userDao.closeCurrentSessionwithTransaction();
    }

    public UserDao userDao() {
        return userDao;
    }

}
