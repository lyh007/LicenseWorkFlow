package com.lyh.licenseworkflow;

import junit.framework.TestCase;
import org.jbpm.api.*;

/**
 * @author kevin
 * @version Revision: 1.00 Date: 11-9-20上午10:59
 * @Email liuyuhui007@gmail.com
 */
public class BaseTestCase extends TestCase {
    private static ProcessEngine processEngine = Configuration.getProcessEngine();

    public static RepositoryService getRepositoryService() {
        return processEngine.getRepositoryService();
    }

    public static TaskService getTaskService() {
        return processEngine.getTaskService();
    }

    public static ExecutionService getExecutionService() {
        return processEngine.getExecutionService();
    }

    public static IdentityService getIdentityService() {
        return processEngine.getIdentityService();
    }

    public static HistoryService getHistoryService() {
        return processEngine.getHistoryService();
    }

    public static ManagementService getManagementService() {
        return processEngine.getManagementService();
    }
}
