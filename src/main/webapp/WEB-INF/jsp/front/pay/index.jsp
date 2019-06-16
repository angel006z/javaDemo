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
<title>支付首页</title>

<link href="<%=basePath%>/static/css/normalize.css?v=1.0.0"
	rel="stylesheet" type="text/css" />
<link href="<%=basePath%>/static/css/sysbase.css?v=1.0.0"
	rel="stylesheet" type="text/css" />
<link href="<%=basePath%>/static/css/sysrespond.css?v=1.0.0"
	rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="<%=basePath%>/static/css/reset.css?v=1.0.0">
    <link rel="stylesheet" href="<%=basePath%>/static/css/index.css?v=1.0.1">
</head>
<body>
<div class="container">
    <div class="container-title">
      <h2>充值中心</h2>
    </div>
    <div class="container-main">
      <div class="row clearfix">
        <div class="title"> 当前充值账号：</div>
        <div class="content">
         AAA
        </div>
      </div><div class="row clearfix">
        <div class="title"> 请选择充值金额：</div>
        <div class="content">
          
        </div>
      </div>
      <div class="row charge-custom clearfix">
        <div class="title"> 请输入充值金额：</div>
        <div class="content">
          <input type="text" id="price" value="0.01" name="price" maxlength="10" autocomplete="off"> 元
        </div>
      </div>
      <div class="row charge-cost clearfix">
        <div class="title"> 应付金额：</div>
        <div class="content">
          <span class="number" style="color:#ff6a06">100</span>
          <span class="number1">元</span>
        </div>
      </div>
      <div class="row clearfix">
        <div class="title"> 支付方式：</div>
        <div class="content">
          
        </div>
      </div>
      <div class="row clearfix">
        <div class="title">&nbsp;</div>
        <div class="content">
          <button class="confirm-charge">确认充值</button>
        </div>
      </div>
    </div>
  </div>
  <div id="divDialogPayLoadingTip" style="display:none;">
  		<div>请在新打开的页面中完成支付</div>
  		<div>请不要关闭此窗口，成功完成付款后将自动跳转，若未跳转可根据您的情况点击下面按钮</div>
  </div>
  <!-- Script Begin -->
	<script src="<%=basePath%>/static/js/jquery/jquery/1.11.3/jquery.js"
		type="text/javascript"></script>
	<script src="<%=basePath%>/static/js/layer/layer/3.1.1/layer.js"
		type="text/javascript"></script>
		<script src="<%=basePath%>/static/js/layer/laypage/1.3.0/laypage.js"
		type="text/javascript"></script>
	<script src="<%=basePath%>/static/js/missy/missy/2.2.0/missy.js"
		type="text/javascript"></script>
	<!-- Script End -->
</body>
</html>
<script type="text/javascript">
$(function(){
	$(".confirm-charge").click(function(){
		confirmCharge();
	});
	
});

function confirmCharge() {
	var layerLoadIndex;
	$.ajax({
		url : "confirmCharge",
		data : {
			price:$("#price").val()
		},
		type : "post",
		dataType : "json",
		beforeSend : function() {
			layerLoadIndex = MISSY.iShowLoading("正在执行中，请稍候");
		},
		ContentType : "application/json;charset=utf-8",
		success : function(response) {
			if(response.errorCode!="1"){
				MISSY.iWrongMessage(response.errorCode,response.errorMessage);
				return;
			}
			console.log(response.form)
			MISSY.iSuccessMessage(response.errorMessage);
			
			  

			$(".container").html(response.form);
		},
		error : function(xmlHttpRequest, textStatus, errorThrown) {
			MISSY.iDebugAjaxError(xmlHttpRequest, textStatus, errorThrown);
			MISSY.iSystemAjaxError();
		},
		complete : function(xmlHttpRequest, textStatus) {
			MISSY.iHideLoading(layerLoadIndex);
		}
	});
}
</script>