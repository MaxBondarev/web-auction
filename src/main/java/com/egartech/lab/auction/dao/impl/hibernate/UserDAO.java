package com.egartech.lab.auction.dao.impl.hibernate;

import org.hibernate.Criteria;
import com.egartech.lab.auction.data.User;
import org.hibernate.criterion.Restrictions;

public class UserDAO extends DAOAbstract<User, String> {

    public User findByLogin(String login) {
        Criteria criteria = getCurrentSession().createCriteria(User.class);
        User user = (User) criteria.add(Restrictions.eq("login", login))
                .uniqueResult();
        return user;
    }
}
