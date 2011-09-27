package com.lyh.licenseworkflow.web.action;

import com.lyh.licenseworkflow.po.User;
import com.lyh.licenseworkflow.service.UserService;
import com.lyh.licenseworkflow.web.base.BaseAction;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kevin
 * @version Revision: 1.00 Date: 11-9-21上午11:05
 * @Email liuyuhui007@gmail.com
 */
@Controller
@Scope("prototype")
@ParentPackage(value = "default")
@Namespace("/")
@Results({
        @Result(name = "success", location = "/WEB-INF/jsp/login.jsp")
})
public class IndexAction extends BaseAction {
    @Resource
    private UserService userService;
    private List<User> users = new ArrayList<User>();

    @Override
    public String execute() throws Exception {
        users = userService.getAllUsers();
        return SUCCESS;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
