<%@ page import="com.controller.Controller" %>
<%@ page import="com.controller.Admin_Controller" %><%--
  Created by IntelliJ IDEA.
  User: Adam
  Date: 2017/3/5
  Time: 15:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>请登录</title>
</head>
<body>
<h3>请登录</h3>
<form method="post">
    <table>
        <tr/>
        <td>用户名:</td>
        <td><input type="text" name="admin_name"></td>
        <tr/>
        <td>密码:</td>
        <td><input type="password" name="admin_password"></td>
        <tr/>
        <td><input type="submit" value="确定"></td>
        <td><input type="reset" value="取消"></td>
    </table>
</form>
<%
    String name = request.getParameter("admin_name");
    String password = request.getParameter("admin_password");
    Admin_Controller ac = new Admin_Controller();
    if(name !=null || password != null) {
        if (ac.login(name, password)) {
            response.sendRedirect("user.jsp");
        } else {
            out.print("<script>alert('用户名或密码错误')</script>");
        }
    }
%>
</body>
</html>
