package com.lyh.licenseworkflow.web.vo;

import com.lyh.licenseworkflow.po.Issue;
import org.jbpm.api.task.Task;

/**
 * 工单值对象
 * Created by IntelliJ IDEA.
 * User: keivn
 * Date: 11-10-8
 * Time: 下午3:41
 */
public class IssueVO extends Issue {
    /**
     * 流程节点名称
     */
    private String workFlowNodeName;
    /**
     * 是否显示审核链接
     */
    private boolean showAudit = false;
    /**
     * 当前工单任务
     */
    private Task issueTask;

    public String getWorkFlowNodeName() {
        return workFlowNodeName;
    }

    public void setWorkFlowNodeName(String workFlowNodeName) {
        this.workFlowNodeName = workFlowNodeName;
    }

    public boolean isShowAudit() {
        return showAudit;
    }

    public void setShowAudit(boolean showAudit) {
        this.showAudit = showAudit;
    }

    public Task getIssueTask() {
        return issueTask;
    }

    public void setIssueTask(Task issueTask) {
        this.issueTask = issueTask;
    }
}
