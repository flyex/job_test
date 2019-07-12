<%--
  Created by IntelliJ IDEA.
  User: zhixin.lv
  Date: 2019/4/4
  Time: 9:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="/struts-tags" prefix="s"%>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>

  <tr>
    <ul>
      <li>
        <s:if test="#session.username==null" >
        <li><a href="${pageContext.request.contextPath}/user2_login">请登录</a></li>
        <li><a href="${pageContext.request.contextPath}/user2_regist">请注册</a></li>
        </s:if>
      </li>
      <s:else>
        <li>welcome
        登陆成功</li>
        <li><s:property value="#session.username"/> </li>
        <li><a href="${ pageContext.request.contextPath }/user2_quit.action">退出</a></li>
      </s:else>
    </ul>

  </tr>
  </body>
</html>
