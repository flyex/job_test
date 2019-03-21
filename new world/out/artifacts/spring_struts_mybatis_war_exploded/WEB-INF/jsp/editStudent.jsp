<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>编辑用户</title>
    <script type="text/javascript">
        function updateStudent(){
            var form = document.forms[0];
            form.action = "<%=basePath%>
    student/updateStudent";
            form.method = "post";
            form.submit();
        }
    </script>

</head>

<body>
<h1>添加用户</h1>
<form action="" name="studentForm">
    <input type="hidden" name="id" value="${student.id }" /> 姓名：<input
        type="text" name="name" value="${student.name }" /> 年龄：<input
        type="text" name="birth" value="${student.age }" /> <input type="button"
                                                              value="编辑" onclick="updateStudent()" />
</form>
</body>

</html>