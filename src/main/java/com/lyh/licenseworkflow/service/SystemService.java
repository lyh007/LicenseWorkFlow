package com.lyh.licenseworkflow.service;

import org.jbpm.api.identity.User;

import java.util.List;

/**
 * @author kevin
 * @version Revision: 1.00 Date: 11-9-21上午11:39
 * @Email liuyuhui007@gmail.com
 */
public interface SystemService {
    /**
     * 获取系统中所有的用户
     *
     * @return 用户列表
     */
    public List<User> queryAllUser();
}
