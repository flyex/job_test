<%--
  Created by IntelliJ IDEA.
  User: zhixin.lv
  Date: 2019/5/13
  Time: 13:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>hello</title>
    <style type="text/css">
        h2 {color: darkorchid;background-color: #F3F3F3;font-family: "Microsoft Tai Le"}
    </style>
</head>

<body style="background-color: yellowgreen">
<p>
    <a href="#tips">跳转name=tips的地方</a>
</p>
<h2>
    springmvc controller 正在运行
</h2>
<a  style="background-color: black" href="https://www.baidu.com">baidu</a>
<!--${pageContext.request.contextPath}-->
<img src="/picture/cs60005.png" width="40" height="60" >
<h1 style="text-align: center;font-family: 'Microsoft JhengHei UI'">居中显示</h1>
<table style="background-color: brown" border="2" bgcolor="#9acd32">
    <p style="background-color: grey;font-family: 'Microsoft JhengHei UI';color: blueviolet" >
        床前明月光，<br/>疑是地上霜。<br/>举头望明月，<br/>低头思故乡。
    </p>
    <p>
        <address>
    v信息: Creation of SecureRandom instance for<a href="https://www.baidu.com" target="_blank" style="text-decoration: none;font-family: 'Microsoft JhengHei UI Light';color: grey;background-color: darkorchid">to_baidu</a>.<br> session ID generation using [SHA1PRNG] took [164] milliseconds.
    5月 17, 2019 3:47:02 下午 org.apache.catalina.core.ApplicationContext log
        </address>
    <p>
        <abbr title="etcetera">show:etc.</abbr>
        <br/>
        <acronym title="World Wide Web">WWW</acronym>
        <br/>
        <bdo dir="rtl">
            Here is some Hebrew text 出输向反
        </bdo>
    </p>
    <p>
        一打有 <del>二十</del> <ins>十二</ins> 件。
        <br/>
        <code>
            int
        </code>
        <var>
            int
        </var>
        <pr>int</pr>
        <br/>
        <var>
            person = {
            firstName:"Bill",
            lastName:"Gates",
            age:50,
            eyeColor:"blue"
            }
        </var>
    </p>
    <br/>
    <a name="tips">基本的注意事项 - 有用的提示</a>
    </p>
    <p>图像放大测试</p>
    <img src="/picture/cs60006.png" width="100" height="100" alt="衣服" usemap="#testmap">
    <map name="testmap" id="testmap">
        <area shape="circle" coords="0,0,20"
              href="https://www.baidu.com"
              target="_blank"
              alt="1234"
        />
        <area shape="rect" coords="40,40,100,100"
              href="index.jsp"
              target="_blank"
              alt="12"
        />
    </map>
</table>
<table border="1" bgcolor="#2f4f4f" >
    <caption>标题</caption>
    <th>编号</th>
    <th colspan="2">姓名</th>
    <tr>
        <td>id</td>
        <td>first name</td>
        <td>last name</td>
    </tr>
    <tr>
        <td>&nbsp;</td>
        <td>null</td>
    </tr>
</table>
<p>table with frame:box</p>
<table frame="above">
    <tr>
        <th>id</th>
        <th>name</th>
    </tr>
    <tr>
        <td>1</td>
        <td>小华</td>
    </tr>
</table>
<form action="/upload" method="post" enctype="multipart/form-data">
    <table>
        <tr>
            <td>请选择文件：</td>
            <td><input type="file" name="file"></td>
        </tr>
        <tr>
            <td>上传文件</td>
            <td><input type="submit" value="submit"></td>
        </tr>
    </table>
</form>
</body>
</html>
