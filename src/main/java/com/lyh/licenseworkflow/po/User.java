package com.lyh.licenseworkflow.po;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户模型
 * @author kevin
 * @version Revision: 1.00 Date: 11-9-22上午11:28
 * @Email liuyuhui007@gmail.com
 */
@Entity
@Table(name = "LICENSE_USER")
public class User implements Serializable {
     /** 数据库唯一标识 */
    private long id;
     /** 用户名 */
    private String name;
    /** 邮件 */
    private String email;
    /** 真实姓名 */
    private String realName;
    /** 密码（需加密） */
    private String password;
    /** 用户工作组ID */
	private Set<Group> groups = new HashSet<Group>();

    public User() {
    }

    public User(String name, String realName) {
        this.name = name;
        this.realName = realName;
        this.password="1";
    }

    public User(String name, String realName, String password) {
        this.name = name;
        this.realName = realName;
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(name = "LICENSE_USER_GROUP",
        joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "GROUP_ID", referencedColumnName = "id")})
    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }
}
