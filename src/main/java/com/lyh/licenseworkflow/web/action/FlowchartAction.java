package com.lyh.licenseworkflow.web.action;

import com.lyh.licenseworkflow.service.IssueService;
import com.lyh.licenseworkflow.system.OceanRuntimeException;
import com.lyh.licenseworkflow.web.base.BaseAction;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.history.HistoryActivityInstance;
import org.jbpm.api.model.ActivityCoordinates;
import org.jbpm.pvm.internal.model.Activity;
import org.jbpm.pvm.internal.model.ProcessDefinitionImpl;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.*;

/**
 * 流程图Action
 *
 * @author kevin
 * @version Revision: 1.00 Date: 11-9-27上午10:47
 * @Email liuyuhui007@gmail.com
 */
@Controller
@Scope("prototype")
@ParentPackage(value = "default")
@Namespace("/")
@Results({
        @Result(name = "success", location = "/WEB-INF/jsp/common/flowchart.jsp")
})
public class FlowchartAction extends BaseAction {
    //流程实例标识
    private String processInstanceId;
    //请求服务
    @Resource
    private IssueService issueService;
    //流程节点坐标
    List<ActivityCoordinates> activityCoordinates = new ArrayList<ActivityCoordinates>();

    @Override
    public String execute() throws Exception {
        if (processInstanceId == null || processInstanceId.length() == 0) {
            throw new OceanRuntimeException("流程实例标识不合法");
        }
        //获取流程定义对象
        ProcessDefinition processDefinition = issueService.getProcessDefinitionByInstanceId(processInstanceId);
//        ProcessDefinitionImpl processDefinitionImpl = (ProcessDefinitionImpl) processDefinition;

        //获得给流程下所有激活的activity
//        Map<String, Activity> mapActivity = processDefinitionImpl.getActivitiesMap();
//        Iterator iter = mapActivity.entrySet().iterator();
//        Activity activity = null;
//        while (iter.hasNext()) {
//            Map.Entry entry = (Map.Entry) iter.next();
//            activity = (Activity) entry.getValue();
//            System.out.println(activity.getType() + "====================" + activity.getName());
//        }
        //某一流程实例所有已经过的流程节点
        List<HistoryActivityInstance> histActInsts = issueService.getHistoryService().createHistoryActivityInstanceQuery().executionId(processInstanceId).list();
//        for (HistoryActivityInstance h : histActInsts) {
//            System.out.println(h.getExecutionId() + "==========22222==========" + h.getActivityName());
//        }
        //获取所有活跃的节点：
//        Set<String> activityNames = issueService.getExecutionService().findProcessInstanceById(processInstanceId).findActiveActivityNames();

        if (histActInsts != null) {
            for (HistoryActivityInstance h : histActInsts) {
                ActivityCoordinates ac = issueService.getRepositoryService().getActivityCoordinates(processDefinition.getId(), h.getActivityName());
                activityCoordinates.add(ac);
                System.out.println("histActInsts: X=" + ac.getX() + " y=" + ac.getY() + " width=" + ac.getWidth() + "height=" + ac.getHeight());
            }
        }
        //下面是根据节点名称来获取坐标
//        if (activityNames != null) {
//            for (String activityName : activityNames) {
//                ActivityCoordinates ac = issueService.getRepositoryService().getActivityCoordinates(processDefinition.getId(), activityName);
//                 System.out.println("getBy ActivityName: X=" + ac.getX() + " y=" + ac.getY() + " width=" + ac.getWidth() + "height=" + ac.getHeight());
//            }
//        }
        return SUCCESS;
    }

    public String outImage() throws Exception {
        //设置页面访问头
        response.setHeader("Cash", "no cash");
        response.setContentType("image/png");

        if (processInstanceId == null || processInstanceId.length() == 0) {
            throw new OceanRuntimeException("流程实例标识不合法");
        }
        InputStream inputStream = issueService.getFlowchart(processInstanceId);
        //从输入流读取数据到输出流
        byte[] bytes = new byte[1024];
        while (-1 != inputStream.read(bytes)) {
            response.getOutputStream().write(bytes);
        }
        response.getOutputStream().flush();
        return null;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public List<ActivityCoordinates> getActivityCoordinates() {
        return activityCoordinates;
    }

    public void setActivityCoordinates(List<ActivityCoordinates> activityCoordinates) {
        this.activityCoordinates = activityCoordinates;
    }
}
