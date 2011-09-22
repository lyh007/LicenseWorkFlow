package com.lyh.licenseworkflow.dao.impl;

import com.lyh.licenseworkflow.dao.UserDao;
import com.lyh.licenseworkflow.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author kevin
 * @version Revision: 1.00 Date: 11-9-22下午12:35
 * @Email liuyuhui007@gmail.com
 */
@Transactional
@Repository("userDao")
public class UserDaoImpl implements UserDao {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    public void save(User user) {
        hibernateTemplate.save(user);
    }

    public List<User> queryUsers() {
        return hibernateTemplate.find("from User");
    }

}
