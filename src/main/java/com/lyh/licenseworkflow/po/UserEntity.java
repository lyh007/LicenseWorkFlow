package com.lyh.licenseworkflow.po;

import org.jbpm.api.identity.User;

import java.io.Serializable;

/**
 * @author kevin
 * @version Revision: 1.00 Date: 11-9-21下午2:38
 * @Email liuyuhui007@gmail.com
 */
public class UserEntity implements Serializable, User {
    private String id;
    private String userNo;// 员工工号
    private String userName;// 员工姓名
    private String givenName;
    private String userSex;// 性别
    private String password;// 密码
    private String userType;// 类型
    private String userMail;// 电子邮件
    private String isValid;// 是否有效Y/N
    private String remarks;// 备注
    protected long dbid; // 数据库内部自生成的ID
    protected int dbversion;

    public UserEntity() {
    }

    public UserEntity(String id, String userName, String userMail) {
        this.id = id;
        this.userName = userName;
        this.userMail = userMail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getValid() {
        return isValid;
    }

    public void setValid(String valid) {
        isValid = valid;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public long getDbid() {
        return dbid;
    }

    public void setDbid(long dbid) {
        this.dbid = dbid;
    }

    public int getDbversion() {
        return dbversion;
    }

    public void setDbversion(int dbversion) {
        this.dbversion = dbversion;
    }

    public String getGivenName() {
        return null;
    }

    public String getFamilyName() {
        return null;
    }

    public String getBusinessEmail() {
        return this.userMail;
    }
}
