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
	<!-- Script Begin -->
	<script src="<%=basePath%>/static/js/jquery/jquery/1.11.3/jquery.js"
			type="text/javascript"></script>
	<script src="<%=basePath%>/static/js/missy/missy/2.2.0/missy.js"
			type="text/javascript"></script>
	<!-- Script End -->
</head>
<body>
<div id="success">
	充值成功
</div>
<div id="failure" style="display: none;">
	如已支付但还未到账，请稍候系统正在处理中。
</div>
 <script>
	 $(function () {
         var result = '${result}';
         if(result=="success") {
             location.href = "<%=basePath%>/front/pay/accountRecharge/index"
         }else{
             $("#success").hide();
             $("#failure").show();
         }
     });

</script>
</body>
</html>