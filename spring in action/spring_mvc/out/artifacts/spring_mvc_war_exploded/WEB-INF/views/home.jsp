<%--
  Created by IntelliJ IDEA.
  User: zhixin.lv
  Date: 2019/3/25
  Time: 17:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<html>
<head>
    <title>mvc</title>
</head>
<body>
mcv Test
</body>
<h1>
    <c:forEach var="x" begin="1" end="9" step="1">
        ${x*x}
    </c:forEach>
</h1>
</html>