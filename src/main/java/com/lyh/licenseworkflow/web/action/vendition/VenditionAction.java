package com.lyh.licenseworkflow.web.action.vendition;

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
import net.sf.cglib.beans.BeanCopier;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.jbpm.api.Execution;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.TaskService;
import org.jbpm.api.task.Task;
import org.jbpm.pvm.internal.model.ExecutionImpl;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 销售人员操作
 * Created by IntelliJ IDEA.
 * User: kevin
 * Date: 11-10-8
 * Time: 下午1:58
 */
@Controller
@Scope("prototype")
@ParentPackage(value = "default")
@Namespace("/vendition")
@Results({
        @Result(name = "index", location = "/WEB-INF/jsp/vendition/index.jsp"),
        @Result(name = "indexAction", location = "/vendition/vendition.action", type = "redirect"),
        @Result(name = "audit", location = "/WEB-INF/jsp/vendition/audit.jsp")
})
public class VenditionAction extends BaseAction {
    private List<IssueVO> issues = new ArrayList<IssueVO>(); //工单列表
    private String taskId; //task 标识
    private Issue issue; //工单
    private String nowDateTime;  //当前时间字符串
    private String result; //审核结果
    private String auditNotion; //审批意见
    @Resource
    private IssueService issueService;
    @Resource
    private UserService userService;


    @Override  //默认销售人员列表
    public String execute() throws Exception {
        List<Issue> dbIssues = new ArrayList<Issue>();
        HttpSession session = request.getSession();
        //获取当前登录用户
        User sessionUser = (User) session.getAttribute(LicenseWorkFlowConstants.SESSION_USER);
        //获取登录用户的任务列表
        List<Task> taskList = issueService.getTaskService().findPersonalTasks(sessionUser.getName());
        //查询库中所有的工单
        dbIssues = issueService.getAllIssues();
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

    //加载要审核的工单
    public String preAudit() throws Exception {
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
            outcome = "根据合同金额判断";
            audit.setAuditResult("同意");
        } else {
            outcome = "否决";
            audit.setAuditResult("否决");
        }
        /** all the variables visible in the given task */
        Set<String> set = taskService.getVariableNames(taskId);
        /** retrieves a map of variables */
        Map<String, Object> variables = taskService.getVariables(taskId, set);
        if (issue.getId() == 0) throw new OceanRuntimeException("标识不合法");
        //获取工单
        issue = issueService.getById(issue.getId());
        variables.put("money", issue.getMoney());
        variables.put("createUser","admin");
        //执行任务
        taskService.completeTask(taskId, outcome, variables);
        //修改工单的审核信息
        audit.setIssue(issue);
        Set<Audit> audits=issue.getAudits();
        audits.add(audit);
        issue.setAudits(audits);
      //  issue.getAudits().add(audit); //添加新的审核信息
        issueService.saveOrUpdate(issue); //更新到库中
        return "indexAction";
    }

    public List<IssueVO> getIssues() {
        return issues;
    }

    public void setIssues(List<IssueVO> issues) {
        this.issues = issues;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
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
