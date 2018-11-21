<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="renderer" content="webkit" />
 	<title>详细页面</title>
    <link href="/static/css/normalize.css?v=7.0.0" rel="stylesheet" type="text/css" />
    <link href="/static/css/sysbase.css?v=1.0.0" rel="stylesheet" type="text/css" />
    <link href="/static/css/sysrespond.css?v=1.0.0" rel="stylesheet" type="text/css" />

</head>
<body class="RightBody">
    <form id="form1">
        <!--OperateButton Begin-->
        <div class="OperateButtonArea">
            <div id="divOTitle" class="OTitle">用户管理</div>
            <div id="OperateButton" class="OOperateButton">
                @Html.Raw(ViewBag.OperateButton)
            </div>
        </div>
        <!--OperateButton End-->
        <!--Content Begin-->
        <div class="ContentArea" id="ContentArea">
            <table class="tbdetail">
                <tr>
                    <td style="text-align: right; ">
                        用户名称：
                    </td>
                    <td style="text-align: left;">
                        <input type="text" class="inputnull input" id="txtUserName"/>
                    </td>
                    <td style="text-align: right;">
                        真实姓名：
                    </td>
                    <td style="text-align: left;">
                        <input type="text" class="inputnull input" id="txtRealName"/>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right; ">
                        街道：
                    </td>
                    <td style="text-align: left;">
                        <select class="inputnull select" id="ddlStreetId">
                            @Html.Raw(ViewBag.GetStreetId)
                        </select>
                    </td>
                    <td style="text-align: right;">
                        执法证号：
                    </td>
                    <td style="text-align: left;">
                        <input type="text" class="inputnull input" id="txtZfNo"/>
                    </td>
                </tr>
                
                <tr>
                    <td style="text-align: right; ">
                        所属中队：
                    </td>
                    <td style="text-align: left;">
                        <select class="inputnull select" id="ddlZdId">
                            @Html.Raw(ViewBag.GetZdId)
                        </select>
                    </td>
                    <td style="text-align: right;">
                        角色：
                    </td>
                    <td style="text-align: left;">
                        <select class="inputnull select" id="ddlRoleId">
                            @Html.Raw(ViewBag.GetRoleId)
                        </select>
                        
                    </td>
                </tr><tr>
                    <td style="text-align: right; ">
                        部门：
                    </td>
                    <td style="text-align: left;">
                        <select class="inputnull select" id="ddlDeptId">
                            @Html.Raw(ViewBag.GetDeptId)
                        </select>
                    </td>
                    <td style="text-align: right;">
                    </td>
                    <td style="text-align: left;">
                        <select class="inputnull select" style="display: none;" id="ddlHomePath">
                            <option value="1">默认页</option>
                            <option value="2">领导页</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;">
                        密码：
                    </td>
                    <td style="text-align: left;">
                        <input type="password" class="inputnull input" id="txt_Pwd"/><span style="margin-left: 5px;">默认密码<span style="color: red;">111111</span></span>
                    </td>
                    <td style="text-align: right;">
                        确认密码：
                    </td>
                    <td style="text-align: left;">
                        <input type="password" class="inputnull input" id="txt_RePwd"/>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;">
                        备注：
                    </td>
                    <td style="text-align: left;" colspan="3">
                        <textarea class="inputnull textarea" id="txtRemark" rows="4"></textarea>
                    </td>
                </tr>
            </table>
        </div>
        
        <!--Content End-->
    </form>
    <!--PageNodeId Begin-->
    <input type="hidden" id="txtListPageNodeId" value="@ViewBag.ListPageNodeId" />
    <!--PageNodeId End-->
