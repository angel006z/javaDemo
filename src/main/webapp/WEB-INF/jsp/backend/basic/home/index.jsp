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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="renderer" content="webkit" />
<title>${title}</title>

<link href="<%=basePath%>/static/css/normalize.css?v=1.0.0"
	rel="stylesheet" type="text/css" />
<link href="<%=basePath%>/static/css/sysbase.css?v=1.0.0"
	rel="stylesheet" type="text/css" />
<link href="<%=basePath%>/static/css/sysrespond.css?v=1.0.0"
	rel="stylesheet" type="text/css" />
	
<body class="RightBody">
	

	<!-- Script Begin -->
	<script src="<%=basePath%>/static/js/jquery/jquery/1.11.3/jquery.js"
	type="text/javascript"></script>
<script src="<%=basePath%>/static/js/layer/layer/3.1.1/layer.js"
	type="text/javascript"></script>
<script src="<%=basePath%>/static/js/missy/missy/2.2.0/missy.js"
	type="text/javascript"></script>
	<!-- Script End -->
</body>
</html>
<script type="text/javascript">

</script>

