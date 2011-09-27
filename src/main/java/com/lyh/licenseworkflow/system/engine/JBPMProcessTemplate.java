package com.lyh.licenseworkflow.system.engine;

import org.jbpm.api.*;

import java.io.InputStream;

/**
 * JBPM模板类，系统初始化时已完成装配
 *
 * @author kevin
 * @version Revision: 1.00 Date: 11-9-22上午11:19
 * @Email liuyuhui007@gmail.com
 */
public class JBPMProcessTemplate {
    protected static ProcessEngine processEngine;
    //资源库服务
    protected static RepositoryService repositoryService;
    //执行服务
    protected static ExecutionService executionService;
    //  管理服务
    protected static ManagementService managementService;
    //任务服务
    protected static TaskService taskService;
    //历史服务
    protected static HistoryService historyService;
    //身份认证服务

    protected static IdentityService identityService;

    public void ininMethod() {
        this.repositoryService = processEngine.getRepositoryService();
        this.executionService = processEngine.getExecutionService();
        this.taskService = processEngine.getTaskService();
        this.historyService = processEngine.getHistoryService();
        this.managementService = processEngine.getManagementService();
        this.identityService = processEngine.getIdentityService();
    }

    public ProcessEngine getProcessEngine() {
        return processEngine;
    }

    public RepositoryService getRepositoryService() {
        if (repositoryService == null) {
            repositoryService = processEngine.getRepositoryService();
        }
        return repositoryService;
    }

    public ExecutionService getExecutionService() {
        if (executionService == null) {
            executionService = processEngine.getExecutionService();
        }
        return executionService;
    }

    public TaskService getTaskService() {
        if (taskService == null) {
            taskService = processEngine.getTaskService();
        }
        return taskService;
    }

    public HistoryService getHistoryService() {
        if (null == historyService)
            historyService = processEngine.getHistoryService();
        return historyService;
    }

    public ManagementService getManagementService() {
        if (null == managementService)
            managementService = processEngine.getManagementService();
        return managementService;
    }

    public IdentityService getIdentityService() {
        if (null == identityService)
            identityService = processEngine.getIdentityService();
        return identityService;
    }

    public void setProcessEngine(ProcessEngine processEngine) {
        this.processEngine = processEngine;
    }

    /**
     * 获取当前流程定义
     *
     * @return 流程定义信息
     */
    public ProcessDefinition getProcessDefinition() {
        return getRepositoryService().createProcessDefinitionQuery().list().get(0);
    }

    /**
     * 根据流程实例获取流程图片流
     *
     * @param processInstanceId 流程实例标识
     * @return 图片流
     */
    public InputStream getFlowchart(String processInstanceId) {
        //获取流程实列
        ProcessInstance processInstance = getExecutionService().findProcessInstanceById(processInstanceId);
        //获取流程定义标识
        String processDefinitionId = processInstance.getProcessDefinitionId();
        //获取流程定义实例
        ProcessDefinition processDefinition =getRepositoryService().createProcessDefinitionQuery().processDefinitionId(processDefinitionId).uniqueResult();
        return getRepositoryService().getResourceAsStream(processDefinition.getDeploymentId(),processDefinition.getImageResourceName());
    }
}
