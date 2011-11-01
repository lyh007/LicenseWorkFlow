package com.lyh.licenseworkflow.system.engine.flowlistener;

import org.jbpm.api.listener.EventListener;
import org.jbpm.api.listener.EventListenerExecution;

/**
 * Created by IntelliJ IDEA.
 * User: kevin
 * Date: 11-11-1
 * Time: 上午9:37
 */
public class LogListener implements EventListener {

    @Override
    public void notify(EventListenerExecution execution) throws Exception {
        System.out.println("============流程结束了============");
    }
}
