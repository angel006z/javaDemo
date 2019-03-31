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
	<form id="form1">
		<!--OperateButton Begin-->
		<div class="OperateButtonArea">
			<div id="divOTitle" class="OTitle">用户管理</div>
			<div id="OperateButton" class="OOperateButton">
				${OperateButton}</div>
		</div>
		<!--OperateButton End-->
		<!--Content Begin-->
		<div class="ContentArea" id="ContentArea">
			<table class="tbdetail">
				<tr>
					<td style="text-align: right;"><span class="inputnull must">*</span>用户名称：
					</td>
					<td style="text-align: left;"><input type="text"
						class="inputnull input" id="txtUserName" /></td>
					<td style="text-align: right; width: 20%;">用户编号：</td>
					<td style="text-align: left;"><input type="text"
						class="inputnull input" id="txtUserCode" /></td>
				</tr>
				<tr>
					<td style="text-align: right;">部门：</td>
					<td style="text-align: left;"><select id="ddlDeptId"
						class="select">
					</select></td>
					<td style="text-align: right;">角色：</td>
					<td style="text-align: left;"><select id="ddlRoleId"
						class="select">
					</select></td>
				</tr>
				<tr>
					<td style="text-align: right;">密码：</td>
					<td style="text-align: left;"><input type="password"
						class="inputnull input" id="txtPassword" /></td>
					<td style="text-align: right; width: 20%;">排序：</td>
					<td style="text-align: left;"><input type="text"
						class="inputnull input" id="txtUserSort" /></td>
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

	<!-- PageParam Begin -->
	<input type="hidden" id="txtPageParam"
		data-ListPageNodeId="${ListPageNodeId}"
		data-AddPageNodeId="${AddPageNodeId}"
		data-EditPageNodeId="${EditPageNodeId}"
		data-DetailPageNodeId="${DetailPageNodeId}"
		data-BasePath="<%=basePath%>" />
	<!-- PageParam End -->

	<!-- Script Begin -->
	<script src="<%=basePath%>/static/js/jquery/jquery/1.11.3/jquery.js"
		type="text/javascript"></script>
	<script src="<%=basePath%>/static/js/layer/layer/3.1.1/layer.js"
		type="text/javascript"></script>
	<script src="<%=basePath%>/static/js/missy/missy/2.2.0/missy.js"
		type="text/javascript"></script>
		<script src="<%=basePath%>/static/js/jquery/md5/1.2.1/jquery.md5.js"
	type="text/javascript"></script>
	<!-- Script End -->
</body>
</html>
<script type="text/javascript">
var g_BasePath = $("#txtPageParam").attr("data-BasePath");
var g_ListPageNodeId = $("#txtPageParam").attr("data-ListPageNodeId");
var g_NodeId = MISSY.getQueryString("nodeId");
var g_Id = MISSY.getQueryString("id");
$(function() {
	initPage();
});

function initPage() {
	$("#BtnSubmitOperate").click(function() {
		clickSubmit();
	});

	$("#BtnBackOperate").click(function() {
		clickBack();
	});
	
	initDataDept();
	initDataRole();
	
	initSingle();	
}
function initDataDept() {
	$.ajax({
		url : "initDataDept",
		data : {nodeId : g_NodeId},
		type : "POST",
		dataType : "json",
		async:false,
		ContentType : "application/json;charset=utf-8",
		success : function(response) {
			var buf =new Array();
			var responseList = response.data == null ? new Array() : response.data;
			for (var i = 0; i < responseList.length; i++) {
				var model = responseList[i];
				buf.push("<option value=\""+model.deptId+"\">"+model.deptName+"</option>");
		    }			
			$("#ddlDeptId").html(buf.join(""));			
		}
	});
}
function initDataRole() {
	$.ajax({
		url : "initDataRole",
		data : {nodeId : g_NodeId},
		type : "POST",
		dataType : "json",
		async:false,
		ContentType : "application/json;charset=utf-8",
		success : function(response) {
			var buf =new Array();
			var responseList = response.data == null ? new Array() : response.data;
			for (var i = 0; i < responseList.length; i++) {
				var model = responseList[i];
				buf.push("<option value=\""+model.roleId+"\">"+model.roleName+"</option>");
		    }			
			$("#ddlRoleId").html(buf.join(""));			
		}
	});
}

function initSingle() {
	if (MISSY.isEmpty(g_Id))
		return;
	$.ajax({
		url : "initSingle",
		data : {
			nodeId : g_NodeId,
			id : g_Id
		},
		type : "POST",
		dataType : "json",
		ContentType : "application/json;charset=utf-8",
		success : function(response) {
			if(response.errorCode!="1"){
				MISSY.iWrongMessage(response.errorCode,response.errorMessage);
				return;
			}
			var responseItem = response.data;
			$("#txtUserCode").val(responseItem.userCode);
			$("#txtUserName").val(responseItem.userName);
			$("#ddlDeptId").val(responseItem.deptId);
			$("#ddlRoleId").val(responseItem.roleId);
			$("#txtUserSort").val(responseItem.userSort);
			$("#txtRemark").val(responseItem.remark);
		},
		error : function(xmlHttpRequest, textStatus, errorThrown) {
			MISSY.iDebugAjaxError(xmlHttpRequest, textStatus, errorThrown);
			MISSY.iSystemAjaxError();
		}
	});
}

function clickBack()
{
	location.href = "list?NodeId=" + g_ListPageNodeId;
}

function clickSubmit()
{
	if (!checkForm())
		return;
	
	var layerLoadIndex;
	$.ajax({
		url : "submitOperate",
		data : {
			nodeId : g_NodeId,
			id : g_Id,
			userCode : $("#txtUserCode").val(),
			userName : $("#txtUserName").val(),
			deptId:$("#ddlDeptId").val(),
			roleId:$("#ddlRoleId").val(),
			password:$("#txtPassword").val(),
			userSort:$("#txtUserSort").val(),
			remark : $("#txtRemark").val()
		},
		type : "POST",
		dataType : "json",
		ContentType : "application/json;charset=utf-8",
		beforeSend : function() {
			layerLoadIndex = MISSY.iShowLoading("正在执行中，请稍候.");
		},
		success : function(response) {
			if(response.errorCode!="1"){
				MISSY.iWrongMessage(response.errorCode,response.errorMessage);
				return;
			}
			MISSY.iSuccessMessage(response.errorMessage,clickBack);			
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

function checkForm()
{
	return true;
}
</script>

