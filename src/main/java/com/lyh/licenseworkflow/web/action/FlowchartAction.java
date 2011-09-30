package com.lyh.licenseworkflow.web.action;

import com.lyh.licenseworkflow.service.IssueService;
import com.lyh.licenseworkflow.system.OceanRuntimeException;
import com.lyh.licenseworkflow.web.base.BaseAction;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.pvm.internal.model.ProcessDefinitionImpl;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.io.InputStream;

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

    @Override
    public String execute() throws Exception {
        if (processInstanceId == null || processInstanceId.length() == 0) {
            throw new OceanRuntimeException("流程实例标识不合法");
        }
        //获取流程定义对象
        ProcessDefinition processDefinition = issueService.getProcessDefinitionByInstanceId(processInstanceId);
        ProcessDefinitionImpl processDefinitionImpl = (ProcessDefinitionImpl) processDefinition;

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
}
