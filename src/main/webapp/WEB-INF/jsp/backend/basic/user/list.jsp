<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="renderer" content="webkit" />
	<title>${title}列表页面</title>
    <link href="/static/css/normalize.css?v=7.0.0" rel="stylesheet" type="text/css" />
    <link href="/static/css/sysbase.css?v=1.0.0" rel="stylesheet" type="text/css" />
    <link href="/static/css/sysrespond.css?v=1.0.0" rel="stylesheet" type="text/css" />
</head>
<body class="RightBody">
    <!--OperateButton Begin-->
    <div class="OperateButtonArea">
        <div id="divOTitle" class="OTitle">用户管理</div>
        <div id="OperateButton" class="OOperateButton">
            ${OperateButton}
        </div>
    </div>
    <!--OperateButton End-->
    <!--Content Begin-->
    <div class="ContentArea" id="ContentArea">
        <div class="ConditionArea">
            <table class="tbCondition">
                <tr>
                    <td style="text-align: right;">用户名称：</td>
                    <td style="text-align: left;"><input type="text" class="input" id="txtUserName" /></td>
                    <td style="text-align: right;">真实姓名：</td>
                    <td style="text-align: left;"><input type="text" class="input" id="txtRealName" /></td>
                </tr>
                <tr>
                    <td style="text-align: right;">部门名称：</td>
                    <td style="text-align: left;">
                        <select class="select" id="ddlDeptId">
                            <option value="">==全部==</option>
                            @Html.Raw(ViewBag.GetDeptId)
                        </select>
                    </td>
                    <td style="text-align: right;">角色名称：</td>
                    <td style="text-align: left;">
                        <select id="ddlRoleId" class="select">
                            <option value="">==全部==</option>
                            @Html.Raw(ViewBag.GetRoleId)
                        </select>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right; ">
                        街道：
                    </td>
                    <td style="text-align: left;">
                        <select class="inputnull select" id="ddlStreetId">
                            <option value="">==全部==</option>
                            @Html.Raw(ViewBag.GetStreetId)
                        </select>
                    </td>
                    <td style="text-align: right; ">
                        所属中队：
                    </td>
                    <td style="text-align: left;">
                        <select class="inputnull select" id="ddlZdId">
                            <option value="">==全部==</option>
                            @Html.Raw(ViewBag.GetZdId)
                        </select>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;">状态：</td>
                    <td style="text-align: left;">
                        <select id="ddlIsValid" class="select">
                            <option value="">==全部==</option>
                            <option value="1" selected="selected">有效</option>
                            <option value="-1">无效</option>
                        </select>
                    </td>
                    <td></td>
                    <td style="text-align: left;"><input type="button" id="BtnSearch" class="button" value="查询" /></td>
                </tr>
            </table>
        </div>
        <div class="tableArea">
            <table id="tblist" class="tblist">
                <tr class="odd">
                    <th class="thcbAll">
                        <input type="checkbox" align="absmiddle" id="cbListCheckAll" />
                    </th>
                    <th style="width:40px;">序号</th>
                    <th>用户名称</th>
                    <th>真实姓名</th>
                    <th>部门名称</th>
                    <th>角色名称</th>
                    <th>街道</th>
                    <th>执法证号</th>
                    <th style="width: 140px;">操作时间</th>
                    <th style="width: 60px;">状态</th>
                    <th style="width: 60px;">操作</th>
                </tr>
            </table>
            <div id="divPagination"></div>
        </div>
        <input type="hidden" id="sortfield" value="" />
        <input type="hidden" id="sorttype" value="" />
    </div>
    <!--Content End-->
    <!--PageNodeId Begin-->
    <input type="hidden" id="txtAddPageNodeId" value="@ViewBag.AddPageNodeId" />
    <input type="hidden" id="txtEditPageNodeId" value="@ViewBag.EditPageNodeId" />
    <input type="hidden" id="txtDetailPageNodeId" value="@ViewBag.DetailPageNodeId" />
    <!--PageNodeId End-->
