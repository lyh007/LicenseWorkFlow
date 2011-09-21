package com.lyh.licenseworkflow.web.base;

import com.lyh.licenseworkflow.service.LicenseWorkFlowIdentityService;
import com.lyh.licenseworkflow.system.util.LogUtil;
import org.apache.log4j.Logger;
import org.jbpm.api.*;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletContext;

/**
 * 服务端的显式对spring bean管理工厂
 *
 * @author kevin
 * @version Revision: 1.00 Date: 11-9-20下午1:34
 * @Email liuyuhui007@gmail.com
 */
public class ServerBeanFactory {
    private static Logger logger = LogUtil.getLogger(LogUtil.SYSTEM_LOG);
    private static ApplicationContext ctx = null;
    private static ServletContext scx = null;
    private static ProcessEngine processEngine = null;
    //技术支持  销售人员 销售负责人（销售总监及助理） License管理员 老板
    private static String[] groups = {"instructor", "vendition", "majordomo", "admin", "boss"};

    /**
     * 分别初始化各个模块，如果有单个模块初始化失败则不影响系统其它模块
     */
    public static void initModules() {
        processEngine = (ProcessEngine) getBean("processEngine");
        if (processEngine == null) System.exit(0);
        deployJPDL();
        initUsers();
    }

    /**
     * 部署流程文件
     */
    private static void deployJPDL() {
        getRepositoryService().createDeployment().addResourceFromClasspath("LicenseWorkFlow.jpdl.xml").addResourceFromClasspath("LicenseWorkFlow.png").deploy();
    }

    /**
     * 初始化用户及工作组
     */
    private static void initUsers() {
        IdentityService identityService = getIdentityService();
        //创建用户组
        for (int i = 0; i < groups.length; i++) {
            identityService.createGroup(groups[i]);
        }
        // 技术支持
        identityService.createUser("wyy", "语嫣", "王");
        identityService.createMembership("wyy", "instructor");
        // 销售人员
        identityService.createUser("mrf", "复", "慕容");
        identityService.createMembership("mrf", "vendition");

        // 销售负责人
        identityService.createUser("mrb", "博", "慕容");
        identityService.createUser("bbt", "不同", "包");

        identityService.createMembership("mrb", "majordomo");
        identityService.createMembership("bbt", "majordomo");

        // License管理员
        identityService.createUser("admin", "管理员", "License");
        identityService.createMembership("admin", "admin");

        // 老板
        identityService.createUser("boss", "版", "老");
        identityService.createMembership("boss", "boss");
    }

    public static void setAppContext(ApplicationContext appCtx) {
        ctx = appCtx;
    }

    public static ApplicationContext getAppContext() {
        return ctx;
    }

    public static void setServletContext(ServletContext servletContext) {
        scx = servletContext;
    }

    public static ServletContext getServletContext() {
        return scx;
    }

    public static Object getBean(String beanName) {
        return ctx.getBean(beanName);
    }

    public static ProcessEngine getProcessEngine() {
        return processEngine;
    }

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
