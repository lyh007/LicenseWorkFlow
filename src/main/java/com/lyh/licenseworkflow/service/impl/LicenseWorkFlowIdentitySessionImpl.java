package com.lyh.licenseworkflow.service.impl;

import com.lyh.licenseworkflow.po.GroupEntity;
import com.lyh.licenseworkflow.po.GroupMemberEntity;
import com.lyh.licenseworkflow.po.UserEntity;
import com.lyh.licenseworkflow.service.LicenseWorkFlowIdentityService;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.jbpm.api.JbpmException;
import org.jbpm.api.identity.Group;
import org.jbpm.api.identity.User;
import org.jbpm.pvm.internal.env.BasicEnvironment;
import org.jbpm.pvm.internal.env.EnvironmentImpl;
import org.jbpm.pvm.internal.id.DbidGenerator;
import org.jbpm.pvm.internal.identity.spi.IdentitySession;

import java.util.Arrays;
import java.util.List;

/**
 * @author kevin
 * @version Revision: 1.00 Date: 11-9-21下午2:48
 * @Email liuyuhui007@gmail.com
 */
public class LicenseWorkFlowIdentitySessionImpl implements LicenseWorkFlowIdentityService {
    protected Session session;
    public LicenseWorkFlowIdentitySessionImpl() {
        this.session = BasicEnvironment.getFromCurrent(Session.class);
    }

    public String createUser(String id, String userName, String businessEmail, String familName) {
        UserEntity user = new UserEntity(id, userName, businessEmail);
        long dbid = EnvironmentImpl.getFromCurrent(DbidGenerator.class).getNextId();
        user.setDbid(dbid);
        session.save(user);
        return user.getId();
    }

    public UserEntity findUserById(String userId) {
        return (UserEntity) session.createCriteria(UserEntity.class).add(Restrictions.eq("id", userId)).uniqueResult();
    }

    public List<User> findUsersById(String... userIds) {
        List<User> users = session.createCriteria(UserEntity.class).add(Restrictions.in("id", userIds)).list();
        if (userIds.length != users.size()) {
            throw new JbpmException("not all users were found: " + Arrays.toString(userIds));
        }
        return users;
    }

    public List<User> findUsers() {
        return session.createCriteria(UserEntity.class).list();
    }

    public void deleteUser(String userId) {
        // lookup the user
        User user = findUserById(userId);
        // cascade the deletion to the memberships
        List<GroupMemberEntity> memberships = session.createCriteria(
                GroupMemberEntity.class).add(Restrictions.eq("user", user)).list();
        // delete the related memberships
        for (GroupMemberEntity membership : memberships) {
            session.delete(membership);
        }
        // delete the user
        session.delete(user);
    }

    public String createGroup(String groupName, String groupType, String parentGroupId) {
        GroupEntity group = new GroupEntity();
        String groupId = groupType != null ? groupType + "." + groupName : groupName;
        group.setId(groupId);
        long dbid = EnvironmentImpl.getFromCurrent(DbidGenerator.class).getNextId();
        group.setDbid(dbid);
        group.setName(groupName);
        group.setType(groupType);
        if (parentGroupId != null) {
            GroupEntity parentGroup = findGroupById(parentGroupId);
            group.setParent(parentGroup);
        }
        session.save(group);
        return group.getId();
    }

    public List<User> findUsersByGroup(String groupId) {
        return session.createCriteria(GroupMemberEntity.class).createAlias("group", "g").add(Restrictions.eq("g.id", groupId)).setProjection(Projections.property("user")).list();
    }

    public GroupEntity findGroupById(String groupId) {
        return (GroupEntity) session.createCriteria(GroupEntity.class).add(Restrictions.eq("id", groupId)).uniqueResult();
    }

    public List<Group> findGroupsByUserAndGroupType(String userId, String groupType) {
        return session.createQuery(
                "select distinct m.group" + " from "
                        + GroupMemberEntity.class.getName()
                        + " as m where m.user.id = :userId"
                        + " and m.group.type = :groupType").setString("userId",
                userId).setString("groupType", groupType).list();
    }

    public List<Group> findGroupsByUser(String userId) {
        List<Group> gList = session.createQuery(
                "select distinct m.group" + " from "
                        + GroupMemberEntity.class.getName()
                        + " as m where m.user.id = :userId").setString(
                "userId", userId).list();
        return gList;
    }

    public void deleteGroup(String groupId) {
        //lookup the group
        GroupEntity group = findGroupById(groupId);
        // cascade the deletion to the memberships
        List<GroupMemberEntity> memberships = session.createCriteria(GroupMemberEntity.class).add(Restrictions.eq("group", group)).list();
        // delete the related memberships
        for (GroupMemberEntity membership : memberships) {
            session.delete(membership);
        }
        // delete the group
        session.delete(group);
    }

    public List<GroupEntity> findGroups() {
        return session.createCriteria(GroupEntity.class).list();
    }

    public void createMembership(String userId, String groupId, String role) {
        UserEntity user = findUserById(userId);
        if (user == null) {
            throw new JbpmException("user " + userId + " doesn't exist");
        }
        GroupEntity group = findGroupById(groupId);
        if (group == null) {
            throw new JbpmException("group " + groupId + " doesn't exist");
        }
        GroupMemberEntity membership = new GroupMemberEntity();
        membership.setUser(user);
        membership.setGroup(group);
        membership.setRole(role);
        long dbid = EnvironmentImpl.getFromCurrent(DbidGenerator.class).getNextId();
        membership.setDbid(dbid);
        session.save(membership);
    }

    public void deleteMembership(String userId, String groupId, String role) {
        GroupMemberEntity membership = (GroupMemberEntity) session.createCriteria(
                GroupMemberEntity.class).createAlias("user", "u").createAlias(
                "group", "g").add(Restrictions.eq("u.id", userId)).add(
                Restrictions.eq("g.id", groupId)).uniqueResult();
        session.delete(membership);
    }
}
