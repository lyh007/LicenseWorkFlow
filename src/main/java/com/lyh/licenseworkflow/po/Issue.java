package com.lyh.licenseworkflow.po;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author kevin
 * @version Revision: 1.00 Date: 11-9-26上午10:45
 * @Email liuyuhui007@gmail.com
 */
@Entity
@Table(name = "LICENSE_ISSUE")
public class Issue implements Serializable {
    /**
     * 数据库唯一标识
     */
    private long id;
    /**
     * 客户名称
     */
    private String costumeName;
    /**
     * 合同金额
     */
    private long money;
    /**
     * 注册License类型    0:临时 1:永久
     */
    private int licenseType;
    /**
     * 销售人员
     */
    private User venditionUser;
    /**
     * 申请人
     */
    private User requestUser;
    /**
     * 申请时间 ,审核时间
     */
    private Date requestTime;
    /**
     * 审核意见
     */
    private String auditNotion;
    /**
     * 审核状态
     */
    private String auditResult;
    private String processInstanceId;
    private String workFlowNodeName; //流程节点名称

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCostumeName() {
        return costumeName;
    }

    public void setCostumeName(String costumeName) {
        this.costumeName = costumeName;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public int getLicenseType() {
        return licenseType;
    }

    public void setLicenseType(int licenseType) {
        this.licenseType = licenseType;
    }

    @ManyToOne
    @JoinColumn(name = "VENDITION_USER")
    public User getVenditionUser() {
        return venditionUser;
    }

    public void setVenditionUser(User venditionUser) {
        this.venditionUser = venditionUser;
    }

    @ManyToOne
    @JoinColumn(name = "REQUEST_USER")
    public User getRequestUser() {
        return requestUser;
    }

    public void setRequestUser(User requestUser) {
        this.requestUser = requestUser;
    }

    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    public String getAuditNotion() {
        return auditNotion;
    }

    public void setAuditNotion(String auditNotion) {
        this.auditNotion = auditNotion;
    }

    public String getAuditResult() {
        return auditResult;
    }

    public void setAuditResult(String auditResult) {
        this.auditResult = auditResult;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    @Transient
    public String getWorkFlowNodeName() {
        return workFlowNodeName;
    }

    public void setWorkFlowNodeName(String workFlowNodeName) {
        this.workFlowNodeName = workFlowNodeName;
    }
}
