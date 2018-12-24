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

<script src="<%=basePath%>/static/js/jquery/jquery/1.11.3/jquery.js"
	type="text/javascript"></script>
<script src="<%=basePath%>/static/js/layer/layer/3.1.1/layer.js"
	type="text/javascript"></script>
<script src="<%=basePath%>/static/js/missy/missy/2.1.0/missy.js"
	type="text/javascript"></script>
</head>
<body class="RightBody">
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

	<!-- PageParam Begin -->
	<input type="hidden" id="txtPageParam"
		data-ListPageNodeId="${ListPageNodeId}"
		data-AddPageNodeId="${AddPageNodeId}"
		data-EditPageNodeId="${EditPageNodeId}"
		data-DetailPageNodeId="${DetailPageNodeId}"
		data-BasePath="<%=basePath%>" />
	<!-- PageParam End -->
</body>
</html>


