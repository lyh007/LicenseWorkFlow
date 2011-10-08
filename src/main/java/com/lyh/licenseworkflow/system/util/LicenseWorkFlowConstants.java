package com.lyh.licenseworkflow.system.util;

import com.lyh.licenseworkflow.po.Issue;
import com.lyh.licenseworkflow.web.vo.IssueVO;
import net.sf.cglib.beans.BeanCopier;

/**
 * 系统常量类
 *
 * @author kevin
 * @version Revision: 1.00 Date: 11-9-21上午10:41
 * @Email liuyuhui007@gmail.com
 */
public class LicenseWorkFlowConstants {
    /**
     * 用户key，存放到HttpSession中引用User对象
     */
    public final static String SESSION_USER = "sessionUser";
    /**
     * 用户key，存放到HttpSession中引用User对象的数据库标识
     */
    //  public final static String SESSION_USER_ID = "sessionUserId";
    /**
     * 用户key，存放到HttpSession中引用User对象的用户名
     */
    //  public final static String SESSION_USER_NAME = "sessionUserName";

    //技术支持  销售人员 销售负责人（销售总监及助理） License管理员 老板
    public static String[] groups = {"instructor", "vendition", "majordomo", "admin", "boss"};
    public static String[] groupNames = {"技术支持", "销售人员", "销售负责人", "License管理员", "老板"};

    /**
     * 工单Bean对象复制
     */
     public final static BeanCopier issuePOTOVOCopier = BeanCopier.create(Issue.class, IssueVO.class, false);
}
