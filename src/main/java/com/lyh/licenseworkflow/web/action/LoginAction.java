package com.lyh.licenseworkflow.web.action;

import com.lyh.licenseworkflow.po.User;
import com.lyh.licenseworkflow.service.UserService;
import com.lyh.licenseworkflow.system.util.LicenseWorkFlowConstants;
import com.lyh.licenseworkflow.web.base.BaseAction;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * 登录
 *
 * @author kevin
 * @version Revision: 1.00 Date: 11-9-23下午1:30
 * @Email liuyuhui007@gmail.com
 */
@Controller
@Scope("prototype")
@ParentPackage(value = "struts-default")
@Namespace("/")
@Results({
        @Result(name = "success", location = "/index.jsp"),
        @Result(name = "login", location = "/WEB-INF/jsp/login.jsp")
})
public class LoginAction extends BaseAction {
    private String name;
    private String password;
    private List<User> users = new ArrayList<User>();
    @Resource
    private UserService userService;

    @Override
    public String execute() throws Exception {
        users = userService.getAllUsers();
        if (StringUtils.isEmpty(name)) {
            addActionError("please input user name!");
            return "login";
        }
        if (StringUtils.isEmpty(password)) {
            addActionError("please input user password!");
            return "login";
        }
        User user = userService.getByName(name);
        if (user == null) {
            addActionError("user is not existed!");
            return "login";
        }
        if (!user.getPassword().equals(password)) {
            addActionError("user password is wrong!");
            return "login";
        }
        HttpSession session = request.getSession();
        session.setAttribute(LicenseWorkFlowConstants.SESSION_USER, user);
        return SUCCESS;
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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
