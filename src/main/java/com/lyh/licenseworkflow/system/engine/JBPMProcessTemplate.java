package com.lyh.licenseworkflow.system.engine;

import org.jbpm.api.*;

/**
 * @author kevin
 * @version Revision: 1.00 Date: 11-9-22上午11:19
 * @Email liuyuhui007@gmail.com
 */
public class JBPMProcessTemplate {
    protected ProcessEngine processEngine;
    protected RepositoryService repositoryService;
    protected ExecutionService executionService;
    protected TaskService taskService;
    protected HistoryService historyService;
    protected ManagementService managementService;
    protected IdentityService identityService;

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

    public void setProcessEngine(ProcessEngine processEngine) {
        this.processEngine = processEngine;
    }

    public RepositoryService getRepositoryService() {
        if (repositoryService == null) {
            repositoryService = processEngine.getRepositoryService();
        }
        return repositoryService;
    }

    public void setRepositoryService(RepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }

    public ExecutionService getExecutionService() {
        if (executionService == null) {
            executionService = processEngine.getExecutionService();
        }
        return executionService;
    }

    public void setExecutionService(ExecutionService executionService) {
        this.executionService = executionService;
    }

    public TaskService getTaskService() {
        if (taskService == null) {
            taskService = processEngine.getTaskService();
        }
        return taskService;
    }

    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    public HistoryService getHistoryService() {
        if (null == historyService)
            historyService = processEngine.getHistoryService();
        return historyService;
    }

    public void setHistoryService(HistoryService historyService) {
        this.historyService = historyService;
    }

    public ManagementService getManagementService() {
        if (null == managementService)
            managementService = processEngine.getManagementService();
        return managementService;
    }

    public void setManagementService(ManagementService managementService) {
        this.managementService = managementService;
    }

    public IdentityService getIdentityService() {
        if (null == identityService)
            identityService = processEngine.getIdentityService();
        return identityService;
    }

    public void setIdentityService(IdentityService identityService) {
        this.identityService = identityService;
    }
}
