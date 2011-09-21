package com.lyh.licenseworkflow.service.impl;

import com.lyh.licenseworkflow.dao.SystemDao;
import com.lyh.licenseworkflow.service.SystemService;
import org.jbpm.api.identity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author kevin
 * @version Revision: 1.00 Date: 11-9-21上午11:40
 * @Email liuyuhui007@gmail.com
 */
@Service("systemService")
@Transactional
public class SystemServiceImpl implements SystemService {

   @Resource
    private SystemDao systemDao;

    public List<User> queryAllUser() {
        return systemDao.queryAllUser();
    }
}
