<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    // 获取请求的路径
    String path = request.getContextPath();
    // getScheme：协议 getServerName：主机名 getServerPort：端口号
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>添加用户</title>
    <script type="text/javascript">
        function addStudent(){
            var form = document.forms[0];
            form.action = "<%=basePath%>student/addStudent";
            form.method = "post";
            form.submit();
        }
    </script>

</head>

<body>
<h1>添加用户</h1>
<form action="" name="StudentForm">
    姓名：<input type="text" name="name"> 年龄：<input type="text"
                                                     name="birth"> <input type="button" value="添加"
                                                                        onclick="addStudent()">
</form>
</body>
</html>