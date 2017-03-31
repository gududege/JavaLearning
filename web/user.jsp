<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.night.model.User" %><%--
  Created by IntelliJ IDEA.
  User: Adam
  Date: 2017/3/5
  Time: 15:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    String helloword = (String) application.getAttribute("helloword");

    String is_quit = request.getParameter("user_quit");
    if (is_quit != null && is_quit.length() > 0){
        Cookie userUUID = new Cookie("userUUID", "");
        Cookie user_password = new Cookie("user_password", "");
        userUUID.setMaxAge(0);
        user_password.setMaxAge(0);
        response.addCookie(userUUID);
        response.addCookie(user_password);
        response.sendRedirect("index.jsp");
    }
%>
<h1><%=helloword%>，<c:out value="${user.getName()}"/></h1><hr/>
<table>
    <tr>
    <td>账户信息：</td>
    </tr>
    <tr>
    <td>手机号：<c:out value="${user.getPhone()}"/></td>
    </tr>>
    <tr>
    <td>密码：<c:out value="${user.getPassword()}"/></td>
    </tr>
</table>
<form method="post"><input type="submit" name="user_quit" value="退出登录"></form>
</body>
</html>
