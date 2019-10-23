<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
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
	server.xml 的path修改为/ 则不需要项目名称
	<br>

	<a href="<%=basePath%>/backend/basic/upload/upload">upload list</a>
<br>
	<a href="<%=basePath%>/pay/accountRecharge/index">charge index</a>
<br>

	<a href="<%=basePath%>/pay/accountRecharge/record">charge record</a>
<br>
</body>
</html>