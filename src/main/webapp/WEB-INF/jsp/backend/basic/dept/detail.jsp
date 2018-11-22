<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="renderer" content="webkit" />
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <title>${title}</title>
    <link href="/static/css/normalize.css?v=7.0.0" rel="stylesheet" type="text/css" />
    <link href="/static/css/sysbase.css?v=1.0.0" rel="stylesheet" type="text/css" />
    <link href="/static/css/sysrespond.css?v=1.0.0" rel="stylesheet" type="text/css" />
</head>
<body class="RightBody">
    <form id="form1">
        <!--OperateButton Begin-->
        <div class="OperateButtonArea">
            <div id="divOTitle" class="OTitle">部门管理</div>
            <div id="OperateButton" class="OOperateButton">
                ${OperateButton}
            </div>
        </div>
        <!--OperateButton End-->
        <!--Content Begin-->
        <div class="ContentArea" id="ContentArea">
            <table class="tbdetail">
                <tr>

                    <td style="text-align: right; ">
                        <span class="inputnull must">*</span>部门名称：
                    </td>
                    <td style="text-align: left;">
                        <input type="text" class="inputnull input" id="txtDeptName" />
                    </td>
                    <td style="text-align: right;width: 20%;">
                        部门编号：
                    </td>
                    <td style="text-align: left;">
                        <input type="text" class="inputnull input" id="txtDeptCode" />
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
    require(["jquery", "MISSY"], function () {
        var g_ListPageNodeId = $("#txtListPageNodeId").val();
        var g_NodeId = MISSY.getQueryString("NodeId");
        $(function () {
            initPage();
        });
        
        function initPage() {
            $("#BtnSubmitOperate").click(function () { ClickSubmit(); });
            $("#BtnBackOperate").click(function () { ClickBack(); });
            var Id = MISSY.getQueryString("Id");
            if (MISSY.isEmpty(Id)) return;
            $.ajax({
                url: "InitSingle",
                data: { NodeId: g_NodeId, Id: Id },
                type: "POST",
                dataType: "json",
                ContentType: "application/json;charset=utf-8",
                success: function (response) {
                    if (!response) {
                        MISSY.iErrorReturnNull();
                        return;
                    }
                    switch (response.ErrorType) //标记
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
                    $("#txtDeptCode").val(model.DeptCode);
                    $("#txtDeptName").val(model.DeptName);
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
            var layerLoadIndex;
            $.ajax({
                url: "AddOrUpdate",
                data: {
                    NodeId: g_NodeId,
                    Id: Id,
                    DeptCode: $("#txtDeptCode").val(),
                    DeptName: $("#txtDeptName").val(),
                    Remark: $("#txtRemark").val()
                },
                type: "POST",
                dataType: "json",
                ContentType: "application/json;charset=utf-8",
                beforeSend: function () { layerLoadIndex = MISSY.iShowLoading("正在执行中，请稍候..."); },
                success: function (response) {
                    if (!response) {MISSY.iErrorReturnNull();return;}
                    switch (response.ErrorType) //标记
                    {
                        case 0: //错误
                            MISSY.iErrorMessage(response.MessageContent);
                            return;
                        case 1: //返回正确数据
                            MISSY.iSuccessMessage(response.MessageContent, ClickBack);
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

        function CheckForm() //CheckForm
        {
            return true;
        }
    });
</script>

