package com.lyh.licenseworkflow.dao;

import com.lyh.licenseworkflow.po.Issue;
import com.lyh.licenseworkflow.system.BaseInterface;

import java.util.List;

/**
 * @author kevin
 * @version Revision: 1.00 Date: 11-9-26上午11:04
 * @Email liuyuhui007@gmail.com
 */
public interface IssueDao extends BaseInterface<Issue> {
     /**
     * 获取所有的请求
     *
     * @return 请求列表
     */
    public List<Issue> getAllIssues();
     /**
     * 更新或保存单个工单
     *
     * @param issue 实体
     */
    public void saveOrUpdate(Issue issue);
}
