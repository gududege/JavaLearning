<%@ page import="com.controller.User_Controller" %><%--
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
<%--设置刷新不报警！--%>
<body><h3>请登录</h3>
<BR/>
<%
    User_Controller uc = new User_Controller();
    String name_cookie = "";
    String password_cookie = "";
    Cookie[] cookies = request.getCookies();
    for (Cookie c : cookies) {
        if (c.getName().equals("user_name")) {
            name_cookie = c.getValue();
        }
        if (c.getName().equals("user_password")) {
            password_cookie = c.getValue();
        }
    }
    //优先使用cookie自动登录
    if (name_cookie.length() > 0) {
        if (uc.login(name_cookie, password_cookie)) {
            response.sendRedirect("user.jsp");
        }
    } else {
        //接受form表单提交的登陆信息
        String name = request.getParameter("user_name");
        String password = request.getParameter("user_password");
        String[] str = request.getParameterValues("auto_login");

        //验证登录信息
        if (name != null && password != null) {
            if (uc.login(name, password)) {
                //设置cookie，有效期10天
                Cookie user_name = new Cookie("user_name", name);
                Cookie user_password = new Cookie("user_password", password);
                if (str != null && str.length > 0) {
                    user_name.setMaxAge(864000);
                    user_password.setMaxAge(864000);
                } else {
                    user_name.setMaxAge(0);
                    user_password.setMaxAge(0);
                }
                response.addCookie(user_name);
                response.addCookie(user_password);
                //跳转前将登录信息存到session里
                session.setAttribute("user_name", name);
                session.setAttribute("user_password", password);
                response.sendRedirect("user.jsp");
            } else {
                out.print("<script>alert('用户名或密码错误(里)')</script>");
            }
        }
    }
%>
<form method="post">
    <table>
        <tr/>
        <td>用户名:</td>
        <td><input type="text" name="user_name" value="<%=session.getAttribute("user_name")%>"></td>
        <tr/>
        <td>密码:</td>
        <td><input type="password" name="user_password" value=""></td>
        <tr/>
        <td><input type="checkbox" name="auto_login" checked>10天内自动登录</td>
        <tr/>
        <td><input type="submit" value="确定"></td>
        <td><input type="reset" value="取消"></td>
    </table>
</form>
<%

%>
</body>
</html>
