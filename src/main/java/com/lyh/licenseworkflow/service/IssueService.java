package com.lyh.licenseworkflow.service;

import com.lyh.licenseworkflow.po.Issue;
import com.lyh.licenseworkflow.system.BaseInterface;

import java.util.List;
import java.util.Map;

/**
 * 工单Service接口
 * @author kevin
 * @version Revision: 1.00 Date: 11-9-26上午11:06
 * @Email liuyuhui007@gmail.com
 */
public interface IssueService extends BaseInterface<Issue> {
     /**
     * 获取所有的请求
     *
     * @return 请求列表
     */
    public List<Issue> getAllIssues();
      /**
     * 启动流程实例
     * @param processDefinitionId 流程标识
     * @param variables 流程变量
     * @return 实例标识
     */
    public String start(String processDefinitionId, Map<String, Object> variables);
    /**
     * 获取当前流程实例，执行到的节点
     *
     * @param processInstanceId 流程实例标识
     * @return 执行节点名称
     */
    public String getActiveName(String processInstanceId);
}
