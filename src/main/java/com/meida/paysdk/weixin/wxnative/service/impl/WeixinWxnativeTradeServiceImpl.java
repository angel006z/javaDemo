package com.meida.paysdk.weixin.wxnative.service.impl;

import com.meida.common.util.DateUtils;
import com.meida.paysdk.weixin.wxnative.pojo.WxnativeParametersTradePay;
import com.meida.paysdk.weixin.wxnative.pojo.WxnativeResultTradePay;
import com.meida.paysdk.weixin.wxnative.service.inter.IWeixinWxnativeTradeService;

import java.util.Date;

public class WeixinWxnativeTradeServiceImpl implements IWeixinWxnativeTradeService {

	@Override
	public WxnativeResultTradePay tradePay(WxnativeParametersTradePay builder) {
		WxnativeResultTradePay result = new WxnativeResultTradePay();
        try
        {
            Date nowTime=DateUtils.now();
//            WXPayConfigImpl wxPayConfigImpl= new WXPayConfigImpl();
//
//            WXPay wxpay = new WXPay(wxPayConfigImpl);
//            Map<String, String> reqData =new HashMap<>();
//            reqData.put("body", builder.getBody());//商品标题
//            reqData.put("detail", builder.getDetail());//商品描述
//            reqData.put("attach", "test");//附加数据
//            reqData.put("out_trade_no", builder.getOut_trade_no());//订单号
//            reqData.put("total_fee", builder.getTotal_fee()+"");//总金额
//            reqData.put("time_start", DateUtils.formatDate(nowTime,"yyyyMMddHHmmss"));//交易起始时间
//            reqData.put("time_expire", DateUtils.formatDate( DateUtils.addHours(nowTime,2),"yyyyMMddHHmmss"));//交易结束时间
//            reqData.put("goods_tag", builder.getGoods_tag());//商品标记
//            reqData.put("trade_type", "NATIVE");//交易类型
//            reqData.put("product_id", builder.getOut_trade_no());//商品ID
//
//            Map<String, String> resultWxPayData = wxpay.unifiedOrder(reqData);//调用统一下单接口
//            String url = resultWxPayData.get("code_url");//获得统一下单接口返回的二维码链接

            result.setForm("");
            result.setCode("1");
            result.setMessage( "操作成功.");
            return result;
        }
        catch (Exception ex)
        {            
            //请求支付过程中异常，请重新操作.     
            result.setCode("0");
            result.setMessage("提交微信表单异常，请返回重新操作。");
            return result;
        }
	}
	
}
