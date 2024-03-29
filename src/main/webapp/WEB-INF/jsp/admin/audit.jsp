<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/taglibs.jsp" %>
<html>
<head>
    <base href="<%=basePath%>">
    <title>License管理员-主页</title>
</head>
<body>
<%@include file="../common/header.jsp" %>
<fieldset>
    <legend>合同详细</legend>
    <table name="table" border="1" width="100%">
        <tr>
            <td align="left" width="150">客户名称：</td>
            <td align="left"><s:property value="issue.costumeName"/></td>
        </tr>
        <tr>
            <td align="left">预计合同金额</td>
            <td align="left"><s:property value="issue.money"/>（单位：万元）</td>
        </tr>
        <tr>
            <td align="left">注册License类型：</td>
            <td align="left"><s:if test="issue.licenseType==0">
                临时
            </s:if>
                <s:else>
                    永久
                </s:else>
            </td>
        </tr>
        <tr>
            <td align="left">销售姓名：</td>
            <td align="left"><s:property value="issue.venditionUser.realName"/></td>
        </tr>
        <tr>
            <td align="left">申请人</td>
            <td align="left"><s:property value="issue.requestUser.realName"/></td>
        </tr>
        <tr>
            <td align="left">申请时间</td>
            <td align="left"><s:date name="issue.requestTime" format="yyyy-MM-dd HH:mm:ss"/></td>
        </tr>
    </table>
</fieldset>
<br>

<fieldset>
    <legend>已审核意见</legend>
    <table name="table" border="1" width="100%">
        <tr>
            <td>审核角色</td>
            <td>审核人</td>
            <td>审核意见</td>
            <td>审核时间</td>
            <td>审核状态</td>
        </tr>
        <s:if test="issue.audits!=null">
            <s:iterator value="issue.audits" var="audit" status="auditStatus">
                <tr>
                    <td><s:property value="auditDept"/></td>
                    <td><s:property value="auditUser.realName"/></td>
                    <td><s:property value="auditNotion"/></td>
                    <td><s:date name="auditTime" format="yyyy-MM-dd HH:mm:ss"/></td>
                    <td><s:property value="auditResult"/></td>
                </tr>
            </s:iterator>
        </s:if>
    </table>
</fieldset>
<br>

<fieldset>
    <legend>生成License</legend>
    <form action="admin/admin!audit.action" method="post">
        <input type="hidden" name="taskId" value="<s:property value="taskId"/>">
        <input type="hidden" name="issue.id" value="<s:property value="issue.id"/>"/>
        <input type="hidden" name="auditDept" value="<s:iterator value="#session.sessionUser.groups"><s:property value="cnName"/></s:iterator>">
        <input type="hidden" name="licenseType" value="<s:property value="licenseType"/>">
        <br>
        生成License类型：<s:if test="issue.licenseType==0">临时</s:if><s:else>永久</s:else><br>
        创建单位：<s:iterator value="#session.sessionUser.groups"><s:property value="cnName"/></s:iterator><br>
        创建时间：<s:property value="nowDateTime"/><br>
        创建人：<s:property value="#session.sessionUser.realName"/>
        <br>
        <input type="submit" value="生成" />
    </form>
</fieldset>
<table name="table" border="0" width="100%">
    <tr>
        <td align="center">【<a href="admin/admin.action">返回</a>】</td>
    </tr>
</table>
</body>
</html>