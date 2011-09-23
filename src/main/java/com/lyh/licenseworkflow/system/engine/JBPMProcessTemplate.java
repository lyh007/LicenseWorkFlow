package com.lyh.licenseworkflow.system.engine;

import org.jbpm.api.*;

/**
 * JBPM模板类，系统初始化时已完成装配
 *
 * @author kevin
 * @version Revision: 1.00 Date: 11-9-22上午11:19
 * @Email liuyuhui007@gmail.com
 */
public class JBPMProcessTemplate {
    protected static ProcessEngine processEngine;
    protected static RepositoryService repositoryService;
    protected static ExecutionService executionService;
    protected static TaskService taskService;
    protected static HistoryService historyService;
    protected static ManagementService managementService;
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
}
