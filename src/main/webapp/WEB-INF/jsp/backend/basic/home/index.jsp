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
	<link rel="stylesheet" href="<%=basePath%>/static/css/reset.css?v=1.0.0">
    <link rel="stylesheet" href="<%=basePath%>/static/css/index.css?v=1.0.1">
    <style>
        .second-menu {
            margin-left: 60px;
            line-height: 30px;
        }

        .tbdetail {
            width: 100%;
            border-collapse: collapse;
            border-spacing: 0;
            margin: 10px auto;
        }

            .tbdetail tr td {
                padding: 5px;
            }

        .text-input {
            display: inline-block;
            vertical-align: top;
            width: 100%;
            height: 32px;
            line-height: 32px;
            font-size: 14px;
            padding-left: 10px;
            border: 1px solid #eee;
            position: relative;
        }
    </style>
<body>
	<div class="head">
        <div class="logo-box">
        </div>
        <a href="#" style="color: #fff;"><h1 class="title">管理信息系统</h1></a>
        <div class="login-box">
            <a class="user-operation">欢迎您，@Utits.CurrentRealName</a>
            <a id="aMessageNum" href="../Right/Index" target="mainFrame" class="user-operation message"><span class="message-num" id="span_msg_num">0</span></a>
            <a href="javascript:void(0)" id="BtnShowPassword" class="user-operation modify-password">修改密码</a>
            <a href="javascript:void(0)" id="BtnQuit" class="user-operation quit">退出系统</a>
        </div>
    </div>
    <div class="content">
        <div class="left-menu" style="overflow-y: auto;">
            <ul class="menus" id="LeftMenu"></ul>
        </div>
        <div class="iframe-box">
            <iframe src="" name="mainFrame" id="mainFrame" frameborder="0"></iframe>
        </div>
    </div>

    <div id="div_ModifyPassword" style="display: none; width: 380px;height:160px;overflow: hidden;">
        <table class="tbdetail">
            <tr>
                <td style="text-align:right;">旧密码：</td>
                <td>
                    <input type="password" id="OldPassword" class="text-input" />
                </td>
            </tr>
            <tr>
                <td style="text-align:right;">新密码：</td>
                <td>
                    <input type="password" id="Password" class="text-input" />
                </td>
            </tr>
            <tr>
                <td style="text-align:right;">确认新密码：</td>
                <td>
                    <input type="password" class="text-input" id="ConfirmPassword" />
                </td>
            </tr>
        </table>
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
        	var redirectUrl = MISSY.getQueryString("redirectUrl");
            if (!MISSY.isEmpty(redirectUrl)) {
                $("#mainFrame").attr("src", redirectUrl);
            }

            $("#BtnQuit").click(function () { clickQuit(); });
            $("#BtnShowPassword").click(function () {
                layer.open({
                    type: 1,
                    shadeClose: true,
                    area: ['auto', 'auto'],
                    content: $("#div_ModifyPassword"),
                    title: "修改密码",
                    btn: ['确定', '取消'],
                    btnAlign: 'c',
                    yes: function (index) {
                        clickModifyPassword();
                    }
                });
            });

            achieveAuthMenuTree();

            $('.menu-link').on('click', function () {                
                $(this).toggleClass('active');
                $(this).parent().children('ul').toggleClass('show');
            });

          
        }
       

        function achieveAuthMenuTree() //权限菜单
        {
            $.ajax({
                url: 'achieveLeftAuthNode',
                type: 'POST',
                dataType: "json",
                async: false,
                beforeSend: function () { $("#LeftMenu").html("<div style='width:120px;margin: 0 auto;'>正在加载...</div>"); },
                ContentType: "application/json;charset=utf-8",
                success: function (_list) {
                    var buf = [];
                    for (var i = 0; i < _list.length; i++) {
                        var modelParent = _list[i];
                        if (modelParent.Pid == 0) {
                            var _childList = new Array();
                            var _tempchildi = 0;
                            for (var k = 0; k < _list.length; k++) {
                                if (_list[k].Pid == 0) continue;
                                if (_list[k].Pid == modelParent.Id) {
                                    _childList[_tempchildi] = _list[k];
                                    _tempchildi++;
                                }
                            }

                            buf.push("<li class=\"menu\">");
                            if (!MISSY.isEmpty(modelParent.Url)) {
                                buf.push("<a  class=\"menu-link " + modelParent.NodeClassName + "\" href=\"");
                                buf.push(modelParent.Url ? modelParent.Url + (modelParent.Url.indexOf("?") > 0 ? "&" : "?") + "NodeId=" + modelParent.Id : "javascript:void(0);");
                                buf.push("\" target=\"" + modelParent.Target + "\">" + modelParent.Name + "</a>");
                            } else
                                buf.push("<a class=\"menu-link " + modelParent.NodeClassName + "\">" + modelParent.Name + "</a>");
                            if (_childList.length > 0) {
                                buf.push("<ul class=\"second-menus\">");
                                for (var j = 0; j < _childList.length; j++) {
                                    buf.push("<li class=\"second-menu\">");
                                    buf.push("<a class=\"second-menu-link\" href=\"");
                                    buf.push(_childList[j].Url ? _childList[j].Url + (_childList[j].Url.indexOf("?") > 0 ? "&" : "?") + "NodeId=" + _childList[j].Id : "javascript:void(0);");
                                    buf.push("\" target=\"" + _childList[j].Target + "\">" + _childList[j].Name + "</a>");
                                    buf.push("</li>");
                                }
                                buf.push("</ul>");
                            }
                            buf.push("</li>");
                        }
                    }
                    $("#LeftMenu").html("<ul>" + buf.join("") + "</ul>");
                },
                error: function (xmlHttpRequest, textStatus, errorThrown) {
                    $("#LeftMenu").html("数据加载失败,请稍后再试.");
                }
            });
        }

        

        function clickModifyPassword() //操作修改密码
        {
            var objOldPassword = document.getElementById("OldPassword");
            var objPassword = document.getElementById("Password");
            var objConfirmPassword = document.getElementById("ConfirmPassword");
            if (!MISSY.isLimitLen(objOldPassword.value, 3, 32)) {
                MISSY.iErrorMessage("旧密码长度为3-32个字符，请重新输入");
                objOldPassword.focus();
                return;
            }
            if (!MISSY.isLimitLen(objPassword.value, 3, 32)) {
                MISSY.iErrorMessage("新密码长度为3-32个字符，请重新输入");
                objPassword.focus();
                return;
            }
            if (!MISSY.isLimitLen(objConfirmPassword.value, 3, 32)) {
                MISSY.iErrorMessage("确认密码长度为3-32个字符，请重新输入");
                objConfirmPassword.focus();
                return;
            }
            if (MISSY.trim(objPassword.value) !== MISSY.trim(objConfirmPassword.value)) {
                MISSY.iErrorMessage("新密码与确认密码不一致");
                objConfirmPassword.focus();
                return;
            }
            var layerLoadIndex;
            $.ajax({
                url: "modifyPassword",
                data: {
                    OldPassword: $.md5(objOldPassword.value),
                    NewPassword: $.md5(objPassword.value)
                },
                type: "POST",
                dataType: "json",
                beforeSend: function () {
                    layerLoadIndex = MISSY.iShowLoading("正在执行中，请稍候...");
                },
                success: function (response) {
                	if(response.code!="1"){
        				MISSY.iWrongMessage(response.code,response.message);
        				return;
        			}
        			MISSY.iSuccessMessage(response.message,closeModifyPassword);		
                },
                error: function (xmlHttpRequest, textStatus, errorThrown) {
                    MISSY.iDebugAjaxError(xmlHttpRequest, textStatus, errorThrown);
                    MISSY.iSystemAjaxError();
                },
                complete: function (xmlHttpRequest, textStatus) {
                    MISSY.iHideLoading(layerLoadIndex);
                }
            });
        }
        function closeModifyPassword() {
            MISSY.iHideDialog();
        }
        function clickQuit() {
            MISSY.iConfirmMessage('是否安全退出？', function () {
                var layerLoadIndex;
                $.ajax({
                    type: "POST",
                    url: "quitLogin",
                    async: false,
                    beforeSend: function () { layerLoadIndex = MISSY.iShowLoading("正在执行中，请稍候..."); },
                    success: function () {
                        location.href = "../login/index";
                    },
                    error: function (xmlHttpRequest, textStatus, errorThrown) {
                        MISSY.iDebugAjaxError(xmlHttpRequest, textStatus, errorThrown);
                        MISSY.iSystemAjaxError();
                    },
                    complete: function (xmlHttpRequest, textStatus) {
                        MISSY.iHideLoading(layerLoadIndex);
                    }
                });
            });
        }

</script>