</body>
</html>
<script src="/static/js/require/require/2.2.0/require.js"></script>
<script src="/static/js/require/config/1.0.1/require.config.js"></script>
<script type="text/javascript">
    require(["jquery", "MISSY"], function () {
        var g_AddPageNodeId = $("#txtAddPageNodeId").val();
        var g_EditPageNodeId = $("#txtEditPageNodeId").val();
        var g_DetailPageNodeId = $("#txtDetailPageNodeId").val();
        var g_NodeId = MISSY.getQueryString("NodeId");
        var g_PageSize = 20; //PAGE NUMBER
        var g_CurrentPage = 1; //CurrentPage
        var g_PageCount = 5;
        $(function () {
            initPage();
        });
        
        function initPage() //initPage
        {
            $("#BtnAddOperate").click(function () { ClickAdd(); });
            $("#BtnUpdateOperate").click(function () { ClickUpdate(); });
            $("#BtnDeleteOperate").click(function () { ClickDelete(); });
            $("#BtnPhyDeleteOperate").click(function () { ClickPhyDelete(); });
            $("#BtnEnableOperate").click(function () { ClickEnable(); });
            $("#BtnDisableOperate").click(function () { ClickDisable(); });
            $("#BtnResetPasswordOperate").click(function () { ClickResetPassword(); });
            $("#BtnExportOperate").click(function () { ClickExport(); });
            $("#BtnSearch").click(function () { ClickSearch(); });
            $("#cbListCheckAll").click(function () { MISSY.setClickCheckAll(this, "nameCbox"); });
            $("body").keydown(function (event) {
                var e = document.all ? window.event : event;
                var keycode = (e.keyCode ? e.keyCode : e.which);
                if (keycode === 13) {
                    ClickSearch();
                }
            });
            ChangePage(1, g_PageSize);
        }

        function ChangePage(paramCurrentPage, paramPageSize) //ChangePage
        {
            var colNumber = $("#tblist tr th").length;
            $.ajax({
                type: "post",
                url: "SearchList",
                data: {
                    UserName: $("#txtUserName").val(),
                    RealName: $("#txtRealName").val(),
                    DeptId: $("#ddlDeptId").val(),
                    RoleId: $("#ddlRoleId").val(),
                    StreetId: $("#ddlStreetId").val(),
                    ZdId: $("#ddlZdId").val(),
                    IsValid: $("#ddlIsValid").val(),
                    NodeId: g_NodeId,
                    currentPage: paramCurrentPage,
                    pageSize: paramPageSize
                },
                dataType: "json",
                beforeSend: function () {
                    $("#tblist tr:gt(0)").remove();
                    $("#tblist").append("<tr><td align=\"center\" colspan=\"" + colNumber + "\">加载中，请稍等．．．</td></tr>");
                },
                success: function (response) {
                    $("#tblist tr:gt(0)").remove();
                    $("#tblist").append("<tr><td align=\"center\" colspan=\"" + colNumber + "\">请刷新后再试.</td></tr>");

                    if (!response) {MISSY.iErrorReturnNull();return;}
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
                    var buf = new Array();
                    var responseList = response.Data == null ? new Array() : response.Data;
                    for (var i = 0; i < responseList.length; i++) {
                        var model = responseList[i];
                        buf.push("<tr class=\"" + (i % 2 === 0 ? "trbg2" : "trbg1") + "\">");
                        buf.push("<td><input type=\"checkbox\" name=\"nameCbox\" value=\"" + model.UserId + "\" /></td>");
                        buf.push("<td>" + (i + 1) + "</td>");
                        buf.push("<td>" + model.UserName + "</td>");
                        buf.push("<td>" + model.RealName + "</td>");
                        buf.push("<td>" + model.DeptName + "</td>");
                        buf.push("<td>" + model.RoleName + "</td>");
                        buf.push("<td>" + model.StreetName + "</td>");
                        buf.push("<td>" + model.ZfNo + "</td>");
                        buf.push("<td>" + MISSY.formatDate(model.OperateDate, 4) + "</td>");
                        buf.push("<td>" + MISSY.formatIsValid(model.IsValid) + "</td>");
                        buf.push("<td><a href=\"Details?NodeId=" + g_DetailPageNodeId + "&Id=" + model.UserId + "\">查看</a></td>");
                        buf.push("</tr>");
                    }
                    if (buf.length <= 0)
                        buf.push("<tr><td align=\"center\" colspan=\"" + colNumber + "\">暂无数据．</td></tr>");
                    $("#tblist tr:gt(0)").remove();
                    $("#tblist").append(buf.join(""));
                    var responseTotalRecord = response.TotalRecord;
                    var responsePageSize = response.PageSize;
                    var responseCurrentPage = response.CurrentPage;
                    g_CurrentPage = responseCurrentPage;
                    var responsePageCount = response.PageCount;
                    g_PageCount = responsePageCount;
                    if (responsePageCount > 1) {
                        var bufPagination = new Array();
                        bufPagination.push("<div class=\"pagination_main\">");
                        bufPagination.push("<span>总数：" + responseTotalRecord + "</span>");
                        bufPagination.push("<span>第" + responseCurrentPage + "/" + responsePageCount + "页</span>");
                        if (responseCurrentPage <= 1 || responseCurrentPage - 1 > responsePageCount)
                            bufPagination.push("<a class=\"pagination_prev\" style=\"color:gray;\">上一页</a>");
                        else
                            bufPagination.push("<a class=\"pagination_prev\" id=\"BtnPrevPage\" href=\"javascript:void(0);\">上一页</a>");

                        if (responseCurrentPage >= responsePageCount)
                            bufPagination.push("<a class=\"pagination_next\" style=\"color:gray;\">下一页</a>");
                        else
                            bufPagination.push("<a class=\"pagination_next\" id=\"BtnNextPage\" href=\"javascript:void(0);\">下一页</a>");

                        bufPagination.push("<label>到第</label><input type=\"text\" class=\"pagination_input\" id=\"txtGoPage\" /><label>页</label>");
                        bufPagination.push("<input type=\"button\" id=\"BtnConfirmPageGo\" class=\"pagination_button\" value=\"确定\" />");
                        bufPagination.push("</div>");
                        $("#divPagination").html(bufPagination.join(""));
                        $("#BtnPrevPage").on("click", function () { ChangePage((responseCurrentPage - 1), responsePageSize); });
                        $("#BtnNextPage").on("click", function () { ChangePage((responseCurrentPage + 1), responsePageSize); });
                        $("#txtGoPage").on("keydown", function () { ClickPageChange(13); });
                        $("#BtnConfirmPageGo").on("click", function () { ClickPageChange(); });
                    } else {
                        $("#divPagination").html("");
                    }
                },
                error: function (xmlHttpRequest, textStatus, errorThrown) {
                    MISSY.iDebugAjaxError(xmlHttpRequest, textStatus, errorThrown);
                    $("#tblist tr:gt(0)").remove();
                    $("#tblist").append("<tr><td align=\"center\" colspan=\"" + colNumber + "\">系统性错误，请稍后再试.或点击<a href=\"ClickRefresh()\">刷新</a></td></tr>");
                }
            });
        }

        function ClickPageChange(codeType) //PageChange
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

        function ClickRefresh() //Refresh
        {
            ChangePage(g_CurrentPage, g_PageSize);
        }

        function ClickSearch() //Serach
        {
            ChangePage(1, g_PageSize);
        }

        function ClickAdd() //Add
        {
            location.href = "Details?NodeId=" + g_AddPageNodeId + "&_r=" + Math.random();
        }

        function ClickUpdate() //Update
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
            if (id !== "") {
                location.href = "Details?ID=" + id + "&NodeId=" + g_EditPageNodeId + "&_r=" + Math.random();
            } else {
                MISSY.iErrorMessage("请选择一项数据.");
            }
        }

        function ConfirmOperateStatus(paramButtonId, paramIds) //OperateStatus操作状态
        {
            var layerLoadIndex;
            $.ajax({
                url: "ListOperateStatus",
                data: { NodeId: g_NodeId, oButtonId: paramButtonId, ids: paramIds },
                type: "post",
                dataType: "json",
                beforeSend: function () { layerLoadIndex = MISSY.iShowLoading("正在执行中，请稍候"); },
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
                            MISSY.iSuccessMessage(response.MessageContent, ClickRefresh);
                            return;
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

        function ClickDisable() //Disable禁用
        {
            var paramIds = MISSY.getCheckboxCheckedVal("nameCbox", ",");
            if (paramIds !== "") {
                MISSY.iConfirmMessage("您确定要禁用吗？", function () { ConfirmOperateStatus(22, paramIds); });
            } else {
                MISSY.iErrorMessage("请选择一项数据.");
            }
        }

        function ClickEnable() //Enable启用
        {
            var paramIds = MISSY.getCheckboxCheckedVal("nameCbox", ",");
            if (paramIds !== "") {
                MISSY.iConfirmMessage("您确定要启用吗？", function () { ConfirmOperateStatus(21, paramIds); });

            } else {
                MISSY.iErrorMessage("请选择一项数据.");
            }
        }

        function ClickDelete() //Delete
        {
            var paramIds = MISSY.getCheckboxCheckedVal("nameCbox", ",");
            if (paramIds !== "") {
                MISSY.iConfirmMessage("删除后不可恢复，您确定要删除吗？", function () { ConfirmOperateStatus(3, paramIds); });
            } else {
                MISSY.iErrorMessage("请选择一项数据.");
            }
        }

        function ClickPhyDelete() //PhyDelete
        {
            var paramIds = MISSY.getCheckboxCheckedVal("nameCbox", ",");
            if (paramIds !== "") {
                MISSY.iConfirmMessage("请谨慎操作，删除后数据不可恢复，您确定要删除吗？", function () {
                    var layerLoadIndex;
                    $.ajax({
                        url: "ListPhyDel",
                        data: { NodeId: g_NodeId, ids: paramIds },
                        type: "post",
                        dataType: "json",
                        beforeSend: function () { layerLoadIndex = MISSY.iShowLoading("正在执行中，请稍候"); },
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
                                    MISSY.iSuccessMessage(response.MessageContent, ClickRefresh);
                                    return;
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
                });
            } else {
                MISSY.iErrorMessage("请选择一项数据.");
            }
        }

        function ClickResetPassword() {
            var paramIds = MISSY.getCheckboxCheckedVal("nameCbox", ",");
            if (paramIds !== "") {
                MISSY.iConfirmMessage("您确定要重置密码吗？", function () {
                    var layerLoadIndex;
                    $.ajax({
                        url: "ResetPassword",
                        data: { NodeId: g_NodeId, ids: paramIds },
                        type: "post",
                        dataType: "json",
                        beforeSend: function () { layerLoadIndex = MISSY.iShowLoading("正在执行中，请稍候"); },
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
                                    MISSY.iSuccessMessageAlert(response.MessageContent);
                                    return;
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
                });
            } else {
                MISSY.iErrorMessage("请选择一项数据.");
            }
        }

        function ClickExport() //导出
        {
            var layerLoadIndex;
            $.ajax({
                url: "ExportReport",
                data: {
                    UserName: $("#txtUserName").val(),
                    RealName: $("#txtRealName").val(),
                    DeptId: $("#ddlDeptId").val(),
                    RoleId: $("#ddlRoleId").val(),
                    StreetId: $("#ddlStreetId").val(),
                    ZdId: $("#ddlZdId").val(),
                    IsValid: $("#ddlIsValid").val(),
                    NodeId: g_NodeId
                },
                type: "POST",
                dataType: "json",
                async: false,
                beforeSend: function () { layerLoadIndex = MISSY.iShowLoading("数据导出中..."); },
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
                            var elementA = document.createElement("a");
                            elementA.setAttribute("href", "/Upload/temp/" + response.MessageContent);
                            elementA.setAttribute("style", "display:none;");
                            elementA.innerHTML = response.MessageContent;
                            document.body.appendChild(elementA);
                            elementA.click();
                            MISSY.iSuccessMessage("导出成功.");
                            return;
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
    });
</script>