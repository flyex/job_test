<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: zhixin.lv
  Date: 2019/3/23
  Time: 15:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>qq</title>
    <link rel="stylesheet"
          type="text/css"
          href="<c:url value="/resources/style.css" />">
</head>
<body>
<h1>welcome to qq</h1>
<a href="<c:url value="/qq_discuss" />">discuss</a>
<a href="<c:url value="/qq/register">">register</a>
</body>
</html>

