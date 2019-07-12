<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: zhixin.lv
  Date: 2019/5/9
  Time: 18:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<form id="loginForm" action="${ pageContext.request.contextPath }/student_login.action"  method="post" novalidate="novalidate">
    <table>
        <tbody><tr>
            <th>
                用户名:
            </th>
            <td>
                <input type="text" id="username" name="username" class="text" maxlength="20">

            </td>
        </tr>
        <tr>
            <th>
                密&nbsp;&nbsp;码:
            </th>
            <td>
                <input type="password" id="password" name="password" class="text" maxlength="20" autocomplete="off">
            </td>
        </tr>

        <tr>
            <th>&nbsp;

            </th>
            <td>
                <input type="submit" class="submit" value="登 录">
            </td>
        </tr>
        <tr class="register">
            <th>&nbsp;

            </th>
            <div><s:actionerror /></div>
            <td>
                <dl>
                    <dt>还没有注册账号？</dt>
                    <dd>
                        立即注册即可体验在线购物！
                        <a href="./会员注册.htm">立即注册</a>
                    </dd>
                </dl>
            </td>
        </tr>
        </tbody></table>
</form>
</html>
