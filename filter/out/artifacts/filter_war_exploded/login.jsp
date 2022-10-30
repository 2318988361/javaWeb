<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/10/22
  Time: 23:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>管理后台登录</h1>
<%
    System.out.println("request=" + request);
%>
<form action="<%=request.getContextPath() %>/loginCheck" method="post">
    u：<input type="text" name="username"/> <br/><br/>
    p：<input type="password" name="password"/> <br/><br/>
    <input type="submit" value="用户登录"/></form>

</body>
</html>
