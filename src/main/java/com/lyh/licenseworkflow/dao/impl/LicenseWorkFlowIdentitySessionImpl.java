package com.lyh.licenseworkflow.dao.impl;

import com.lyh.licenseworkflow.po.GroupEntity;
import com.lyh.licenseworkflow.po.GroupMemberEntity;
import com.lyh.licenseworkflow.po.UserEntity;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.jbpm.api.JbpmException;
import org.jbpm.api.identity.Group;
import org.jbpm.api.identity.User;
import org.jbpm.pvm.internal.env.BasicEnvironment;
import org.jbpm.pvm.internal.env.EnvironmentImpl;
import org.jbpm.pvm.internal.id.DbidGenerator;
import org.jbpm.pvm.internal.identity.spi.IdentitySession;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;

import java.util.Arrays;
import java.util.List;

/**
 * @author kevin
 * @version Revision: 1.00 Date: 11-9-21下午2:48
 * @Email liuyuhui007@gmail.com
 */
public class LicenseWorkFlowIdentitySessionImpl implements IdentitySession {
    protected Session session;
    private LocalSessionFactoryBean aa;
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

    public List<User> findUsersByGroup(String s) {
        return null;
    }

    public GroupEntity findGroupById(String s) {
        return null;
    }

    public List<Group> findGroupsByUserAndGroupType(String s, String s1) {
        return null;
    }

    public List<Group> findGroupsByUser(String s) {
        return null;
    }

    public void deleteGroup(String s) {

    }

    public void createMembership(String s, String s1, String s2) {

    }

    public void deleteMembership(String s, String s1, String s2) {

    }
}
