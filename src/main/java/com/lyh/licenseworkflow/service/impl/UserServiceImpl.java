package com.lyh.licenseworkflow.service.impl;

import com.lyh.licenseworkflow.dao.UserDao;
import com.lyh.licenseworkflow.po.User;
import com.lyh.licenseworkflow.service.UserService;
import com.lyh.licenseworkflow.system.engine.JBPMProcessTemplate;
import org.jbpm.api.ProcessDefinition;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.jnlp.ExtendedService;
import java.util.List;

/**
 * 用户Service
 *
 * @author kevin
 * @version Revision: 1.00 Date: 11-9-23上午11:07
 * @Email liuyuhui007@gmail.com
 */
@Service("userService")
@Transactional
public class UserServiceImpl extends JBPMProcessTemplate implements UserService {
    @Resource
    private UserDao userDao;

    /**
     * 保存用户
     *
     * @param user 用户信息
     * @return 数据库生成标识
     */
    public long save(User user) {
        return userDao.save(user);
    }

    /**
     * 更新用户信息
     *
     * @param user 用户信息
     */
    public void update(User user) {
        userDao.update(user);
    }

    /**
     * 删除用户
     *
     * @param id 要删除的对象标识
     */
    public void delete(Long id) {
        userDao.delete(id);
    }

    /**
     * 获取用户信息
     *
     * @param id 唯一标识
     * @return 用户对象
     */
    public User getById(Long id) {
        return userDao.getById(id);
    }

    /**
     * 获取所有的用户
     *
     * @return 用户列表
     */
    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    /**
     * 通过用户名获取用户信息
     *
     * @param name 用户名
     * @return 用户信息
     */
    public User getByName(String name) {
        User user = null;
        user = userDao.getByName(name);

        return user;
    }
}
