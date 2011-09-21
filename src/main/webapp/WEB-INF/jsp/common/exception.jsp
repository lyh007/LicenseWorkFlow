<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="taglibs.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Ocean Runtime Exception</title>
</head>
<body>
<center>
    <div>
        <s:text name="%{exception.errorCode}"/><br>
        <input name="" type="button" class="gx_w_bn all_butt" value="返 回" onclick="javascript:history.go(-1);"/>
    </div>
</center>
</body>
</html>