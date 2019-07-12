<%--
  Created by IntelliJ IDEA.
  User: zhixin.lv
  Date: 2018/11/12
  Time: 14:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*" deferredSyntaxAllowedAsLiteral="true" %>
<html>
<body>
test21+
<%= (String) request.getAttribute("name")%>
<%= (String) request.getParameter("name")%>
</body>
</html>
