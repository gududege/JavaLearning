
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册用户</title>
    <script>

    </script>
</head>
<body>
    <h3>请输入注册信息</h3>
    <hr/>
    <form action="registservlet" method="post">
        <table>
            <tr/>
            <td>用户名：<input type="text" name="user_Name"/></td>
            <tr/>
            <td>密码&nbsp;&nbsp;：<input type="password" name="user_password"/></td>
            <tr/>
            <td>手机号：<input type="text" name="user_phone"/></td>
            <tr/>
            <td><input type="submit" value="提交"/><input type="reset" value="重置"></td>
        </table>
    </form>
</body>
</html>
