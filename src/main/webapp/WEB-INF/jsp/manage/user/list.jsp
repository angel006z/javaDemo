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


</head>
<body class="RightBody">

	<!--OperateButton Begin-->
	<div class="OperateButtonArea">
		<div id="divOTitle" class="OTitle">用户管理</div>
		<div id="OperateButton" class="OOperateButton">${OperateButton }
		</div>

	</div>
	<!--OperateButton End-->
	<!--Content Begin-->
	<div class="ContentArea" id="ContentArea">
		<div class="ConditionArea">
			<table class="tbCondition">
				<tr>
					<td style="text-align: right;">用户名称：</td>
					<td style="text-align: left;"><input type="text" class="input"
						id="txtUserName" /></td>
					<td style="text-align: center;"><input type="button"
						id="BtnSearch" class="button" value="查询" /></td>
				</tr>
			</table>
		</div>
		<div class="tableArea">
			<table id="tblist" class="tblist">
				<tr class="odd">
					<th class="thcbAll"><input type="checkbox" align="absmiddle"
						id="cbListCheckAll" /></th>
					<th style="width: 40px;">序号</th>
					<th>用户名称</th>
					<th>部门名称</th>
					<th>角色名称</th>
					<th>排序</th>
					<th>备注</th>
					<th style="width: 140px;">操作时间</th>
					<th style="width: 60px;">操作</th>
				</tr>
			</table>
			<div id="divPagination"></div>
		</div>
	</div>
	<!--Content End-->

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
		<script src="<%=basePath%>/static/js/layer/laypage/1.3.0/laypage.js"
		type="text/javascript"></script>
	<script src="<%=basePath%>/static/js/missy/missy/2.2.0/missy.js"
		type="text/javascript"></script>
	<!-- Script End -->

</body>
</html>
<script type="text/javascript">
var g_BasePath = $("#txtPageParam").attr("data-BasePath");
var g_AddPageNodeId = $("#txtPageParam").attr("data-AddPageNodeId");
var g_EditPageNodeId = $("#txtPageParam").attr("data-EditPageNodeId");
var g_DetailPageNodeId = $("#txtPageParam").attr("data-DetailPageNodeId");
var g_NodeId = MISSY.getQueryString("nodeId");
var g_PageSize = 20;
var g_CurrentPage = 1;
var g_PageCount = 5;
$(function() {
	initPage();
});

function initPage() {
	$("#BtnAddOperate").click(function() {
		clickAdd();
	});
	$("#BtnUpdateOperate").click(function() {
		clickUpdate();
	});
	$("#BtnDeleteOperate").click(function() {
		clickDelete();
	});
	$("#BtnPhyDeleteOperate").click(function() {
		clickPhyDelete();
	});
	$("#BtnEnableOperate").click(function() {
		clickEnable();
	});
	$("#BtnDisableOperate").click(function() {
		clickDisable();
	});
	$("#BtnSearch").click(function() {
		clickSearch();
	});
	$("#cbListCheckAll").click(function() {
		MISSY.setClickCheckAll(this, "nameCbox");
	});
	$("body").keydown(function(event) {
		var e = document.all ? window.event : event;
		var keycode = (e.keyCode ? e.keyCode : e.which);
		if (keycode === 13) {
			clickSearch();
		}
	});
    
	changePage(1, g_PageSize);
}

