<%--
  Created by IntelliJ IDEA.
  User: zhixin.lv
  Date: 2019/3/26
  Time: 15:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册的信息</title>
</head>
<body>
<h2>提交的学生信息如下 - </h2>
<table>
    <tr>
        <td>姓名：</td>
        <td>${name}</td>
    </tr>
    <tr>
        <td>年龄：</td>
        <td>${age}</td>
    </tr>
    <tr>
        <td>地址：</td>
        <td>${address}</td>
    </tr>
</table>
<p>
    今天的日期是: <%= (new java.util.Date()).toLocaleString()%>
</p>
</body>
</html>
