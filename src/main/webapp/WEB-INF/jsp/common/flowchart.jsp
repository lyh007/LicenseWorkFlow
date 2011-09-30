<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/taglibs.jsp" %>
<html>
<head>
    <base href="<%=basePath%>">
    <title>流程图页面</title>
</head>
<body>
     <img src="flowchart!outImage.action?processInstanceId=<s:property value="processInstanceId"/>" alt="流程图" style="position:absolute;left:0px;top:0px;">
    <s:iterator value="activityCoordinates" var="activityCoordinate" status="activityCoordinateStatus">
           <div style='top:<s:property value="y"/>;left:<s:property value="x"/>;width:<s:property value="width"/>;height:<s:property value="height"/>;position:absolute;transparent;border: 2 solid #FF0000;'></div>
    </s:iterator>
</body>
</html>