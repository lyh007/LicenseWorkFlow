<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/taglibs.jsp" %>
<html>
<head>
    <base href="<%=basePath%>">
    <title>技术人员-添写申请表单</title>
</head>
<body>
<table border="0" width="100%">
    <tr>
        <td align="left"></td>
        <td align="right">当前用户: <s:property value="#session.sessionUser.realName"/> &nbsp;&nbsp;&nbsp;&nbsp;
            用户组：
            <s:iterator value="#session.sessionUser.groups">
                <s:property value="cnName"/>
            </s:iterator>
            &nbsp;&nbsp;&nbsp;&nbsp;【<a href="/logout.action">退出</a>】
        </td>
    </tr>
</table>
<fieldset>
    <legend>申请信息</legend>
    <table border="1" width="100%">
        <caption>申请信息</caption>
        <thead>
        <tr>
            <td>序号</td>
            <td>客户姓名</td>
            <td>合同金额（单位：万元）</td>
            <td>License类型</td>
            <td>申请时间</td>
            <td>申请人</td>
            <td>流程状态</td>
            <td>操作</td>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
    <table name="table" border="0" width="100%">
        <tr>
            <td><a href="instructor/instructor!request.action">发起申请</a></td>
        </tr>
    </table>
</fieldset>
</body>
</html>