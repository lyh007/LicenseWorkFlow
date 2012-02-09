<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/taglibs.jsp" %>
<html>
<head>
    <base href="<%=basePath%>">
    <title>销售负责人-主页</title>
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
    </table>
</fieldset>
</body>
</html>