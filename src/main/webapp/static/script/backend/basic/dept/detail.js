var g_BasePath = $("#txtPageParam").attr("data-BasePath");
var g_ListPageNodeId = $("#txtPageParam").attr("data-ListPageNodeId");
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
			switch (response.errorCode) 
			{
			case 0: // 错误
				MISSY.iErrorMessage(response.errorMessage);
				return;
			case 1: // 返回正确数据
				break;
			case 2: // 请求地址不正确
				MISSY.iNoFound(response.errorMessage);
				return;
			case 3: // 未登录
				MISSY.iNoLogin(response.errorMessage);
				return;
			case 4: // 无页面权限
				MISSY.iNoPageAuth(response.errorMessage);
				return;
			case 5: // 无操作权限
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

function ClickBack() // Back
{
	location.href = "list?NodeId=" + g_ListPageNodeId;
}

function ClickSubmit() // Add、Update
{
	if (!CheckForm())
		return;
	var id = MISSY.getQueryString("id");
	var layerLoadIndex;
	$.ajax({
		url : "submitOperate",
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
			layerLoadIndex = MISSY.iShowLoading("正在执行中，请稍候.");
		},
		success : function(response) {
			if (!response) {
				MISSY.iErrorReturnNull();
				return;
			}
			switch (response.errorCode) // 标记
			{
			case 0: // 错误
				MISSY.iErrorMessage(response.errorMessage);
				return;
			case 1: // 返回正确数据
				MISSY.iSuccessMessage(response.errorMessage, ClickBack);
				break;
			case 2: // 请求地址不正确
				MISSY.iNoFound(response.errorMessage);
				return;
			case 3: // 未登录
				MISSY.iNoLogin(response.errorMessage);
				return;
			case 4: // 无页面权限
				MISSY.iNoPageAuth(response.errorMessage);
				return;
			case 5: // 无操作权限
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

function CheckForm() // CheckForm
{
	return true;
}