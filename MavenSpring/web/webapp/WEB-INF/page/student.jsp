<%--
  Created by IntelliJ IDEA.
  User: zhixin.lv
  Date: 2019/1/29
  Time: 13:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="foem" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Spring MVC表单处理(复选框)</title>
</head>
<body>

<h2>用户信息 - </h2>
<form:form method="POST" action="/addUser">
    <table>
        <tr>
            <td><form:label path="username">用户名：</form:label></td>
            <td><form:input path="username" /></td>
        </tr>
        <tr>
            <td><form:label path="id">id：</form:label></td>
            <td><form:input path="id" /></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="提交"/>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>