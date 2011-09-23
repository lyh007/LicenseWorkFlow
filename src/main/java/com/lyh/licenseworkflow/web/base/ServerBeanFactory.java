package com.lyh.licenseworkflow.web.base;

import com.lyh.licenseworkflow.po.Group;
import com.lyh.licenseworkflow.po.User;
import com.lyh.licenseworkflow.system.util.LogUtil;
import org.apache.log4j.Logger;
import org.jbpm.api.*;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.HibernateTemplate;

import javax.servlet.ServletContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private static HibernateTemplate hibernateTemplate = null;
    //技术支持  销售人员 销售负责人（销售总监及助理） License管理员 老板
    private static String[] groups = {"instructor", "vendition", "majordomo", "admin", "boss"};

    /**
     * 分别初始化各个模块，如果有单个模块初始化失败则不影响系统其它模块
     */
    public static void initModules() {
        processEngine = (ProcessEngine) getBean("processEngine");
        hibernateTemplate = (HibernateTemplate) getAppContext().getBean("hibernateTemplate");
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
            List<Group> dbGroups = (List<Group>) hibernateTemplate.find("from " + Group.class.getName() + " where name='" + groups[i] + "'");
            //系统中已经存在则不初始化
            if (dbGroups == null || dbGroups.size() == 0) {
                identityService.createGroup(groups[i]);
                hibernateTemplate.save(new Group(groups[i]));
            }

        }
        if (!userExistsByName("wyy")) {
            // 技术支持
            identityService.createUser("wyy", "语嫣", "王");
            identityService.createMembership("wyy", "instructor");
            Group group = findGroupByName("instructor");
            User user = new User("wyy", "王语嫣");
            if (group != null) {
                Set<Group> userGroups = new HashSet<Group>();
                userGroups.add(group);
                user.setGroups(userGroups);
                hibernateTemplate.save(user);
            }
        }

        if (!userExistsByName("mrf")) {
            // 销售人员
            identityService.createUser("mrf", "复", "慕容");
            identityService.createMembership("mrf", "vendition");
            Group group = findGroupByName("vendition");
            User user = new User("mrf", "慕容复");
            if (group != null) {
                Set<Group> userGroups = new HashSet<Group>();
                userGroups.add(group);
                user.setGroups(userGroups);
            }
            hibernateTemplate.save(user);
        }

        if (!userExistsByName("mrb")) {
            // 销售负责人
            identityService.createUser("mrb", "博", "慕容");
            identityService.createMembership("mrb", "majordomo");
            Group group = findGroupByName("majordomo");
            User user = new User("mrb", "慕容博");
            if (group != null) {
                Set<Group> userGroups = new HashSet<Group>();
                userGroups.add(group);
                user.setGroups(userGroups);
            }
            hibernateTemplate.save(user);
        }

        if (!userExistsByName("bbt")) {
            identityService.createUser("bbt", "不同", "包");
            identityService.createMembership("bbt", "majordomo");
            Group group = findGroupByName("majordomo");
            User user = new User("bbt", "包不同");
            if (group != null) {
                Set<Group> userGroups = new HashSet<Group>();
                userGroups.add(group);
                user.setGroups(userGroups);
            }
            hibernateTemplate.save(user);
        }

        if (!userExistsByName("admin")) {
            // License管理员
            identityService.createUser("admin", "管理员", "License");
            identityService.createMembership("admin", "admin");

            Group group = findGroupByName("admin");
            User user = new User("admin", "License管理员");
            if (group != null) {
                Set<Group> userGroups = new HashSet<Group>();
                userGroups.add(group);
                user.setGroups(userGroups);
            }
            hibernateTemplate.save(user);
        }
        if (!userExistsByName("boss")) {
            // 老板
            identityService.createUser("boss", "板", "老");
            identityService.createMembership("boss", "boss");
            Group group = findGroupByName("boss");
            User user = new User("boss", "老板");
            if (group != null) {
                Set<Group> userGroups = new HashSet<Group>();
                userGroups.add(group);
                user.setGroups(userGroups);
            }
            hibernateTemplate.save(user);
        }
    }

    /**
     * 判断用户是否存在
     *
     * @param name 用户名
     * @return 存在返回true
     */
    private static boolean userExistsByName(String name) {
        List<User> dbUsers = (List<User>) hibernateTemplate.find("from " + User.class.getName() + " where name='" + name + "'");
        //系统中已经存在则不初始化
        if (dbUsers == null || dbUsers.size() == 0) {
            return false;
        } else {
            return true;
        }
    }

    private static Group findGroupByName(String name) {
        Group group = null;
        List<Group> dbGroups = (List<Group>) hibernateTemplate.find("from " + Group.class.getName() + " where name='" + name + "'");
        //系统中已经存在则不初始化
        if (dbGroups != null && dbGroups.size() >= 0) {
            group = dbGroups.get(0);
        }
        return group;
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

    public static HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    public static void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        ServerBeanFactory.hibernateTemplate = hibernateTemplate;
    }
}
