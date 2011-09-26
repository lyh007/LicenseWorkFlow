package com.lyh.licenseworkflow.web.action.instructor;

import com.lyh.licenseworkflow.po.Audit;
import com.lyh.licenseworkflow.po.Issue;
import com.lyh.licenseworkflow.po.User;
import com.lyh.licenseworkflow.service.IssueService;
import com.lyh.licenseworkflow.service.UserService;
import com.lyh.licenseworkflow.system.OceanRuntimeException;
import com.lyh.licenseworkflow.system.util.LicenseWorkFlowConstants;
import com.lyh.licenseworkflow.web.base.BaseAction;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 技术支持人员操作
 *
 * @author kevin
 * @version Revision: 1.00 Date: 11-9-23下午4:07
 * @Email liuyuhui007@gmail.com
 */
@Controller
@Scope("prototype")
@ParentPackage(value = "default")
@Namespace("/instructor")
@Results({
        @Result(name = "index", location = "/WEB-INF/jsp/instructor/index.jsp"),
        @Result(name = "indexAction", location = "/instructor/instructor.action", type = "redirect"),
        @Result(name = "request", location = "/WEB-INF/jsp/instructor/request.jsp"),
        @Result(name = "view", location = "/WEB-INF/jsp/instructor/view.jsp")
})
public class InstructorAction extends BaseAction {
    public SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private String processDefinitionId;
    private String nowDateTime;
    private Issue issue;
    private String requestTime;
    private String venditionUser; //销售人员姓名
    private List<Issue> issues = new ArrayList<Issue>();
    @Resource
    private UserService userService;
    @Resource
    private IssueService issueService;

    @Override
    public String execute() throws Exception {
        List<Issue> dbIssues = new ArrayList<Issue>();
        dbIssues = issueService.getAllIssues();
        for (Issue issue : dbIssues) {
            String ActiveName = issueService.getActiveName(issue.getProcessInstanceId());
            issue.setWorkFlowNodeName(ActiveName);
            issues.add(issue);
        }
        return "index";
    }

    //提交请求链接
    public String request() throws Exception {

        processDefinitionId = userService.getProcessDefinition().getId();
        nowDateTime = sdf.format(new Date());
        return "request";
    }

    //保存请求
    public String submitIssue() throws Exception {
        HttpSession session = request.getSession();
        Map<String, Object> variables = new HashMap<String, Object>();
        User sessionUser = (User) session.getAttribute(LicenseWorkFlowConstants.SESSION_USER);
        User user = userService.getByName(sessionUser.getName());
        issue.setRequestUser(user);
        if (requestTime != null && requestTime.length() > 0) {
            issue.setRequestTime(sdf.parse(requestTime));
        }
        User vUser = userService.getByName(venditionUser);
        issue.setVenditionUser(vUser);

        Audit audit = new Audit();
        // audit.setAuditDept();//用户组名称
        audit.setAuditNotion("发起人");
        audit.setAuditResult("提交申请完成");
        if (requestTime != null && requestTime.length() > 0) {
            audit.setAuditTime(sdf.parse(requestTime));
        }
        audit.setAuditUser(vUser);
        audit.setIssue(issue);
        Set<Audit> audits = new HashSet<Audit>();
        audits.add(audit);
        issue.setAudits(audits);
        issueService.save(issue);
        //申请信息
        variables.put("venditionUser", venditionUser);
        //启动流程
        String processInstanceId = issueService.start(processDefinitionId, variables);
        issue.setProcessInstanceId(processInstanceId);
        issueService.update(issue);
        return "indexAction";
    }

    //查看申请
    public String view() throws Exception {
        if (issue.getId() == 0) throw new OceanRuntimeException("标识不合法");
        issue = issueService.getById(issue.getId());
        return "view";
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

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public String getVenditionUser() {
        return venditionUser;
    }

    public void setVenditionUser(String venditionUser) {
        this.venditionUser = venditionUser;
    }

    public List<Issue> getIssues() {
        return issues;
    }

    public void setIssues(List<Issue> issues) {
        this.issues = issues;
    }
}
