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
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kevin
 * @version Revision: 1.00 Date: 11-9-23下午2:58
 * @Email liuyuhui007@gmail.com
 */
@Controller
@Scope("prototype")
@ParentPackage(value = "default")
@Namespace("/")
@Results({
        @Result(name = "login", location = "/WEB-INF/jsp/login.jsp")
})
public class LogoutAction extends BaseAction {
    private List<User> users = new ArrayList<User>();
    @Resource
    private UserService userService;

    @Override
    public String execute() throws Exception {
        HttpSession session = request.getSession();
        if (session != null) {
            //Session无效
            session.invalidate();
        }
        users = userService.getAllUsers();
        return "login";
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
