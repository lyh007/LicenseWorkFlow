package com.lyh.licenseworkflow.dao;

import com.lyh.licenseworkflow.po.User;
import com.lyh.licenseworkflow.system.BaseInterface;

import java.util.List;

/**
 * 用户DAO
 *
 * @author kevin
 * @version Revision: 1.00 Date: 11-9-22下午12:35
 * @Email liuyuhui007@gmail.com
 */
public interface UserDao extends BaseInterface<User> {
    /**
     * 获取所有的用户
     *
     * @return 用户列表
     */
    public List<User> getAllUsers();
     /**
     * 通过用户名获取用户信息
     *
     * @param name 用户名
     * @return 用户信息
     */
    public User getByName(String name);
}
