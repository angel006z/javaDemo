<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="renderer" content="webkit" />
<meta name="viewport"
	content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
<title>${title}</title>
<link href="<%=basePath%>/static/css/normalize.css?v=7.0.0" rel="stylesheet"
	type="text/css" />
<link href="<%=basePath%>/static/css/sysbase.css?v=1.0.0" rel="stylesheet"
	type="text/css" />
<link href="<%=basePath%>/static/css/sysrespond.css?v=1.0.0" rel="stylesheet"
	type="text/css" />
</head>
<body data-base-path="<%=basePath%>" class="RightBody">
	<form id="form1">
		<!--OperateButton Begin-->
		<div class="OperateButtonArea">
			<div id="divOTitle" class="OTitle">部门管理</div>
			<div id="OperateButton" class="OOperateButton">
				${OperateButton}</div>
		</div>
		<!--OperateButton End-->
		<!--Content Begin-->
		<div class="ContentArea" id="ContentArea">
			<table class="tbdetail">
				<tr>

					<td style="text-align: right;"><span class="inputnull must">*</span>部门名称：
					</td>
					<td style="text-align: left;"><input type="text"
						class="inputnull input" id="txtDeptName" /></td>
					<td style="text-align: right; width: 20%;">部门编号：</td>
					<td style="text-align: left;"><input type="text"
						class="inputnull input" id="txtDeptCode" /></td>
				</tr>
				<tr>
					<td style="text-align: right;">备注：</td>
					<td style="text-align: left;" colspan="3"><textarea
							class="inputnull textarea" id="txtRemark" rows="4"></textarea></td>
				</tr>
			</table>
		</div>
		<!--Content End-->
	</form>
	<!--PageNodeId Begin-->
	<input type="hidden" id="txtListPageNodeId" value="${ListPageNodeId}" />
	<input type="hidden" id="txtAddPageNodeId" value="${AddPageNodeId}" />
	<input type="hidden" id="txtEditPageNodeId" value="${EditPageNodeId}" />
	<input type="hidden" id="txtDetailPageNodeId"
		value="${DetailPageNodeId}" />
	<!--PageNodeId End-->
</body>
</html>

<script src="/static/js/jquery/jquery/1.11.3/jquery.js"
	type="text/javascript"></script>
<script src="/static/js/layer/layer/3.1.1/layer.js"
	type="text/javascript"></script>
<script src="/static/js/missy/missy/2.1.0/missy.js"
	type="text/javascript"></script>

<script type="text/javascript">
	var g_ListPageNodeId = $("#txtListPageNodeId").val();
	var g_NodeId = MISSY.getQueryString("nodeId");
	$(function() {
		initPage();
	});

	function initPage() {
		$("#BtnSubmitOperate").click(function() {
			ClickSubmit();
		});
		$("#BtnBackOperate").click(function() {
			ClickBack();
		});
		var id = MISSY.getQueryString("id");
		if (MISSY.isEmpty(id))
			return;
		alert(id)
		$.ajax({
			url : "initSingle",
			data : {
				nodeId : g_NodeId,
				id : id
			},
			type : "POST",
			dataType : "json",
			ContentType : "application/json;charset=utf-8",
			success : function(response) {
				if (!response) {
					MISSY.iErrorReturnNull();
					return;
				}
				switch (response.errorCode) //标记
				{
				case 0: //错误
					MISSY.iErrorMessage(response.errorMessage);
					return;
				case 1: //返回正确数据
					break;
				case 2: //请求地址不正确
					MISSY.iNoFound(response.errorMessage);
					return;
				case 3: //未登录
					MISSY.iNoLogin(response.errorMessage);
					return;
				case 4: //无页面权限
					MISSY.iNoPageAuth(response.errorMessage);
					return;
				case 5: //无操作权限
					MISSY.iNoOperateAuth(response.errorMessage);
					return;
				default:
					MISSY.iErrorMessage(response.errorMessage);
					return;
				}
				var model = response.data;
				$("#txtDeptCode").val(model.deptCode);
				$("#txtDeptName").val(model.deptName);
				$("#txtRemark").val(model.remark);
			},
			error : function(xmlHttpRequest, textStatus, errorThrown) {
				MISSY.iDebugAjaxError(xmlHttpRequest, textStatus, errorThrown);
				MISSY.iSystemAjaxError();
			}
		});
	}

	function ClickBack() //Back
	{
		location.href = "list?NodeId=" + g_ListPageNodeId + "&_r="
				+ Math.random();
	}

	function ClickSubmit() //Add、Update
	{
		if (!CheckForm())
			return;
		var id = MISSY.getQueryString("id");
		var layerLoadIndex;
		$.ajax({
			url : "addOrUpdate",
			data : {
				nodeId : g_NodeId,
				id : id,
				deptCode : $("#txtDeptCode").val(),
				deptName : $("#txtDeptName").val(),
				remark : $("#txtRemark").val()
			},
			type : "POST",
			dataType : "json",
			ContentType : "application/json;charset=utf-8",
			beforeSend : function() {
				layerLoadIndex = MISSY.iShowLoading("正在执行中，请稍候...");
			},
			success : function(response) {
				if (!response) {
					MISSY.iErrorReturnNull();
					return;
				}
				switch (response.errorCode) //标记
				{
				case 0: //错误
					MISSY.iErrorMessage(response.errorMessage);
					return;
				case 1: //返回正确数据
					MISSY.iSuccessMessage(response.errorMessage, ClickBack);
					break;
				case 2: //请求地址不正确
					MISSY.iNoFound(response.errorMessage);
					return;
				case 3: //未登录
					MISSY.iNoLogin(response.errorMessage);
					return;
				case 4: //无页面权限
					MISSY.iNoPageAuth(response.errorMessage);
					return;
				case 5: //无操作权限
					MISSY.iNoOperateAuth(response.errorMessage);
					return;
				default:
					MISSY.iErrorMessage(response.errorMessage);
					return;
				}
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

	function CheckForm() //CheckForm
	{
		return true;
	}
</script>

