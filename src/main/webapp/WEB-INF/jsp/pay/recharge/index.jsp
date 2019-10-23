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
    <title>充值</title>
    <link rel="stylesheet" href="<%=basePath%>/static/css/normalize.css?v=1.0.0">

    <link rel="stylesheet" href="<%=basePath%>/static/css/charge.css?v=1.0.1">
</head>
<body>
<div class="container">
    <div class="container-title">
        <h2>充值中心<a href="record">充值记录</a></h2>
    </div>
    <div class="container-main">
        <div class="row clearfix">
            <div class="title">当前充值账号：</div>
            <div class="content">
                AAA
            </div>
        </div>
        <div class="row clearfix">
            <div class="title">请选择充值金额：</div>
            <div class="content">
                <ul class="charge-money">
                    <li class="item" data-price="100">
                        <a href="javascript:void(0);"><b class="price">
                            100元
                        </b></a>
                    </li>
                    <li class="item" data-price="200">
                        <a href="javascript:void(0);"><b class="price">
                            200元
                        </b></a>
                    </li>
                    <li class="item" data-price="500">
                        <a href="javascript:void(0);"><b class="price">
                            500元
                        </b></a>
                    </li>
                    <li class="item" data-price="1000">
                        <a href="javascript:void(0);">
                            <b class="price">
                                1000元
                            </b>
                        </a>
                    </li>
                    <li class="item" data-price="2000">
                        <a href="javascript:void(0);">
                            <b class="price">
                                2000元
                            </b>
                        </a>
                    </li>
                    <li class="item" data-price="1">
                        <a class="custom" href="javascript:void(0);">
                            <strong class="amount">
                                自定义
                            </strong>
                            <strong class="amount">
                                充值金额
                            </strong>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="row charge-custom clearfix" style="display: none;">
            <div class="title">请输入充值金额：</div>
            <div class="content">
                <input type="text" id="custom_fee" value="1" name="custom_fee" maxlength="10" autocomplete="off"> 元
            </div>
        </div>
        <div class="row charge-cost clearfix">
            <div class="title">应付金额：</div>
            <div class="content">
                <span class="number total_fee" style="color:#ff6a06">100</span>
                <span class="number1">元</span>
            </div>
        </div>
        <div class="row charge-type clearfix">
            <div class="title">支付方式：</div>
            <div class="content">
                <ul class="charge-pay-type">
                    <li class="pay-type-item alipay active" data-pay-type="alipay" data-pay-channel="Alipay_PAGE">
                        <a href="javascript:void(0);">
                            <img src="<%=basePath%>/static/img/charge-channel-alipay.jpg" height="38" width="107"/>
                        </a>
                    </li>
                    <li class="pay-type-item weixin" data-pay-type="weixin" data-pay-channel="Weixin_NATIVE">
                        <a href="javascript:void(0);"><img src="<%=basePath%>/static/img/charge-channel-weixin.jpg"
                                                           height="38" width="107"/></a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="row clearfix">
            <div class="title">&nbsp;</div>
            <div class="content">
                <button class="confirm-charge">确认充值</button>
            </div>
        </div>
    </div>
</div>
<div id="divDialogWeixinTip" style="display: none;">
    <div id="qrcode" style="width:100px; height:100px; margin-top:15px;"></div>
</div>
<div id="divDialogPayLoadingTip" style="display:none;" title="请支付订单">
    <div class="charge-bankpay-tip">
        <div class="content">
        <div class="tip">请您在<strong class="timer"><span class="hour">1</span>小时<span class="minute">59</span>分<span
                class="second"></span></strong>内，在新打开的支付页面中完成支付...
        </div>
        <div class="btn-group">
            <a class="btn btn-complete" href="javascript:void(0);">支付完成</a>
            <a class="btn btn-problem" target="_blank"
               href="http://bbs.360safe.com/forum.php?mod=viewthread&amp;tid=6999505&amp;page=1">遇到问题?</a>
        </div>
        <div class="tail">
            <a class="modify" href="javascript:void(0);">&lt;返回修改支付方式</a>
        </div></div>
    </div>
</div>
<div id="form-pay" style="display: none;"></div>
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
    var g_basePath='<%=basePath%>';
    $(function () {
        $(".charge-money").on("click", ".item", function () {
            var tempPrice = $(this).attr("data-price");
            if (tempPrice == 1) {
                $(".charge-custom").show();
                $("#custom_fee").val("").focus().val(tempPrice);
            } else {
                $(".charge-custom").hide();
            }
            $(".total_fee").text(tempPrice);
            $(".charge-money .item").removeClass("active");
            $(this).addClass("active");
        });
        $("#custom_fee").keyup(function () {
            var tempPrice = $(this).val();
            $(".total_fee").text(tempPrice);
        });

        $(".charge-type").on("click", ".pay-type-item", function () {
            $(".charge-type .pay-type-item").removeClass("active");
            $(this).addClass("active");
        });
        $(".charge-bankpay-tip").on("click",".modify",function(){
            MISSY.iHideDialog();
        });
        $(".confirm-charge").click(function () {
            rechargeOperate();
        });
    });

    function makeCode(tempVal) {
        $("#qrcode").html("");
        var qrcode = new QRCode(document.getElementById("qrcode"), {
            width: 100,
            height: 100
        });
        qrcode.makeCode(tempVal);
    }

    function rechargeOperate() {
        var payType = $(".charge-type .pay-type-item.active").attr("data-pay-type");
        var payChannel = $(".charge-type .pay-type-item.active").attr("data-pay-channel");
        var totalFee = $(".total_fee").text();
        var layerLoadIndex;
        $.ajax({
            url: "rechargeOperate",
            data: JSON.stringify({
                payType: payType,
                payChannel: payChannel,
                totalFee: totalFee
            }),
            type: "post",
            dataType: "json",
            beforeSend: function () {
                layerLoadIndex = MISSY.iShowLoading("正在执行中，请稍候");
            },
            contentType: "application/json;charset=utf-8",
            success: function (response) {
                if (response.code != "1") {
                    MISSY.iWrongMessage(response.code, response.message);
                    return;
                }
                if (payType == "weixin") {

                } else {
                    MISSY.iShowDialog("divDialogPayLoadingTip", "请支付订单", 480, 236);
                    var action = g_basePath+"/pay/recharge/jump";
                    var form = $("<form></form>");
                    $("#form-pay").append(form);
                    form.attr("action", action);
                    form.attr("method", "post");
                    form.attr("target", "_blank");
                    var formMsg = $('<input type="text" name="formMsg" />');
                    formMsg.attr('value', $.base64.encode(response.message, "utf-8"));
                    form.append(formMsg);
                    form.submit();
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
</script>