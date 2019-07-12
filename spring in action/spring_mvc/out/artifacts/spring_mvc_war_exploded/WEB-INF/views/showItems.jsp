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
mcv Test
</body>
    <c:forEach items="${list}" var="item" varStatus="id"  >
        ${id.index}${item}
    </c:forEach>

</html>
