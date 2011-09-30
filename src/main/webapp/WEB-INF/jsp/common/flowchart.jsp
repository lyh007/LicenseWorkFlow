<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/taglibs.jsp" %>
<html>
<head>
    <base href="<%=basePath%>">
    <title>流程图页面</title>
</head>
<body>
     <img src="flowchart!outImage.action?processInstanceId=<s:property value="processInstanceId"/>" alt="流程图" style="position:absolute;left:0px;top:0px;">
      <div style='top:238;left:89;width:92;height:52;position:absolute;transparent;border: 2 solid #FF0000;'></div>
</body>
</html>