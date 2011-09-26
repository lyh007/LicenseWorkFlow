<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/taglibs.jsp" %>
<html>
<head>
    <base href="<%=basePath%>">
    <title>查看-填写请求表单</title>
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
        <%--<%--%>
            <%--if (auditList != null && auditList.size() > 0) {--%>
                <%--for (Map<String, Object> m : auditList) {--%>
        <%--%>--%>
        <%--<tr>--%>
            <%--<td><%=m.get("auditDept")%>--%>
            <%--</td>--%>
            <%--<td><%=JbpmUtil.getUserName((String) m.get("auditUser"))%>--%>
            <%--</td>--%>
            <%--<td><%=m.get("auditNotion")%>--%>
            <%--</td>--%>
            <%--<td><%=m.get("auditTime")%>--%>
            <%--</td>--%>
            <%--<td><%=m.get("auditResult")%>--%>
            <%--</td>--%>
        <%--</tr>--%>
        <%--<%--%>
                <%--}--%>
            <%--}--%>
        <%--%>--%>
    </table>
</fieldset>

<br>

</body>
</html>