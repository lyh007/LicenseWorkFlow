package com.lyh.licenseworkflow.web.action.admin;

import com.lyh.licenseworkflow.po.Audit;
import com.lyh.licenseworkflow.po.Group;
import com.lyh.licenseworkflow.po.Issue;
import com.lyh.licenseworkflow.po.User;
import com.lyh.licenseworkflow.service.IssueService;
import com.lyh.licenseworkflow.service.UserService;
import com.lyh.licenseworkflow.system.OceanRuntimeException;
import com.lyh.licenseworkflow.system.util.LicenseWorkFlowConstants;
import com.lyh.licenseworkflow.web.base.BaseAction;
import com.lyh.licenseworkflow.web.vo.IssueVO;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.jbpm.api.Execution;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.TaskService;
import org.jbpm.api.history.HistoryProcessInstance;
import org.jbpm.api.task.Task;
import org.jbpm.pvm.internal.model.ExecutionImpl;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: kevin
 * Date: 12-2-17
 * Time: 下午3:10
 * License管理员 Action
 */
@Controller
@Scope("prototype")
@ParentPackage(value = "default")
@Namespace("/admin")
@Results({
        @Result(name = "index", location = "/WEB-INF/jsp/admin/index.jsp"),
        @Result(name = "indexAction", location = "/admin/admin.action", type = "redirect"),
        @Result(name = "audit", location = "/WEB-INF/jsp/admin/audit.jsp"),
        @Result(name = "view", location = "/WEB-INF/jsp/admin/view.jsp")
})
public class AdminAction extends BaseAction {
    @Resource
    private IssueService issueService;
    @Resource
    private UserService userService;

    private List<IssueVO> issues = new ArrayList<IssueVO>(); //工单列表
    private Issue issue; //工单
    private String nowDateTime;  //当前时间字符串
    private String taskId; //任务标识

    @Override  //默认License管理员的工单列表
    public String execute() throws Exception {
        List<Issue> dbIssues = new ArrayList<Issue>();
        HttpSession session = request.getSession();
        //获取当前登录用户
        User sessionUser = (User) session.getAttribute(LicenseWorkFlowConstants.SESSION_USER);
        //获取流程定义列表
        List<ProcessDefinition> pdList = issueService.getRepositoryService().createProcessDefinitionQuery().list();
        //获取流程实例列表
        List<ProcessInstance> piList = issueService.getExecutionService().createProcessInstanceQuery().list();
        //获取该用户组内的任务
        List<Task> taskList = issueService.getTaskService().findPersonalTasks(sessionUser.getName());
        //查询库中所有的工单
        dbIssues = issueService.getAllIssues();
        //获取流程寮例历史记录列表
        List<HistoryProcessInstance> hplist = issueService.getHistoryService().createHistoryProcessInstanceQuery().list();
        String processDefinitionId = "";    //流程定义标识
        if (pdList != null && pdList.size() > 0) {
            processDefinitionId = ((ProcessDefinition) (pdList.get(0))).getId();
        }
        //遍历系统中的工单
        for (Issue issue : dbIssues) {
            //设置工单的流程节点
            String activeName = issueService.getActiveName(issue.getProcessInstanceId());
            IssueVO issueVO = new IssueVO();
            issueVO.setWorkFlowNodeName(activeName);
            //遍历当前登录用户的task列表
            for (Task task : taskList) {
                //获取执行
                Execution execution = issueService.getExecutionService().findExecutionById(task.getExecutionId());
                ExecutionImpl execImpl = (ExecutionImpl) execution;
                //获取执行的流程实例
                ProcessInstance processInstance = execImpl.getProcessInstance();
                if (issue.getProcessInstanceId().equals(processInstance.getId())) {
                    //显示审核链接
                    issueVO.setShowAudit(true);
                    //当前工单要审核的task
                    issueVO.setIssueTask(task);
                    break;
                } else {
                    issueVO.setShowAudit(false);
                }
            }
            LicenseWorkFlowConstants.issuePOTOVOCopier.copy(issue, issueVO, null);
            issues.add(issueVO);
        }
        return "index";
    }

    //加载要生成License的工单
    public String preAudit() {
        if (issue.getId() == 0) throw new OceanRuntimeException("标识不合法");
        issue = issueService.getById(issue.getId());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        nowDateTime = sdf.format(new Date());
        return "audit";
    }

    //审核
    public String audit() throws Exception {

        TaskService taskService = issueService.getTaskService();
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute(LicenseWorkFlowConstants.SESSION_USER);
        User user = userService.getById(sessionUser.getId());
        Set<String> set = taskService.getVariableNames(taskId);
        Map<String, Object> variables = taskService.getVariables(taskId, set);

        String groupName = "";
        if (user.getGroups() != null && user.getGroups().size() > 0) {
            List<Group> groupList = new ArrayList<Group>();
            groupList.addAll(user.getGroups());
            groupName = groupList.get(0).getCnName();
        }
        Audit audit = new Audit();  //审批意见
        audit.setAuditDept(groupName);
        audit.setAuditUser(user);
        audit.setAuditNotion("生成License");
        audit.setAuditTime(new Date());
        if (issue.getId() == 0) throw new OceanRuntimeException("标识不合法");
        issue = issueService.getById(issue.getId());
        String resultStr="";
        if(issue.getLicenseType()==1){
            resultStr="永久License";
        }else{
            resultStr="临时License";
        }
        audit.setAuditResult("已生成");
        //修改工单的审核信息
        audit.setIssue(issue);
        Set<Audit> audits = issue.getAudits();
        audits.add(audit);
        issue.setAudits(audits);
        issueService.saveOrUpdate(issue); //更新到库中
        variables.put("licenseType", issue.getLicenseType());
        taskService.completeTask(taskId, "根据License类型判断", variables);
        return "indexAction";
    }

    //查看申请
    public String view() throws Exception {
        if (issue.getId() == 0) throw new OceanRuntimeException("标识不合法");
        issue = issueService.getById(issue.getId());
        return "view";
    }
    public List<IssueVO> getIssues() {
        return issues;
    }

    public void setIssues(List<IssueVO> issues) {
        this.issues = issues;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public String getNowDateTime() {
        return nowDateTime;
    }

    public void setNowDateTime(String nowDateTime) {
        this.nowDateTime = nowDateTime;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
}
