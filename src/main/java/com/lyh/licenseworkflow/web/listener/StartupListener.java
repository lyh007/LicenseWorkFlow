package com.lyh.licenseworkflow.web.listener;

import com.lyh.licenseworkflow.system.Version;
import com.lyh.licenseworkflow.system.util.LogUtil;
import com.lyh.licenseworkflow.web.base.ServerBeanFactory;
import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 启动监听类，用来获取Spring容器的
 *
 * @author kevin
 * @version Revision: 1.00 Date: 11-9-20下午1:31
 * @Email liuyuhui007@gmail.com
 */
public class StartupListener implements ServletContextListener {
    private Logger logger = LogUtil.getLogger(LogUtil.SYSTEM_LOG);

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContextEvent.getServletContext());
        ServerBeanFactory.setAppContext(ctx);
        ServerBeanFactory.setServletContext(servletContextEvent.getServletContext());
        //初始化系统中的模块
        ServerBeanFactory.initModules();
        logger.info("LICENSEWORKFLOW Server [" + Version.getInstance().getSvnTag() + ".GA (build: SVNTag="
                + Version.getInstance().getSvnTag() + " date=" + Version.getInstance().getBuildDate() + ")] has started...");
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        logger.info("LICENSEWORKFLOW Server [" + Version.getInstance().getSvnTag() + ".GA (build: SVNTag="
                + Version.getInstance().getSvnTag() + " date=" + Version.getInstance().getBuildDate() + ")] has stopped...");
    }
}
