package com.lyh.licenseworkflow.po;

import org.jbpm.api.identity.Group;

import java.io.Serializable;

/**
 * @author kevin
 * @version Revision: 1.00 Date: 11-9-21下午2:41
 * @Email liuyuhui007@gmail.com
 */
public class GroupEntity implements Serializable, Group {
    private String id;
    private String name;//组织名称
    private String type;//组织类型
    private GroupEntity parent;//父组织
    private String remarks;//备注
    protected long dbid;
    protected int dbversion;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public GroupEntity getParent() {
        return parent;
    }

    public void setParent(GroupEntity parent) {
        this.parent = parent;
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
}
