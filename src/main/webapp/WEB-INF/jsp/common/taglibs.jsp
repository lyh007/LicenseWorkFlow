<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" errorPage="/common/error.jsp" contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>