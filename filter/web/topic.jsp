<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/10/23
  Time: 17:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>发表评论</title>
</head>
<body>
<h1>发表对阿凡达电影的评论</h1>
过滤词：苹果，香蕉  ~~ 有敏感词。。
<form action="/filter/topic/show.jsp" method="post">
用户：<input type="text" name="username"><br/>
    <p style="color: red" >${requestScope.get("alert")}</p>
<textarea name="topic" ></textarea><br/>
    <%response.setContentType("text/html;charset=utf-8");%>
<input type="submit" value="提交评论">
</form>
</body>
</html>
