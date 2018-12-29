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
	
	initSingle();	
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
			$("#txtDeptCode").val(responseItem.deptCode);
			$("#txtDeptName").val(responseItem.deptName);
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