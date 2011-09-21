package com.lyh.licenseworkflow.po;

import java.io.Serializable;

/**
 * @author kevin
 * @version Revision: 1.00 Date: 11-9-21下午2:44
 * @Email liuyuhui007@gmail.com
 */
public class GroupMemberEntity implements Serializable {
    protected long dbid;
    private String groupType;
    protected int dbversion;
    private UserEntity user;
    private GroupEntity group;
    protected String role;

    public GroupMemberEntity() {
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

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public GroupEntity getGroup() {
        return group;
    }

    public void setGroup(GroupEntity group) {
        this.group = group;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }
}
