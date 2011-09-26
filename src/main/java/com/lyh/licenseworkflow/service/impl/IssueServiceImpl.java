package com.lyh.licenseworkflow.service.impl;

import com.lyh.licenseworkflow.dao.IssueDao;
import com.lyh.licenseworkflow.po.Issue;
import com.lyh.licenseworkflow.service.IssueService;
import com.lyh.licenseworkflow.system.engine.JBPMProcessTemplate;
import org.jbpm.api.ProcessInstance;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 工单Service接口实现
 *
 * @author kevin
 * @version Revision: 1.00 Date: 11-9-26上午11:28
 * @Email liuyuhui007@gmail.com
 */
@Service("issueService")
@Transactional
public class IssueServiceImpl extends JBPMProcessTemplate implements IssueService {
    @Resource
    private IssueDao issueDao;

    @Override
    public long save(Issue issue) {
        return issueDao.save(issue);
    }

    @Override
    public void update(Issue issue) {
        issueDao.update(issue);
    }

    @Override
    public void delete(Long id) {
        issueDao.delete(id);
    }

    @Override
    public Issue getById(Long id) {
        return issueDao.getById(id);
    }

    /**
     * 获取所有的请求
     *
     * @return 请求列表
     */
    @Override
    public List<Issue> getAllIssues() {
        return issueDao.getAllIssues();
    }

    /**
     * 启动流程实例
     *
     * @param processDefinitionId 流程标识
     * @param variables           流程变量
     * @return 实例标识
     */
    public String start(String processDefinitionId, Map<String, Object> variables) {
        ProcessInstance processInstance = getExecutionService().startProcessInstanceById(processDefinitionId, variables);
        return processInstance.getId();
    }

    /**
     * 获取当前流程实例，执行到的节点
     *
     * @param processInstanceId 流程实例标识
     * @return 执行节点名称
     */
    public String getActiveName(String processInstanceId) {
        String activeName = "";
        ProcessInstance processInstance = getExecutionService().findProcessInstanceById(processInstanceId);
        if (processInstance != null) {
            Iterator it = processInstance.findActiveActivityNames().iterator();
            for (; it.hasNext(); ) {
                activeName += it.next();
            }
        }
        return activeName;
    }
}
