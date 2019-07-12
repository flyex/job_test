<%--
  Created by IntelliJ IDEA.
  User: zhixin.lv
  Date: 2019/5/10
  Time: 11:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>hi</title>
</head>
<body>
    hello!
    <tr>
        <s:if test="#session.existStudent==null">
           <th>哈哈 你还没登陆</th>
        </s:if>
        <s:else>
            <th colspan="5">id：<s:property value="#session.existStudent.id" /></th>
            <th colspan="5">姓名：<s:property value="#session.existStudent.name" /></th>
            <th colspan="5">年纪：<s:property value="#session.existStudent.age" /></th>
            <!--<th colspan="5">生日：<s:property value="#session.existStudent.birth"  /></th>-->
            <th colspan="5">生日：<s:date name="#session.existStudent.birth" format="yyyy-MM-dd" /></th>
        </s:else>
    </tr>
</body>
</html>
