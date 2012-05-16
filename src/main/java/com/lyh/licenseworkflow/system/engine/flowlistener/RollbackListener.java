package com.lyh.licenseworkflow.system.engine.flowlistener;

import com.lyh.licenseworkflow.service.IssueService;
import com.lyh.licenseworkflow.web.base.ServerBeanFactory;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.listener.EventListener;
import org.jbpm.api.listener.EventListenerExecution;
import org.jbpm.pvm.internal.model.ActivityImpl;
import org.jbpm.pvm.internal.model.ExecutionImpl;
import org.jbpm.pvm.internal.model.ProcessDefinitionImpl;
import org.jbpm.pvm.internal.model.TransitionImpl;

import javax.annotation.Resource;

/**
 * Created by IntelliJ IDEA.
 * User: kevin
 * Date: 12-5-16
 * Time: 下午1:40
 */
public class RollbackListener implements EventListener {
    String m_rollbackTo;

    @Override
    public void notify(EventListenerExecution execution) throws Exception {
        //获取流程定义对象
        ProcessInstance processInstance = execution.getProcessInstance();
        String processDefinitionId = processInstance.getProcessDefinitionId();
        IssueService issueService=( IssueService)ServerBeanFactory.getBean("issueService");
        ProcessDefinitionImpl processDefinition = (ProcessDefinitionImpl) issueService.getRepositoryService().createProcessDefinitionQuery().processDefinitionId(processDefinitionId).uniqueResult();

        //获取退回目的地的活动定义对象
        ActivityImpl toActivityImpl = processDefinition.findActivity(m_rollbackTo);

        //退回活动目的地不存在，流程定义错误
        if (null == toActivityImpl) {
            String ms = "in " + processDefinitionId + " no " + m_rollbackTo;
            throw new Exception(ms);
        }

        //获得当前活动的定义对象
        ActivityImpl fromActivityImpl = ((ExecutionImpl) execution).getActivity();

        //建立退回的转移路径
        TransitionImpl transtion = fromActivityImpl.createOutgoingTransition();
        String tranName = fromActivityImpl.getName() + "to " + m_rollbackTo;
        transtion.setName(tranName);
        transtion.setDestination(toActivityImpl);
    }
}
