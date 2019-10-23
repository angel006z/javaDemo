<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body data-base-path="<%=basePath%>">
<form id="from" action="<%=basePath%>doUpload" method="post" enctype="multipart/form-data">
<input type="file" name="file">
<input type="submit" value="upload"/>
</form>
</body>
</html>