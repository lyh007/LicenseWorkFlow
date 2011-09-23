package com.lyh.licenseworkflow.po;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 工作组模型
 *
 * @author kevin
 * @version Revision: 1.00 Date: 11-9-22下午8:14
 * @Email liuyuhui007@gmail.com
 */
@Entity
@Table(name = "LICENCE_GROUP")
public class Group implements Serializable {
    /**
     * 数据库唯一标识
     */
    private long id;
    /**
     * 工作组名称
     */
    private String name;
    /**
     * 工作组中文名称
     */
    private String cnName;
    /**
     * 父工作组
     */
    private Group parentGroup;
    /**
     * 子工作组
     */
    private Set<Group> subGroups = new HashSet<Group>(0);

    public Group() {
    }

    public Group(String name) {
        this.name = name;
    }

    public Group(String name, String cnName) {
        this.name = name;
        this.cnName = cnName;
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

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    public Group getParentGroup() {
        return parentGroup;
    }

    public void setParentGroup(Group parentGroup) {
        this.parentGroup = parentGroup;
    }
    @OneToMany(targetEntity =Group.class ,cascade=CascadeType.ALL,mappedBy ="subGroups")
    public Set<Group> getSubGroups() {
        return subGroups;
    }

    public void setSubGroups(Set<Group> subGroups) {
        this.subGroups = subGroups;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }
}
