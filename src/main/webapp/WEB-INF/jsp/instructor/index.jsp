<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/taglibs.jsp" %>
<html>
<head>
    <base href="<%=basePath%>">
    <title>技术支持-主页</title>
</head>
<body>
<<%@include file="../common/header.jsp" %>
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
                    <td><a href="instructor/instructor!view.action?issue.id=<s:property value="id"/>">查看</a></td>
                </tr>
            </s:iterator>
        </s:if>
        <s:else>
            无申请记录
        </s:else>
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