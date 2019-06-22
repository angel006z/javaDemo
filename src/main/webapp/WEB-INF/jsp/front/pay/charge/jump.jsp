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
<title>正在跳转到支付页面</title>
	<!-- Script Begin -->
	<script src="<%=basePath%>/static/js/jquery/jquery/1.11.3/jquery.js"
			type="text/javascript"></script>
	<script src="<%=basePath%>/static/js/layer/layer/3.1.1/layer.js"
			type="text/javascript"></script>
	<script src="<%=basePath%>/static/js/missy/missy/2.2.0/missy.js"
			type="text/javascript"></script>
	<script src="<%=basePath%>/static/js/jquery/base64/0.0.3/jquery.base64.js"
			type="text/javascript"></script>
	<!-- Script End -->
</head>
<body>
<div id="div-form-pay" style="display: none;"></div>
</body>
</html>
<script type="text/javascript">
	var formMsg='<%=com.meida.common.util.RequestParameters.getString("formMsg") %>';
$(function(){
   $("#div-form-pay").html($.base64.decode(formMsg,"utf-8"));
	
});
</script>