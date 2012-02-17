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
    <legend>审批列表</legend>
    <table border="1" width="100%">
        <caption>审批列表</caption>
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
        <s:if test="issues!=null">
        <s:iterator value="issues" status="issueStatus">
        <tr>
            <td><s:property value="id"/></td>
            <td><s:property value="costumeName"/></td>
            <td><s:property value="money"/></td>
            <td>
                <s:if test="licenseType==0">
                    临时
                </s:if>
                <s:else>
                    永久
                </s:else>
            </td>
            <td><s:date name="requestTime" format="yyyy-MM-dd HH:mm:ss"/></td>
            <td><s:property value="requestUser.realName"/></td>
            <td>
                <s:if test="workFlowNodeName!=null && workFlowNodeName!=''">
                    <s:property value="workFlowNodeName"/>
                </s:if>
                <s:else>结束</s:else>
            </td>
            <td>
                <s:if test="showAudit">
                    <a href="admin/admin!preAudit.action?issue.id=<s:property value="id"/>&taskId=<s:property value="issueTask.id"/>">生成License</a>
                </s:if>
                <s:else>
                    <a href="instructor/instructor!view.action?issue.id=<s:property value="id"/>">查看</a>
                </s:else>
            </td>
            </s:iterator>
            </s:if>
        </tbody>
    </table>
</fieldset>
</body>
</html>