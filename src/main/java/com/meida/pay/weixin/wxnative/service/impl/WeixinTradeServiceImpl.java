package com.meida.pay.weixin.wxnative.service.impl;

import com.meida.pay.weixin.wxnative.pojo.ParametersTradePay;
import com.meida.pay.weixin.wxnative.pojo.ResultTradePay;
import com.meida.pay.weixin.wxnative.service.inter.IWeixinTradeService;

public class WeixinTradeServiceImpl implements IWeixinTradeService{

	@Override
	public ResultTradePay tradePay(ParametersTradePay builder) {
		ResultTradePay result = new ResultTradePay();
        try
        {
//            WxPayData data = new WxPayData();
//            data.SetValue("body", builder.getBody());//商品标题
//            data.SetValue("detail", builder.getDetail());//商品描述
//            data.SetValue("attach", "test");//附加数据
//            data.SetValue("out_trade_no", builder.getOut_trade_no());//订单号
//            data.SetValue("total_fee", builder.getTotal_fee());//总金额
//            data.SetValue("time_start", DateTime.Now.ToString("yyyyMMddHHmmss"));//交易起始时间
//            data.SetValue("time_expire", DateTime.Now.AddHours(2).ToString("yyyyMMddHHmmss"));//交易结束时间
//            data.SetValue("goods_tag", builder.getGoods_tag());//商品标记
//            data.SetValue("trade_type", "NATIVE");//交易类型
//            data.SetValue("product_id", builder.getOut_trade_no());//商品ID
//
//            WxPayData resultWxPayData = WxPayApi.UnifiedOrder(data);//调用统一下单接口
//            String url = resultWxPayData.GetValue("code_url").ToString();//获得统一下单接口返回的二维码链接

//            result.setForm(url);
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
