package com.lyh.licenseworkflow.service;

import com.lyh.licenseworkflow.po.Issue;
import com.lyh.licenseworkflow.system.BaseInterface;
import com.lyh.licenseworkflow.system.engine.JBPMService;
import org.jbpm.api.ProcessDefinition;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * 工单Service接口
 *
 * @author kevin
 * @version Revision: 1.00 Date: 11-9-26上午11:06
 * @Email liuyuhui007@gmail.com
 */
public interface IssueService extends BaseInterface<Issue>, JBPMService {
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
