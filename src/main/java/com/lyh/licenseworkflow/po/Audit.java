package com.lyh.licenseworkflow.po;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 工单(请求)审核信息
 *
 * @author kevin
 * @version Revision: 1.00 Date: 11-9-26下午5:06
 * @Email liuyuhui007@gmail.com
 */
@Entity
@Table(name = "LICENSE_ISSUE_ADUIT")
public class Audit implements Serializable {
    /**
     * 数据库唯一标识
     */
    private long id;
    /**
     * 工单或请求
     */
    private Issue issue;
    /**
     * 审核角色
     */
    private String auditDept;
    /**
     * 申核用户
     */
    private User auditUser;
    /**
     * 审核意
     */
    private String auditNotion;
    /**
     * 审核时
     */
    private Date auditTime;
    /**
     * 审核状态
     */
    private String auditResult;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "ISSUE_AUDIT", referencedColumnName = "id")
    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public String getAuditDept() {
        return auditDept;
    }

    public void setAuditDept(String auditDept) {
        this.auditDept = auditDept;
    }

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "AUDIT_USER")
    public User getAuditUser() {
        return auditUser;
    }

    public void setAuditUser(User auditUser) {
        this.auditUser = auditUser;
    }

    public String getAuditNotion() {
        return auditNotion;
    }

    public void setAuditNotion(String auditNotion) {
        this.auditNotion = auditNotion;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public String getAuditResult() {
        return auditResult;
    }

    public void setAuditResult(String auditResult) {
        this.auditResult = auditResult;
    }
}
