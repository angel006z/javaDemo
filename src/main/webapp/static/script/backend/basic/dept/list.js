var g_BasePath = $("#txtPageParam").attr("data-BasePath");
var g_AddPageNodeId = $("#txtPageParam").$("#data-AddPageNodeId");
var g_EditPageNodeId = $("#txtPageParam").$("#data-EditPageNodeId");
var g_DetailPageNodeId = $("#txtPageParam").$("#data-DetailPageNodeId");
var g_NodeId = MISSY.getQueryString("NodeId");
var g_PageSize = 20; // PAGE NUMBER
var g_CurrentPage = 1; // CurrentPage
var g_PageCount = 5;
$(function() {
    initPage();
});

function initPage() // initPage
{
    $("#BtnAddOperate").click(function() {
	ClickAdd();
    });
    $("#BtnUpdateOperate").click(function() {
	ClickUpdate();
    });
    $("#BtnDeleteOperate").click(function() {
	ClickDelete();
    });
    $("#BtnPhyDeleteOperate").click(function() {
	ClickPhyDelete();
    });
    $("#BtnEnableOperate").click(function() {
	ClickEnable();
    });
    $("#BtnDisableOperate").click(function() {
	ClickDisable();
    });
    $("#BtnSearch").click(function() {
	ClickSearch();
    });
    $("#cbListCheckAll").click(function() {
	MISSY.setClickCheckAll(this, "nameCbox");
    });
    $("body").keydown(function(event) {
	var e = document.all ? window.event : event;
	var keycode = (e.keyCode ? e.keyCode : e.which);
	if (keycode === 13) {
	    ClickSearch();
	}
    });
    ChangePage(1, g_PageSize);
}

