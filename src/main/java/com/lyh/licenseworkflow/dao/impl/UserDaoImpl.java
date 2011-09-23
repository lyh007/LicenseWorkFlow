package com.lyh.licenseworkflow.dao.impl;

import com.lyh.licenseworkflow.dao.EnhancedHibernateDaoSupport;
import com.lyh.licenseworkflow.dao.UserDao;
import com.lyh.licenseworkflow.po.User;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户DAO实现
 *
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

    /**
     * 获取所有的用户
     *
     * @return 用户列表
     */
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        users = (List<User>) getHibernateTemplate().find("from User");
        if (users != null && users.size() > 0) {
            for (User user : users) {
                Hibernate.initialize(user.getGroups());
            }
        }
        return users;
    }
}
