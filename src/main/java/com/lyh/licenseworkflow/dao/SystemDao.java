package com.lyh.licenseworkflow.dao;

import org.jbpm.api.identity.User;

import java.util.List;

/**
 * @author kevin
 * @version Revision: 1.00 Date: 11-9-21上午11:35
 * @Email liuyuhui007@gmail.com
 */
public interface SystemDao {
    public List<User> queryAllUser();
}
