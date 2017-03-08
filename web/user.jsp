<%--
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
        Cookie user_name = new Cookie("user_name", "");
        Cookie user_password = new Cookie("user_password", "");
        user_name.setMaxAge(0);
        user_password.setMaxAge(0);
        response.addCookie(user_name);
        response.addCookie(user_password);
        response.sendRedirect("index.jsp");
    }
%>
<h1><%=helloword%>，<%=session.getAttribute("user_name")%>
</h1>
密码：<%=session.getAttribute("user_password")%>
<form method="post"><input type="submit" name="user_quit" value="退出登录"></form>
</body>
</html>
