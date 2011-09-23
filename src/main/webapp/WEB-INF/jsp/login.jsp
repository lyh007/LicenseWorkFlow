<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common/taglibs.jsp" %>
<html>
<head><title>Simple jsp page</title></head>
<body>
<fieldset>
    <legend>登陆</legend>
    <s:actionerror/>
    <form action="login.action" method="post">
        用户名：<input type="text" name="name" value="${name}"/><br/>
        密&nbsp;&nbsp;&nbsp;&nbsp;码：<input type="password" name="password" value="${password}"/><br/>
        <input type="submit" value="登录"/>
    </form>
</fieldset>
<br><br>
<table name="table" border="1" width="100%">
    <tr>
        <td>序号</td>
        <td>用户名</td>
        <td>用户姓名</td>
        <td>用户角色</td>
    </tr>
    <s:iterator var="user" value="users" status="userStatus">
        <tr>
            <td>${userStatus.index+1}</td>
            <td>${user.name}</td>
            <td>${user.realName}</td>
            <td>
                <s:iterator value="#user.groups">
                    <s:property value="cnName"/>
                </s:iterator>
            </td>
        </tr>
    </s:iterator>
</table>
</body>
</html>