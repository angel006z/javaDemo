<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path;
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="renderer" content="webkit"/>
    <title>充值记录</title>
    <link rel="stylesheet" href="<%=basePath%>/static/css/normalize.css?v=1.0.0">
    <link rel="stylesheet" href="<%=basePath%>/static/css/charge.css?v=1.0.1">
    <style>
        .cr-title {margin-bottom: 20px;color: #3C3C3C;font-size: 14px;font-weight: bold;border-bottom: 2px solid #888;line-height: normal;padding-bottom: 5px;padding-top: 25px;}
        .cr-time-left {float: left;}
        .cr-time-right {float: right;}
        .cr-time-item {display: inline-block;height: 25px;line-height: 25px;padding: 0 15px;border-radius: 3px;cursor: pointer;margin-right: 10px;}
        .cr-time-item:hover, .cr-time-item-selected {background-color: #DB3752;color: #fff;}
        .cr-area {*zoom: 1;margin-top: 10px;}
        .cr-area-table {font-size: 14px;border-collapse: collapse;border-spacing: 0;border: 1px solid #ddd;border-top-width: 2px;table-layout: fixed;width: 100%;}
        .cr-area-table tr th {font-weight: normal;text-align: center;padding: 8px 0px;background: #f2f2f2;border: 1px solid #ddd;}
        .cr-area-table tr td {font-weight: normal;text-align: center;padding: 8px 0px;border: 1px solid #ddd;}
    </style>

</head>
<body>
<div><a href="index">充值</a></div>
<div class="charge-record">
    <div class="cr-title">充值记录（只记录最近三个月）</div>
    <div class="cr-time">
        <div class="cr-time-left">
            <span class="cr-time-item cr-time-item-selected" data-value="0">全部</span>
            <span class="cr-time-item" data-value="1">今天</span>
            <span class="cr-time-item" data-value="2">本周</span>
            <span class="cr-time-item" data-value="3">本月</span>
            <span class="cr-time-item" data-value="4">最近三个月</span>
        </div>
        <div class="cr-time-right">
            总积分：1000
            <a href="index" class="button" style="width:120px;height:40px;">充值</a>
        </div>
    </div>
    <div class="cr-area">
        <table id="tableList" class="cr-area-table">
            <tr>
                <th>交易号</th>
                <th>充值时间</th>
                <th>金额（元）</th>
                <th>充值通道</th>
                <th>充值状态</th>
            </tr>
        </table>
        <div id="divPagination" style="margin-top: 10px;text-align: right;"></div>
    </div>
</div>

<!-- Script Begin -->
<script src="<%=basePath%>/static/js/jquery/jquery/1.11.3/jquery.js"
        type="text/javascript"></script>
<script src="<%=basePath%>/static/js/layer/layer/3.1.1/layer.js"
        type="text/javascript"></script>
<script src="<%=basePath%>/static/js/layer/laypage/1.3.0/laypage.js"
        type="text/javascript"></script>
<script src="<%=basePath%>/static/js/missy/missy/2.2.0/missy.js"
        type="text/javascript"></script>
<script src="<%=basePath%>/static/js/jquery/base64/0.0.3/jquery.base64.js"
        type="text/javascript"></script>
<script src="<%=basePath%>/static/js/qrcodejs/qrcode.js"
        type="text/javascript"></script>
<!-- Script End -->
</body>
</html>
<script type="text/javascript">
    var g_PageSize = 10;
    var g_CurrentPage = 1;
    var g_PageCount = 5;
    $(function () {
        $(".cr-time-item").click(function () {
            $(".cr-time-item").removeClass("cr-time-item-selected");
            $(this).addClass("cr-time-item-selected");
            clickSearch();
        });
        initPage();
    });
    function initPage()
    {
        changePage(1, g_PageSize);
    }

    function changePage(_currentPage, _pageSize)
    {
        var colNumber = $("#tableList tr th").length;
        var tempTimeType = $(".cr-time-item-selected").attr("data-value");
        $.ajax({
            type: "post",
            url: "searchList",
            data: JSON.stringify({
                pagination: {
                    currentPage: _currentPage,
                    pageSize: _pageSize,
                },
                timeType:tempTimeType
            }),
            dataType: "json",
            beforeSend: function () {
                $("#tableList tr:gt(0)").remove();
                $("#tableList").append("<tr><td align=\"center\" colspan=\"" + colNumber + "\">加载中，请稍等．．．</td></tr>");
            },
            contentType: "application/json;charset=utf-8",
            success: function (response) {
                if (response.code != "1") {
                    MISSY.iWrongMessage(response.code, response.message);
                    return;
                }

                var buf = new Array();
                var List = response.data == null ? new Array() : response.data;
                for (var i = 0; i < List.length; i++) {
                    var model = List[i];
                    buf.push("<tr>");
                    buf.push("<td>" + model.orderNo + "</td>");
                    buf.push("<td>" + MISSY.formatDate(model.rechargeDate,4) + "</td>");
                    buf.push("<td><strong>" + model.rechargeAmount + "</strong></td>");
                    var rechargeType = "未知";
                    switch (model.rechargeType)
                    {
                        case "alipay": rechargeType = "支付宝"; break;
                        case "weixin": rechargeType = "微信"; break;
                        case "bank": rechargeType = "储蓄卡"; break;
                    }
                    buf.push("<td>" + rechargeType + "</td>");
                    var rechargeStatus = "未知";
                    switch (model.rechargeStatus)
                    {
                        case "yes": rechargeStatus = "已付款"; break;
                        case "no": rechargeStatus = "未付款"; break;
                    }
                    buf.push("<td>" + rechargeStatus + "</td>");
                    buf.push("</tr>");
                }
                if (buf.length <= 0)
                    buf.push("<tr><td align=\"center\" colspan=\"" + colNumber + "\">暂无数据．</td></tr>");
                $("#tableList tr:gt(0)").remove();
                $("#tableList").append(buf.join(""));

                g_CurrentPage = response.pagination.CurrentPage;
                g_PageCount = response.pagination.PageCount;
                if (g_PageCount > 1) {
                    MISSY.setCommonPagination(g_PageCount, g_CurrentPage, response.pagination.PageSize, 'divPagination', ChangePage);
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                MISSY.iDebugAjaxError(XMLHttpRequest, textStatus, errorThrown);
                $("#tableList tr:gt(0)").remove();
                $("#tableList").append("<tr><td align=\"center\" colspan=\"" + colNumber + "\">系统性错误，请稍后再试.或点击<a href=\"javascrit:clickRefresh();\">刷新</a></td></tr>");
            }
        });
    }

    function clickRefresh()
    {
        changePage(g_CurrentPage, g_PageSize);
    }
    function clickSearch()
    {
        changePage(1, g_PageSize);
    }
</script>