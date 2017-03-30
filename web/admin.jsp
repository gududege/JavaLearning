<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<h1><c:out value="${applicationScope.get('helloword')}"/>,管理员</h1>
<c:out value="你的账户为：${param['admin_name']} 密码为：${param['admin_password']}"/>
<form method="post" action="admin.jsp">
    <input type="text" name="age" value="${param.age}"/>
    <input type="submit" value="提交"/>
</form><br/>
</body>
</html>
