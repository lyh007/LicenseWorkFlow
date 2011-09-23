package com.lyh.licenseworkflow.service;

import com.lyh.licenseworkflow.po.User;
import com.lyh.licenseworkflow.system.BaseInterface;
import org.jbpm.api.ProcessDefinition;
import org.subethamail.smtp.command.HelloCommand;

import java.util.List;

/**
 * 用户Service
 * @author kevin
 * @version Revision: 1.00 Date: 11-9-23上午11:06
 * @Email liuyuhui007@gmail.com
 */
public interface UserService extends BaseInterface<User> {
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

     /**
     * 获取当前流程定义
     *
     * @return 流程定义信息
     */
    public ProcessDefinition getProcessDefinition();
}
