package com.meida.pay.weixin.wxnative.service.inter;

import com.meida.pay.weixin.wxnative.pojo.WxnativeParametersTradePay;
import com.meida.pay.weixin.wxnative.pojo.WxnativeResultTradePay;

public interface IWeixinWxnativeTradeService {
	//交易支付
    WxnativeResultTradePay tradePay(WxnativeParametersTradePay builder);
}
