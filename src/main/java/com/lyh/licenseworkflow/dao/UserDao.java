package com.lyh.licenseworkflow.dao;

import com.lyh.licenseworkflow.po.User;
import com.lyh.licenseworkflow.system.BaseInterface;

import java.util.List;

/**
 * @author kevin
 * @version Revision: 1.00 Date: 11-9-22下午12:35
 * @Email liuyuhui007@gmail.com
 */
public interface UserDao extends BaseInterface<User> {
    public List<User> queryUsers();
}
