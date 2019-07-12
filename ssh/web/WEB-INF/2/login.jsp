<%--
  Created by IntelliJ IDEA.
  User: zhixin.lv
  Date: 2019/4/4
  Time: 17:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>注册用户</title>
</head>

<body>
<form action="${pageContext.request.contextPath}/user2_login.action" method="post" >
    <table>
        <tr>
            <s:actionerror />
        </tr>
        <tr>
            <td>用户名：</td>
            <td><input type="text" name="username"  /> </td>
        </tr>
        <tr>
            <td>排名：</td>
            <td><input type="text" name="paiming"  /> </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="登录" />
                <input type="reset" value="重置" /></td>
        </tr>
    </table>
</form>
</body>
</html>
