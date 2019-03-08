<%--
  Created by IntelliJ IDEA.
  User: zhixin.lv
  Date: 2018/11/12
  Time: 14:36
  To change this template use File | Settings | File Templates.
--%>
<!--<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <base href="<%=basePath%>">

    <title>My JSP 'index.jsp' starting page</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
</head>
-->

<body>
This is my JSP page. <br>
<a href="http://localhost:8080/ServletTest/1">get请求1</a><br/>
<!-- 对于一个html页面来说，如果没有以http开始，则默认的前面会加上
    协议类型://目前这个页面所在的服务器:目前端口/目前项目/你给的这个名称 -->
<a href="1">get请求2</a><hr/>
<form method = "post" action="1">
    <input type="submit" value="提交"/>
</form>
</body>
</html>
