package com.lyh.licenseworkflow.web.filter;

import com.lyh.licenseworkflow.system.util.LicenseWorkFlowConstants;
import com.lyh.licenseworkflow.system.util.LogUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kevin
 * @version Revision: 1.00 Date: 11-9-21上午10:27
 * @Email liuyuhui007@gmail.com
 */
public class LoginFilter implements Filter {
    /**
     * 所有不需要进行权限判断的请求url片段
     */
    private List<String> ingoreUrls = new ArrayList<String>();

    private Logger logger = LogUtil.getLogger(LogUtil.SYSTEM_LOG);

    public void init(FilterConfig filterConfig) throws ServletException {
        String ignoreStr = filterConfig.getInitParameter("ignoreUrl");
        if (StringUtils.isNotEmpty(ignoreStr)) {
            String igs[] = ignoreStr.split(",");
            for (String ig : igs) {
                ingoreUrls.add(ig.trim());
            }
        }
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        StringBuffer buffer = httpRequest.getRequestURL();// 得到请求url
        String queryString = httpRequest.getQueryString();// 得到请求参数字符串
        HttpSession session = httpRequest.getSession();
        //用户请求的完整地址
        String wonderUrl = URLEncoder.encode(httpRequest.getRequestURI() + "?" + httpRequest.getQueryString(), "UTF-8");
        String fullUrl = buffer.toString();// 构造完整的url地址，该地址是客户端浏览器请求web服务器时的完整地址
        boolean checkPermission = true;// 是否需要检查用户是否登录，有些请求是不需要检查的，比如请求图片，请求CSS，请求JS或者是JVM内部发起的获取页面的请求
        for (String ingoreUrl : ingoreUrls) {
            if (fullUrl.indexOf(ingoreUrl) != -1) {// 客户端请求的URL字符串中包括不需要检查是否登录的字符串片段，则不需要检查权限
                checkPermission = false;
                break;
            }
        }
        if (!checkPermission) {// 不需要检查是否登录，直接放行
            filterChain.doFilter(httpRequest, httpResponse);
        } else {// 需要检查权限，判断用户是否已经登录
            String loginUser = (String) httpRequest.getSession().getAttribute(LicenseWorkFlowConstants.SESSION_USER);
            if (loginUser == null) {
                //如果被拦截，那么先记录上次想请求的地址，以便下次登录后直接跳转过去。
                //session失效
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/index.jsp?wonderUrl=" + wonderUrl);
                return;
            } else {
                filterChain.doFilter(httpRequest, httpResponse);
            }
        }
    }

    public void destroy() {
    }
}
