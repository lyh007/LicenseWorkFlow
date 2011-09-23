package com.lyh.licenseworkflow.web.action.instructor;

import com.lyh.licenseworkflow.service.UserService;
import com.lyh.licenseworkflow.web.base.BaseAction;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 技术支持人员操作
 *
 * @author kevin
 * @version Revision: 1.00 Date: 11-9-23下午4:07
 * @Email liuyuhui007@gmail.com
 */
@Controller
@Scope("prototype")
@ParentPackage(value = "struts-default")
@Namespace("/instructor")
@Results({
         @Result(name = "index", location = "/WEB-INF/jsp/instructor/index.jsp"),
        @Result(name = "request", location = "/WEB-INF/jsp/instructor/request.jsp")
})
public class InstructorAction extends BaseAction {
    private String processDefinitionId;
    private String nowDateTime;
    @Resource
    private UserService userService;

    @Override
    public String execute() throws Exception {
        return "index";
    }

    public String request() throws Exception {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        processDefinitionId = userService.getProcessDefinition().getId();
        nowDateTime =sdf.format(new Date());
        return "request";
    }

    public String getProcessDefinitionId() {
        return processDefinitionId;
    }

    public void setProcessDefinitionId(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }

    public String getNowDateTime() {
        return nowDateTime;
    }

    public void setNowDateTime(String nowDateTime) {
        this.nowDateTime = nowDateTime;
    }
}