function ChangePage(paramCurrentPage, paramPageSize) // ChangePage
{
    var colNumber = $("#tblist tr th").length;
    $
	    .ajax({
		type : "post",
		url : "searchList",
		data : {
		    deptName : $("#txtDeptName").val(),
		    nodeId : g_NodeId,
		    currentPage : paramCurrentPage,
		    pageSize : paramPageSize
		},
		dataType : "json",
		beforeSend : function() {
		    $("#tblist tr:gt(0)").remove();
		    $("#tblist").append(
			    "<tr><td align=\"center\" colspan=\"" + colNumber
				    + "\">加载中，请稍等．．．</td></tr>");
		},
		success : function(response) {
		    $("#tblist tr:gt(0)").remove();
		    $("#tblist").append(
			    "<tr><td align=\"center\" colspan=\"" + colNumber
				    + "\">请刷新后再试.</td></tr>");

		    if (!response) {
			MISSY.iErrorReturnNull();
			return;
		    }
		    switch (response.errorCode) {
		    case "0":// 错误
			MISSY.iErrorMessage(response.errorMessage);
			return;
		    case "1":// 返回正确数据
			break;
		    case "2":// 请求地址不正确
			MISSY.iNoFound(response.errorMessage);
			return;
		    case "3":// 未登录
			MISSY.iNoLogin(response.errorMessage);
			return;
		    case "4":// 无页面权限
			MISSY.iNoPageAuth(response.errorMessage);
			return;
		    case "5":// 无操作权限
			MISSY.iNoOperateAuth(response.errorMessage);
			return;
		    default:
			MISSY.iErrorMessage(response.errorMessage);
			return;
		    }

		    var buf = new Array();
		    var responseList = response.data == null ? new Array()
			    : response.data;
		    for (var i = 0; i < responseList.length; i++) {
			var model = responseList[i];
			buf.push("<tr class=\""
				+ (i % 2 === 0 ? "trbg2" : "trbg1") + "\">");
			buf
				.push("<td><input type=\"checkbox\" name=\"nameCbox\" value=\""
					+ model.deptId + "\" /></td>");
			buf.push("<td>" + (i + 1) + "</td>");
			buf.push("<td>" + model.deptName + "</td>");
			buf.push("<td>" + model.remark + "</td>");
			buf.push("<td>"
				+ MISSY.formatDate(model.operateDate, 4)
				+ "</td>");
			buf.push("<td><a href=\"detail?nodeId="
				+ g_DetailPageNodeId + "&id=" + model.deptId
				+ "\">查看</a></td>");
			buf.push("</tr>");
		    }
		    if (buf.length <= 0)
			buf.push("<tr><td align=\"center\" colspan=\""
				+ colNumber + "\">暂无数据．</td></tr>");
		    $("#tblist tr:gt(0)").remove();
		    $("#tblist").append(buf.join(""));

		    var pagination = response.pagination;
		    var responseTotalRecord = pagination.totalRecord;
		    var responsePageSize = pagination.PageSize;
		    var responseCurrentPage = pagination.CurrentPage;
		    g_CurrentPage = responseCurrentPage;
		    var responsePageCount = pagination.PageCount;
		    g_PageCount = responsePageCount;
		    if (responsePageCount > 1) {
			var bufPagination = new Array();
			bufPagination.push("<div class=\"pagination_main\">");
			bufPagination.push("<span>总数：" + responseTotalRecord
				+ "</span>");
			bufPagination.push("<span>第" + responseCurrentPage
				+ "/" + responsePageCount + "页</span>");
			if (responseCurrentPage <= 1
				|| responseCurrentPage - 1 > responsePageCount)
			    bufPagination
				    .push("<a class=\"pagination_prev\" style=\"color:gray;\">上一页</a>");
			else
			    bufPagination
				    .push("<a class=\"pagination_prev\" id=\"BtnPrevPage\" href=\"javascript:void(0);\">上一页</a>");

			if (responseCurrentPage >= responsePageCount)
			    bufPagination
				    .push("<a class=\"pagination_next\" style=\"color:gray;\">下一页</a>");
			else
			    bufPagination
				    .push("<a class=\"pagination_next\" id=\"BtnNextPage\" href=\"javascript:void(0);\">下一页</a>");

			bufPagination
				.push("<label>到第</label><input type=\"text\" class=\"pagination_input\" id=\"txtGoPage\" /><label>页</label>");
			bufPagination
				.push("<input type=\"button\" id=\"BtnConfirmPageGo\" class=\"pagination_button\" value=\"确定\" />");
			bufPagination.push("</div>");
			$("#divPagination").html(bufPagination.join(""));
			$("#BtnPrevPage").on(
				"click",
				function() {
				    ChangePage((responseCurrentPage - 1),
					    responsePageSize);
				});
			$("#BtnNextPage").on(
				"click",
				function() {
				    ChangePage((responseCurrentPage + 1),
					    responsePageSize);
				});
			$("#txtGoPage").on("keydown", function() {
			    ClickPageChange(13);
			});
			$("#BtnConfirmPageGo").on("click", function() {
			    ClickPageChange();
			});
		    } else {
			$("#divPagination").html("");
		    }
		},
		error : function(xmlHttpRequest, textStatus, errorThrown) {
		    MISSY.iDebugAjaxError(xmlHttpRequest, textStatus,
			    errorThrown);
		    $("#tblist tr:gt(0)").remove();
		    $("#tblist")
			    .append(
				    "<tr><td align=\"center\" colspan=\""
					    + colNumber
					    + "\">系统性错误，请稍后再试.或点击<a href=\"ClickRefresh()\">刷新</a></td></tr>");
		}
	    });
}

function ClickPageChange(codeType)// PageChange
{
    var isOkRun = false;
    if (codeType === 13) {
	if (event.keyCode === 13) {
	    isOkRun = true;
	}
    } else {
	isOkRun = true;
    }
    if (isOkRun === false)
	return;

    var objGoPage = document.getElementById("txtGoPage");
    var tempGoPage = objGoPage.value;
    if (tempGoPage != null) {
	if (!tempGoPage.match(/^[1-9]+\d*$/)) {
	    objGoPage.value = "";
	    return;
	}
    }
    ChangePage(tempGoPage, g_PageSize);
}

