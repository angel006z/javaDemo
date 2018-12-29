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
		<div id="divOTitle" class="OTitle">部门管理</div>
		<div id="OperateButton" class="OOperateButton">${OperateButton }
		</div>

	</div>
	<!--OperateButton End-->
	<!--Content Begin-->
	<div class="ContentArea" id="ContentArea">
		<div class="ConditionArea">
			<table class="tbCondition">
				<tr>
					<td style="text-align: right;">部门名称：</td>
					<td style="text-align: left;"><input type="text" class="input"
						id="txtDeptName" /></td>
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
					<th>部门名称</th>
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
	<script src="<%=basePath%>/static/js/missy/missy/2.2.0/missy.js"
		type="text/javascript"></script>
	<script src="<%=basePath%>/static/script/backend/basic/dept/list.js"
		type="text/javascript"></script>
	<!-- Script End -->

</body>
</html>
