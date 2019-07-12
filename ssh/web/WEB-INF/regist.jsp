<%@ taglib prefix="s" uri="/struts-tags" %>
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
<script>
    function checkForm() {
        // 校验用户名:
        // 获得用户名文本框的值:
        var username = document.getElementById("username").value;
        if (username == null || username == '') {
            alert("用户名不能为空!");
            return false;
        }
    }
    function checkUsername() {
        var username = document.getElementById("username").value;

        var xhr = createXmlHttp();

        xhr.onreadystatechange = function () {
            if (xhr.readyState==4){
                if (xhr.status==200){
                    document.getElementById("span11").innerHTML = xhr.responseText;
                }
            }
        }
        xhr.open("GET","${pageContext.request.contextPath}/user_findByUsername.action?time="+new Date().getTime()+"&username="+username,true);

        xhr.send(null);
    }

    function createXmlHttp(){
        var xmlHttp;
        try{ // Firefox, Opera 8.0+, Safari
            xmlHttp=new XMLHttpRequest();
        }
        catch (e){
            try{// Internet Explorer
                xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
            }
            catch (e){
                try{
                    xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
                }
                catch (e){}
            }
        }

        return xmlHttp;
    }
</script>
</head>
<html>
<head>
    <title>regist</title>
</head>
<body>
<form action="user_add" method="post" onsubmit="return checkForm();">
    <table>
        <tr>
            <th>test：</th>
            <td>
                <input type="text" id="test" name="test1" class="text" maxlength="20" />
            </td>
        </tr>
        <tr>


            <th>排名:</th>
            <td>
                <input type="text" name="paiming" >
                <span><s:fielderror fieldName="paiming"/></span>
            </td>
        </tr>
        <tr>
            <th>用户名：</th>
            <td>
                <input type="text" id="username" name="username" class="text" maxlength="20" onblur="checkUsername()"/>
                <span id="span11"></span>
                <!--<span><s:fielderror fieldName="username"/></span>-->
            </td>
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
