package com.meida.paysdk.weixin.wxnative.service.inter;

import com.meida.paysdk.weixin.wxnative.pojo.WxnativeParametersTradePay;
import com.meida.paysdk.weixin.wxnative.pojo.WxnativeResultTradePay;

public interface IWeixinWxnativeTradeService {
	//交易支付
    WxnativeResultTradePay tradePay(WxnativeParametersTradePay builder);
}