</body>
</html>
<script src="/static/js/require/require/2.2.0/require.js"></script>
<script src="/static/js/require/config/1.0.1/require.config.js"></script>
<script type="text/javascript">
    require(["jquery", "MISSY","jquery.md5"], function () {
        var g_ListPageNodeId = $("#txtListPageNodeId").val();
        var g_NodeId = MISSY.getQueryString("NodeId");
        $(function () {
            initPage();
        });
       
        function initPage() {
            $("#BtnSubmitOperate").click(function () { ClickSubmit(); });
            $("#BtnBackOperate").click(function () { ClickBack(); });

            var Id = MISSY.getQueryString("Id");
            if (MISSY.isEmpty(Id))
                return;
            $.ajax({
                url: "InitSingle",
                data: { NodeId: g_NodeId, Id: Id },
                type: "POST",
                dataType: "json",
                ContentType: "application/json;charset=utf-8",
                success: function (response) {
                    if (!response) { MISSY.iErrorReturnNull(); return; }
                    switch (response.ErrorType)//标记
                    {
                        case 0: //错误
                            MISSY.iErrorMessage(response.MessageContent);
                            return;
                        case 1: //返回正确数据
                            break;
                        case 2: //请求地址不正确
                            MISSY.iNoFound(response.MessageContent);
                            return;
                        case 3: //未登录
                            MISSY.iNoLogin(response.MessageContent);
                            return;
                        case 4: //无页面权限
                            MISSY.iNoPageAuth(response.MessageContent);
                            return;
                        case 5: //无操作权限
                            MISSY.iNoOperateAuth(response.MessageContent);
                            return;
                        default:
                            MISSY.iErrorMessage(response.MessageContent);
                            return;
                    }
                    var model = response.Data;
                    $("#txtUserName").val(model.UserName);
                    $("#txtRealName").val(model.RealName);
                    $("#ddlStreetId").val(model.StreetId);
                    $("#ddlZdId").val(model.ZdId);
                    $("#txtZfNo").val(model.ZfNo);
                    $("#ddlDeptId").val(model.DeptId);
                    $("#ddlRoleId").val(model.RoleId);
                    $("#ddlHomePath").val(model.HomePath);
                    $("#txtRemark").val(model.Remark);
                },
                error: function (xmlHttpRequest, textStatus, errorThrown) {
                    MISSY.iDebugAjaxError(xmlHttpRequest, textStatus, errorThrown);
                    MISSY.iSystemAjaxError();
                }
            });
        }

        function ClickBack() //Back
        {
            location.href = "List?NodeId=" + g_ListPageNodeId + "&_r=" + Math.random();
        }

        function ClickSubmit() //Add、Update
        {
            if (!CheckForm()) return;
            var Id = MISSY.getQueryString("Id");
            var Password = $("#txt_Pwd").val();
            if (!MISSY.isEmpty(Password)) {
                Password = $.md5(Password);
            }
            var layerLoadIndex;
            $.ajax({
                url: "AddOrUpdate",
                data: {
                    NodeId: g_NodeId,
                    Id: Id,
                    ZfNo:$("#txtZfNo").val(),
                    StreetId: $("#ddlStreetId").val(),
                    ZdId: $("#ddlZdId").val(),
                    UserName: $("#txtUserName").val(),
                    RealName: $("#txtRealName").val(),
                    Password: Password,
                    DeptId: $("#ddlDeptId").val(),
                    RoleId: $("#ddlRoleId").val(),
                    HomePath: $("#ddlHomePath").val(),
                    Remark:$("#txtRemark").val()
                },
                type: "POST",
                dataType: "json",
                ContentType: "application/json;charset=utf-8",
                beforeSend: function () { layerLoadIndex = MISSY.iShowLoading("正在执行中，请稍候..."); },
                success: function (response) {
                    if (!response) { MISSY.iErrorReturnNull(); return; }
                    switch (response.ErrorType)//标记
                    {
                        case 0://错误
                            MISSY.iErrorMessage(response.MessageContent);
                            return;
                        case 1://返回正确数据
                            MISSY.iSuccessMessage(response.MessageContent, ClickBack);
                            break;
                        case 2://请求地址不正确
                            MISSY.iNoFound(response.MessageContent);
                            return;
                        case 3://未登录
                            MISSY.iNoLogin(response.MessageContent);
                            return;
                        case 4://无页面权限
                            MISSY.iNoPageAuth(response.MessageContent);
                            return;
                        case 5://无操作权限
                            MISSY.iNoOperateAuth(response.MessageContent);
                            return;
                        default:
                            MISSY.iErrorMessage(response.MessageContent);
                            return;
                    }
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

        function BlurUserName()//检查用户名是否存在
        {
            var UserName = $("#txtUserName").val();
            var Id = MISSY.getQueryString("Id");
            if (!MISSY.isEmpty(UserName)) {
                $.ajax({
                    url: "BlurName",
                    data: {
                        NodeId: g_NodeId,
                        Id: Id,
                        UserName: UserName
                    },
                    type: "POST",
                    dataType: "json",
                    ContentType: "application/json;charset=utf-8",
                    success: function (response) {
                        if (!response) { MISSY.iErrorReturnNull(); return; }
                        switch (response.ErrorType)//标记
                        {
                            case 0://错误
                                $("#UserNameMsg").css("color", "red");
                                $("#UserNameMsg").html(response.MessageContent);
                                return;
                            case 1://返回正确数据
                                break;
                            case 2://请求地址不正确
                                MISSY.iNoFound(response.MessageContent);
                                return;
                            case 3://未登录
                                MISSY.iNoLogin(response.MessageContent);
                                return;
                            case 4://无页面权限
                                MISSY.iNoPageAuth(response.MessageContent);
                                return;
                            case 5://无操作权限
                                MISSY.iNoOperateAuth(response.MessageContent);
                                return;
                            default:
                                MISSY.iErrorMessage(response.MessageContent);
                                return;
                        }
                    },
                    error: function (xmlHttpRequest, textStatus, errorThrown) {
                        MISSY.iDebugAjaxError(xmlHttpRequest, textStatus, errorThrown);
                        MISSY.iSystemAjaxError();
                    }
                });
            }
        }

        function CheckForm()  //CheckForm
        {
            return true;
        }
    });
</script>
