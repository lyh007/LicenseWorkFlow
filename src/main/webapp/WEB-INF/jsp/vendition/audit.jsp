<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/taglibs.jsp" %>
<html>
<head>
    <base href="<%=basePath%>">
    <title>销售人员-工单审核</title>
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
            <td align="left"><s:if test="licenseType==0">
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
<fieldset>
    <legend>事项审核</legend>
    <form action="vendition/vendition!audit.action" method="post">
        <input type="hidden" name="taskId" value="<s:property value="taskId"/>">
        <%--<input type="hidden" name="processInstanceId" value="<s:property value="issue.processInstanceId"/>">--%>
        <%--<input type="hidden" name="auditDept" value="<s:iterator value="#session.sessionUser.groups"><s:property value="name"/></s:iterator>">--%>
        <%--<input type="hidden" name="auditUser" value="<s:property value="#session.sessionUser.name"/>">--%>
         <input type="hidden" name="issue.id" value="<s:property value="issue.id"/>"/>
        <input type="radio" name="result" value="1" checked/>同意
        <input type="radio" name="result" value="0"/>不同意
        <br/>
        审批意见：
        <textarea name="auditNotion" rows="4" cols="40">同意执行</textarea>
        <br><br>
        审核单位：<s:iterator value="#session.sessionUser.groups"><s:property value="cnName"/></s:iterator><br>
        审核时间：<input type="text" name="auditTime" value="<s:property value="nowDateTime"/>" readOnly/><br>
        审核人： <s:property value="#session.sessionUser.realName"/>
        <br>
        <input type="submit" value="提交"/>
    </form>
</fieldset>
<table name="table" border="0" width="100%">
    <tr>
        <td align="center">【<a href="vendition/vendition.action">返回</a>】</td>
    </tr>
</table>
</body>
</html>