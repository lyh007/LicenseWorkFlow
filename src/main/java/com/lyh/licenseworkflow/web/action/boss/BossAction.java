package com.lyh.licenseworkflow.web.action.boss;

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
 * Date: 12-5-16
 * Time: 下午3:05
 */
@Controller
@Scope("prototype")
@ParentPackage(value = "default")
@Namespace("/boss")
@Results({
        @Result(name = "index", location = "/WEB-INF/jsp/boss/index.jsp"),
        @Result(name = "indexAction", location = "/boss/boss.action", type = "redirect"),
        @Result(name = "audit", location = "/WEB-INF/jsp/boss/audit.jsp"),
        @Result(name = "view", location = "/WEB-INF/jsp/boss/view.jsp")
})
public class BossAction extends BaseAction {
    @Resource
    private IssueService issueService;
    @Resource
    private UserService userService;

    private Issue issue; //工单
    private List<IssueVO> issues = new ArrayList<IssueVO>(); //工单列表
    private String nowDateTime;  //当前时间字符串
    private String taskId; //任务标识
    private String result; //审核结果
    private String auditNotion; //审批意见
    @Override  //默认Boss工单列表
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

    //查看申请
    public String view() throws Exception {
        if (issue.getId() == 0) throw new OceanRuntimeException("标识不合法");
        issue = issueService.getById(issue.getId());
        return "view";
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
        Audit audit = new Audit();  //审批意见
        String groupName = "";
        if (user.getGroups() != null && user.getGroups().size() > 0) {
            List<Group> groupList = new ArrayList<Group>();
            groupList.addAll(user.getGroups());
            groupName = groupList.get(0).getCnName();
        }
        //设置审核的部门名称
        audit.setAuditDept(groupName);
        audit.setAuditNotion(auditNotion);  //审批意见
        audit.setAuditTime(new Date()); //审批时间
        audit.setAuditUser(user); //审批用户
        String outcome = "";  //边
        if ("1".equals(result)) {
            outcome = "同意";
            audit.setAuditResult("同意");
        } else {
            outcome = "否决";
            audit.setAuditResult("不同意");
        }
        /** all the variables visible in the given task */
        Set<String> set = taskService.getVariableNames(taskId);
        /** retrieves a map of variables */
        Map<String, Object> variables = taskService.getVariables(taskId, set);
        if (issue.getId() == 0) throw new OceanRuntimeException("标识不合法");
        //获取工单
        issue = issueService.getById(issue.getId());
        variables.put("createUser", "admin");

        //执行任务
        taskService.completeTask(taskId, outcome, variables);
        //修改工单的审核信息
        audit.setIssue(issue);
        Set<Audit> audits = issue.getAudits();
        audits.add(audit);
        issue.setAudits(audits);
        issueService.saveOrUpdate(issue); //更新到库中

        return "indexAction";
    }
    public IssueService getIssueService() {
        return issueService;
    }

    public void setIssueService(IssueService issueService) {
        this.issueService = issueService;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getAuditNotion() {
        return auditNotion;
    }

    public void setAuditNotion(String auditNotion) {
        this.auditNotion = auditNotion;
    }
}
