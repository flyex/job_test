<%--
  Created by IntelliJ IDEA.
  User: zhixin.lv
  Date: 2019/4/8
  Time: 9:59
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<html>
<head>
    <title>regist</title>
</head>
<body>
<form action="user2_regist.action" method="post">
    <table>
        <tr>
            <td>用户名：</td>
            <td>
                <input type="text" id="username" name="username" class="text" maxlength="20" onblur="checkUsername()"/>
            <td><span id="span1"></span></td>
            </td>
        </tr>
        <tr>



            <td>排名:</td>
            <td><input type="text" name="paiming" ></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="注册" />
                <input type="reset" value="重置" />
            </td>
        </tr>
    </table>
</form>

</body>
</html>
