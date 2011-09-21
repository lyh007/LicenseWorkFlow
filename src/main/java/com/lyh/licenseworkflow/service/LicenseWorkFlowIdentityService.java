package com.lyh.licenseworkflow.service;

import com.lyh.licenseworkflow.po.GroupEntity;
import com.lyh.licenseworkflow.po.UserEntity;
import org.jbpm.api.identity.Group;
import org.jbpm.api.identity.User;
import org.jbpm.pvm.internal.identity.spi.IdentitySession;

import java.util.List;

/**
 * @author kevin
 * @version Revision: 1.00 Date: 11-9-21下午4:54
 * @Email liuyuhui007@gmail.com
 */
public interface LicenseWorkFlowIdentityService extends IdentitySession {
    String createUser(String id, String userName, String businessEmail, String familName);

    UserEntity findUserById(String userId);

    List<User> findUsersById(String... userIds);

    List<User> findUsers();

    void deleteUser(String userId);

    String createGroup(String groupName, String groupType, String parentGroupId);

    List<User> findUsersByGroup(String groupId);

    GroupEntity findGroupById(String groupId);

    List<Group> findGroupsByUserAndGroupType(String userId, String groupType);

    List<Group> findGroupsByUser(String userId);

    void deleteGroup(String groupId);

    List<GroupEntity> findGroups();

    void createMembership(String userId, String groupId, String role);

    void deleteMembership(String userId, String groupId, String role);
}
