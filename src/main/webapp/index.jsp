<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
String path = request.getContextPath(); 
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index</title>
</head>
<body>
<p>${999%4}</p>
server.xml 的path修改为/  则不需要项目名称
<br>
<a href="http://localhost:9090/backend/basic/user/list?NodeId=100">user list</a>
<br>
<a href="http://localhost:9090/backend/basic/user/role?NodeId=200">role list</a>
<br>
<a href="http://localhost:9090/backend/basic/dept/list?NodeId=300">dept list</a>

</body>
</html>