function changePage(paramCurrentPage, paramPageSize) {
	var colNumber = $("#tblist tr th").length;
	$.ajax({
		type : "post",
		url : "searchList",
		data : {
			userName : $("#txtUserName").val(),
			nodeId : g_NodeId,
			currentPage : paramCurrentPage,
			pageSize : paramPageSize
		},
		dataType : "json",
		beforeSend : function() {
			$("#tblist tr:gt(0)").remove();
			$("#tblist").append("<tr><td align=\"center\" colspan=\"" + colNumber + "\">加载中，请稍等．．．</td></tr>");			
		},
		success : function(response) {
			$("#tblist tr:gt(0)").remove();
			$("#tblist").append("<tr><td align=\"center\" colspan=\"" + colNumber + "\">请刷新后再试.</td></tr>");

			if(response.code!="1"){
				MISSY.iWrongMessage(response.code,response.message);
				return;
			}

			var buf = new Array();
			var responseList = response.data == null ? new Array() : response.data;
			for (var i = 0; i < responseList.length; i++) {
				var model = responseList[i];
				buf.push("<tr>");
				buf.push("<td><input type=\"checkbox\" name=\"nameCbox\" value=\"" + model.userId + "\" /></td>");
				buf.push("<td>" + (i + 1) + "</td>");
				buf.push("<td>" + model.userName + "</td>");
				buf.push("<td>" + model.deptName + "</td>");
				buf.push("<td>" + model.roleName + "</td>");
				buf.push("<td>" + model.userSort + "</td>");
				buf.push("<td>" + model.remark + "</td>");
				buf.push("<td>" + MISSY.formatDate(model.operateDate, 4) + "</td>");
				buf.push("<td><a href=\"detail?nodeId=" + g_DetailPageNodeId + "&id=" + model.userId + "\">查看</a></td>");
				buf.push("</tr>");
			}
			if (buf.length <= 0)
				buf.push("<tr><td align=\"center\" colspan=\"" + colNumber + "\">暂无数据．</td></tr>");
			$("#tblist tr:gt(0)").remove();
			$("#tblist").append(buf.join(""));		
			
			g_CurrentPage = response.pagination.CurrentPage;
			g_PageCount = response.pagination.PageCount;
			if (g_PageCount > 1) {
	              MISSY.setCommonPagination(g_PageCount, g_CurrentPage, response.pagination.PageSize, 'divPagination', changePage);
	        }		 
		},
		error : function(xmlHttpRequest, textStatus, errorThrown) {
			MISSY.iDebugAjaxError(xmlHttpRequest, textStatus, errorThrown);
			$("#tblist tr:gt(0)").remove();
			$("#tblist").append("<tr><td align=\"center\" colspan=\"" + colNumber + "\">系统性错误，请稍后再试.或点击<a href=\"javascript:;\" class=\"errorRefresh\">刷新</a></td></tr>");
			$("#tblist .errorRefresh").click(function(){clickRefresh();});
		}
	});
}

function clickRefresh() {
	changePage(g_CurrentPage, g_PageSize);
}

function clickSearch() {
	changePage(1, g_PageSize);
}

function clickAdd() {
	location.href = "detail?nodeId=" + g_AddPageNodeId;
}

function clickUpdate() {
	var id = "";
	var checkboxlist = document.getElementsByName("nameCbox");
	var checkboxLength = 0;
	for (var j = 0; j < checkboxlist.length; j++) {
		if (checkboxlist[j].type == "checkbox") {
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
		if (checkboxlist[i].type == "checkbox") {
			if (checkboxlist[i].checked) {
				id = checkboxlist[i].value;
				break;
			}
		}
	}
	
	if (!MISSY.isEmpty(id))
		location.href = "detail?nodeId=" + g_EditPageNodeId + "&id=" + id;
	else
		MISSY.iErrorMessage("请选择一项数据.");
}

function confirmBatchOperate(paramButtonId, paramIds) {
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
			if(response.code!="1"){
				MISSY.iWrongMessage(response.code,response.message);
				return;
			}
			MISSY.iSuccessMessage(response.message, clickRefresh);			
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

function clickDisable() {
	var paramIds = MISSY.getCheckboxCheckedVal("nameCbox", ",");
	if (!MISSY.isEmpty(paramIds)) {
		MISSY.iConfirmMessage("您确定要禁用吗？", function() {
			confirmBatchOperate(22, paramIds);
		});
	} else {
		MISSY.iErrorMessage("请选择一项数据.");
	}
}

function clickEnable() {
	var paramIds = MISSY.getCheckboxCheckedVal("nameCbox", ",");
	if (!MISSY.isEmpty(paramIds)) {
		MISSY.iConfirmMessage("您确定要启用吗？", function() {
			confirmBatchOperate(21, paramIds);
		});
	} else {
		MISSY.iErrorMessage("请选择一项数据.");
	}
}

function clickDelete() {
	var paramIds = MISSY.getCheckboxCheckedVal("nameCbox", ",");
	if (!MISSY.isEmpty(paramIds)) {
		MISSY.iConfirmMessage("删除后不可恢复，您确定要删除吗？", function() {
			confirmBatchOperate(3, paramIds);
		});
	} else {
		MISSY.iErrorMessage("请选择一项数据.");
	}
}

function clickPhyDelete() {
	if (!MISSY.isEmpty(paramIds)) {
		MISSY.iConfirmMessage("请谨慎操作，删除后数据不可恢复，您确定要删除吗？", function() {
			confirmBatchOperate(4, paramIds);
		});
	} else {
		MISSY.iErrorMessage("请选择一项数据.");
	}
}
</script>