function ClickRefresh()// Refresh
{
    ChangePage(g_CurrentPage, g_PageSize);
}

function ClickSearch()// Search
{
    ChangePage(1, g_PageSize);
}

function ClickAdd() // Add
{
    location.href = "detail?NodeId=" + g_AddPageNodeId;
}

function ClickUpdate() // Update
{
    var id = "";
    var checkboxlist = document.getElementsByName("nameCbox");
    var checkboxLength = 0;
    for (var j = 0; j < checkboxlist.length; j++) {
	if (checkboxlist[j].type === "checkbox") {
	    if (checkboxlist[j].checked) {
		checkboxLength++;
	    }
	}
    }
    if (checkboxLength > 1) {
	MISSY.iErrorMessage("请只选择一项数据.");
	return;
    }
    for (var i = 0; i < checkboxlist.length; i++) {
	if (checkboxlist[i].type === "checkbox") {
	    if (checkboxlist[i].checked) {
		id = checkboxlist[i].value;
		break;
	    }
	}
    }
    if (id !== "")
	location.href = "detail?nodeId=" + g_EditPageNodeId + "&id=" + id;
    else
	MISSY.iErrorMessage("请选择一项数据.");
}

function ConfirmBatchOperate(paramButtonId, paramIds) {
    var layerLoadIndex;
    $.ajax({
	url : "batchOperate",
	data : {
	    nodeId : g_NodeId,
	    oButtonId : paramButtonId,
	    ids : paramIds
	},
	type : "post",
	dataType : "json",
	beforeSend : function() {
	    layerLoadIndex = MISSY.iShowLoading("正在执行中，请稍候");
	},
	ContentType : "application/json;charset=utf-8",
	success : function(response) {
	    if (!response) {
		MISSY.iErrorReturnNull();
		return;
	    }
	    switch (response.errorCode)// 标记
	    {
	    case 0:// 错误
		MISSY.iErrorMessage(response.errorMessage);
		return;
	    case 1:// 返回正确数据
		MISSY.iSuccessMessage(response.errorMessage, ClickRefresh);
		return;
	    case 2:// 请求地址不正确
		MISSY.iNoFound(response.errorMessage);
		return;
	    case 3:// 未登录
		MISSY.iNoLogin(response.errorMessage);
		return;
	    case 4:// 无页面权限
		MISSY.iNoPageAuth(response.errorMessage);
		return;
	    case 5:// 无操作权限
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

function ClickDisable()// Disable禁用
{
    var paramIds = MISSY.getCheckboxCheckedVal("nameCbox", ",");
    if (paramIds !== "") {
	MISSY.iConfirmMessage("您确定要禁用吗？", function() {
	    ConfirmBatchOperate(22, paramIds);
	});
    } else {
	MISSY.iErrorMessage("请选择一项数据.");
    }
}

function ClickEnable()// Enable启用
{
    var paramIds = MISSY.getCheckboxCheckedVal("nameCbox", ",");
    if (paramIds !== "") {
	MISSY.iConfirmMessage("您确定要启用吗？", function() {
	    ConfirmBatchOperate(21, paramIds);
	});
    } else {
	MISSY.iErrorMessage("请选择一项数据.");
    }
}

function ClickDelete() // Delete
{
    var paramIds = MISSY.getCheckboxCheckedVal("nameCbox", ",");
    if (paramIds !== "") {
	MISSY.iConfirmMessage("删除后不可恢复，您确定要删除吗？", function() {
	    ConfirmBatchOperate(3, paramIds);
	});
    } else {
	MISSY.iErrorMessage("请选择一项数据.");
    }
}

function ClickPhyDelete() // PhyDelete
{
    if (paramIds !== "") {
	MISSY.iConfirmMessage("请谨慎操作，删除后数据不可恢复，您确定要删除吗？", function() {
	    ConfirmBatchOperate(4, paramIds);
	});
    } else {
	MISSY.iErrorMessage("请选择一项数据.");
    }
}