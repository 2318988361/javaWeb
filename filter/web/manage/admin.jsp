<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/10/23
  Time: 0:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>后台管理</h1>
<%
    //验证request对象是和前面的filter是一个对象
    System.out.println("request=" + request);
%>
<a href="#">用户列表</a>||<a href="#">添加用户</a>||<a href="#">删除用户</a>
<hr/>
<img src="/filter/manage/shunping.jpg" height="300"/>

</body>
</html>
