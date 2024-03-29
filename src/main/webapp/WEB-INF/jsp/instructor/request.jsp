<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/taglibs.jsp" %>
<html>
<head>
    <base href="<%=basePath%>">
    <title>技术支持-填写请求表单</title>
</head>
<body>
<%@include file="../common/header.jsp" %>
<fieldset>
    <legend>申请表(技术人员填写)</legend>
    <form action="instructor/instructor!submitIssue.action" method="post">
        <input type="hidden" name="processDefinitionId" value="${processDefinitionId}">
        <table name="table" border="1" width="100%">
            <tr>
                <td align="right">客户名称：</td>
                <td align="left"><input type="text" name="issue.costumeName" value="大燕国"/></td>
            </tr>
            <tr>
                <td align="right">预计合同金额：</td>
                <td align="left"><input type="text" name="issue.money" value="30"/>（单位：万元）</td>
            </tr>
            <tr>
                <td align="right">注册License类型：</td>
                <td align="left">
                    <select name="issue.licenseType" style="width:155px">
                        <option value="0" selected>临时</option>
                        <option value="1">永久</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td align="right">销售姓名：</td>
                <td align="left">
                    <select name="venditionUser" style="width:155px">
                        <option value="mrf" checked>慕容复</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td align="right">申请人：</td>
                <td align="left"><s:property value="#session.sessionUser.realName"/>
                </td>
            </tr>
            <tr>
                <td align="right">申请时间：</td>
                <td align="left">
                    <input type="text" name="requestTime" value="${nowDateTime}" readOnly/>
                </td>
            </tr>
            <tr>
                <td align="center" colspan="2"><input type="submit" value="提交"/></td>
            </tr>
        </table>
    </form>
</fieldset>
<table name="table" border="0" width="100%">
    <tr>
        <td align="center">【<a href="instructor/instructor.action">返回</a>】</td>
    </tr>
</table>
</body>
</html>