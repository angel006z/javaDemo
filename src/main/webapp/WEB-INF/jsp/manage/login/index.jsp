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

<link href="<%=basePath%>/static/css/reset.css?v=1.0.0"
	rel="stylesheet" type="text/css" />
<link href="<%=basePath%>/static/css/login.css?v=1.0.0"
	rel="stylesheet" type="text/css" />
	
<body>
<script type="text/javascript">
    if (window.top != window.self) {
        top.location.href = self.location;
    }
</script>

<div class="login-wrapper">
    <div class="login-box">
        <h2 class="login-title">管理信息系统</h2>
        <div class="left-img"></div>
        <div class="input-box">
            <!--<h3 class="input-title">用户名</h3>-->
            <input type="text" id="txtUserName" class="login-input" placeholder="请输入用户名">
            <!--<h3 class="input-title">密码</h3>-->
            <input type="password" id="txtPassword" class="login-input" placeholder="请输入密码">
            <a href="javascript:;" class="login-btn" id="btnLogin">登 录</a>
        </div>

    </div>
</div>
	
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
$(function () {
    initPage();
});

function initPage() {
    $("#btnLogin").click(function () { login(); });
    $("#txtUserName").focus();
    $("body").keydown(function (event) {
        var e = document.all ? window.event : event;
        var keycode = (e.keyCode ? e.keyCode : e.which);
        if (keycode === 13) {
            login();
        }
    });
}

function login() {
    var userName = $("#txtUserName").val();
    if (MISSY.isEmpty(userName)) {
        MISSY.iErrorMessage("用户名不能为空！");
        $("#txtUserName").focus();
        return false;
    }
    var password = $("#txtPassword").val();
    if (MISSY.isEmpty(password)) {
        MISSY.iErrorMessage("密码不能为空！");
        $("#txtPassword").focus();
        return false;
    }


    userName = MISSY.trim(userName);
    password = MISSY.trim(password);
    var layerLoadIndex;
    $.ajax({
        type: "POST",
        url: "loginSystem",
        data: {
        	userName: userName,
            password: $.md5(password)
        },
        dataType: "json",
        beforeSend: function () {
            layerLoadIndex = MISSY.iShowLoading("正在执行中，请稍候...");
            $("#btnLogin").attr("disabled", "disabled");
        },
        success: function (response) {
            if (!response) { MISSY.iErrorReturnNull(); return; }
            if (response.code == "1") {
                var callback = MISSY.getQueryString("callback");
                if (MISSY.isEmpty(callback)) {
                    location.href = "<%=basePath%>/backend/basic/home/index";
                } else {
                    location.href = callback;
                }
            } else {
                MISSY.iErrorMessage(response.message);
            }
        },
        error: function (xmlHttpRequest, textStatus, errorThrown) {
            MISSY.iDebugAjaxError(xmlHttpRequest, textStatus, errorThrown);
            MISSY.iErrorMessage("系统忙,请稍后再试.");
        },
        complete: function (xmlHttpRequest, textStatus) {
            $("#btnLogin").removeAttr("disabled");
            MISSY.iHideLoading(layerLoadIndex);
        }
    });
    return true;
}


function resert() {
    $("#txtUsername").attr("value", "");
    $("#txtPassword").attr("value", "");
}
</script>

