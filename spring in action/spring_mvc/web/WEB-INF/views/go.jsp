<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: zhixin.lv
  Date: 2019/3/26
  Time: 9:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>mvc</title>
</head>
<body>
<h1>mcv Test</h1>
</body>
    <c:forEach items="${list2}" var="item" varStatus="id"  >
       <h2>${id.index}${item}</h2>
    </c:forEach>

</html>
