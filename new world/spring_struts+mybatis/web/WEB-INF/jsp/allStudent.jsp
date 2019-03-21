<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 <%
         String path = request.getContextPath();
         String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                 + path + "/";
     %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <base href="<%=basePath%>">
        <script type="text/javascript" src="js/jquery-1.7.1.js"></script>
        <title>用户列表</title>
        <script type="text/javascript">
            function del(id){
                         alert("删除");
                         $.get("<%=basePath%>
                     student/delStudent?id=" + id, function(data) {
                                 if ("success" == data.result) {
                                     window.location.reload();
                                 } else {
                                     alert("删除失败");
                                 }
                         });
                 }
        </script>
    </head>
    <body>
         <h6>
                 <a href="<%=basePath%>student/toAddStudent">添加用户</a>
             </h6>
         <table border="1">
                 <tbody>
                 <tr>
                         <th>姓名</th>
                         <th>年龄</th>
                         <th>操作</th>
                     </tr>
                 <c:if test="${!empty studentList }">
                         <c:forEach items="${studentList}" var="student">
                             <tr>
                                 <td>${student.name}</td>
                                 <td>${student.birth}</td>
                                 <td><a href="<%=basePath%>student/getStudent?id=${student.id}">编辑</a>
                                     <a href="javascript:del('${student.id }')">删除</a></td>
                             </tr>
                         </c:forEach>
                     </c:if>
             </tbody>
             </table>
     </body>
</html>