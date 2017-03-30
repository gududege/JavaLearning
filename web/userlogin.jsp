<%@ page import="com.night.controller.User_Controller" %><%--
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
        if (c.getName().equals("user_Name")) {
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
        String name = request.getParameter("user_Name");
        String password = request.getParameter("user_password");
        String[] str = request.getParameterValues("auto_login");

        //验证登录信息
        if (name != null && password != null) {
            if (uc.login(name, password)) {
                //设置cookie，有效期10天
                Cookie user_Name = new Cookie("user_Name", name);
                Cookie user_password = new Cookie("user_password", password);
                if (str != null && str.length > 0) {
                    user_Name.setMaxAge(864000);
                    user_password.setMaxAge(864000);
                } else {
                    user_Name.setMaxAge(0);
                    user_password.setMaxAge(0);
                }
                response.addCookie(user_Name);
                response.addCookie(user_password);
                //跳转前将登录信息存到session里
                session.setAttribute("user_Name", name);
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
        <td><input type="text" name="user_Name" value="<%=session.getAttribute("user_Name")%>"></td>
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
<form method="post" action="regist.jsp">
    <input type="submit" value="注册新用户">
</form>
</body>
</html>
