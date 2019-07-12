<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: zhixin.lv
  Date: 2019/3/26
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生注册</title>
</head>

<body>
<h2>填写信息</h2>
    <form:form method="post" action="/addstudent">
        <table>
            <tr>
                <td><form:label path="name">名字：</form:label></td>
                <td><form:input path="name" /></td>
            </tr>
            <tr>
                <td><form:label path="address">地址：</form:label></td>
                <td><form:input path="address" /></td>
            </tr>
            <tr>
                <td><form:label path="age">年纪:</form:label></td>
                <td><form:input path="age" /></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="提交表单"/>
                </td>
            </tr>
        </table>
    </form:form>
</body>
</html>
