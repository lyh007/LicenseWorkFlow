package com.lyh.licenseworkflow.dao.impl;

import com.lyh.licenseworkflow.dao.SystemDao;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.identity.User;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author kevin
 * @version Revision: 1.00 Date: 11-9-21上午11:37
 * @Email liuyuhui007@gmail.com
 */
@Repository("systemDao")
public class SystemDaoImpl implements SystemDao {
    @Resource
    private ProcessEngine processEngine;

    public List<User> queryAllUser() {
        List<User> users = processEngine.getIdentityService().findUsers();
        return users;
    }

    public ProcessEngine getProcessEngine() {
        return processEngine;
    }

    public void setProcessEngine(ProcessEngine processEngine) {
        this.processEngine = processEngine;
    }
}
