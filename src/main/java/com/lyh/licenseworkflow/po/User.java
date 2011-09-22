package com.lyh.licenseworkflow.po;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author kevin
 * @version Revision: 1.00 Date: 11-9-22上午11:28
 * @Email liuyuhui007@gmail.com
 */
@Entity
@Table(name = "LICENCE_USER")
public class User implements Serializable {
    private long id;
    private String name;
    private String email;

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
}
