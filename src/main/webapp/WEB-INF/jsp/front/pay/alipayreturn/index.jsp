<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>支付宝同步跳转页面</title>
</head>
<body>
<%-- <script>
location.href="<%=basePath%>/front/pay/charge/index"
</script> --%>
</body>
</html>