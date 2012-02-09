package com.lyh.licenseworkflow.web.action.majordomo;

import com.lyh.licenseworkflow.web.base.BaseAction;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.omg.PortableInterceptor.SUCCESSFUL;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * 销售负责人操作
 * Created by IntelliJ IDEA.
 * User: kevin
 * Date: 12-2-9
 * Time: 上午10:56
 */
@Controller
@Scope("prototype")
@ParentPackage(value = "default")
@Namespace("/majordomo")
@Results({
        @Result(name = "index", location = "/WEB-INF/jsp/majordomo/index.jsp"),
        @Result(name = "indexAction", location = "/vendition/vendition.action", type = "redirect")
})
public class MajordomoAction extends BaseAction {
    @Override  //默认销售负责人负责的工单列表
    public String execute() throws Exception {
        return "index";
    }
}
