package com.lyh.licenseworkflow.system.engine;

import org.jbpm.api.*;

import java.io.InputStream;
import java.util.Map;

/**
 * JBPM Service
 *
 * @author kevin
 * @version Revision: 1.00 Date: 11-9-30下午3:49
 * @Email liuyuhui007@gmail.com
 */
public interface JBPMService {
    //资源库服务
    public RepositoryService getRepositoryService();

    //执行服务
    public ExecutionService getExecutionService();

    //  管理服务
    public ManagementService getManagementService();

    //任务服务
    public TaskService getTaskService();

    //历史服务
    public HistoryService getHistoryService();

    //身份认证服务
    public IdentityService getIdentityService();

    /**
     * 启动流程实例
     *
     * @param processDefinitionId 流程标识
     * @param variables           流程变量
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

    /**
     * 根据流程实例获取流程图片流
     *
     * @param processInstanceId 流程实例标识
     * @return 图片流
     */
    public InputStream getFlowchart(String processInstanceId);

    /**
     * 通过流程实例标识获取流程定义对象
     *
     * @param processInstanceId 流程实例标识
     * @return 流程实例对象
     */
    public ProcessDefinition getProcessDefinitionByInstanceId(String processInstanceId);
}

