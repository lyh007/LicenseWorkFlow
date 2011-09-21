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
    private String name;// 员工姓名
    private String password;// 密码
    private String mail;// 电子邮件
    protected long dbid; // 数据库内部自生成的ID
    protected int dbversion;

    public UserEntity() {
    }

    public UserEntity(String id, String name, String mail) {
        this.id = id;
        this.name = name;
        this.mail = mail;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
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
        return this.mail;
    }
}
