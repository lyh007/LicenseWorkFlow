package com.lyh.licenseworkflow.dao.impl;

import com.lyh.licenseworkflow.dao.EnhancedHibernateDaoSupport;
import com.lyh.licenseworkflow.dao.IssueDao;
import com.lyh.licenseworkflow.po.Issue;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 工单DAO
 *
 * @author kevin
 * @version Revision: 1.00 Date: 11-9-26上午11:04
 * @Email liuyuhui007@gmail.com
 */
@Transactional
@Repository("issueDao")
public class IssueDaoImpl extends EnhancedHibernateDaoSupport<Issue> implements IssueDao {
    @Override
    protected String getEntityName() {
        return Issue.class.getName();
    }

    /**
     * 获取所有的请求
     *
     * @return 请求列表
     */
    public List<Issue> getAllIssues() {
        return getAllEntities();
    }
}
