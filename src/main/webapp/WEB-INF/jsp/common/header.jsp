<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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