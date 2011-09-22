package com.lyh.licenseworkflow.dao.impl;

import com.lyh.licenseworkflow.dao.EnhancedHibernateDaoSupport;
import com.lyh.licenseworkflow.dao.UserDao;
import com.lyh.licenseworkflow.po.User;
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
public class UserDaoImpl extends EnhancedHibernateDaoSupport<User> implements UserDao {
    @Override
    protected String getEntityName() {
        return User.class.getName();
    }

    public List<User> queryUsers() {
        return getHibernateTemplate().find("from User");
    }
}
