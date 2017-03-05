<%@ page import="java.util.Calendar" %><%--
  Created by IntelliJ IDEA.
  User: Adam
  Date: 2017/3/5
  Time: 15:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员页面</title>
</head>
<body>
    <%
       String helloword  = (String) application.getAttribute("helloword");
    %>
    <h1><%=helloword%>管理员</h1>
</body>
</html>
