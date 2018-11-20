package com.meida.pay.weixin.wxnative.service.inter;

import com.meida.pay.weixin.wxnative.pojo.ParametersTradePay;
import com.meida.pay.weixin.wxnative.pojo.ResultTradePay;

public interface IWeixinTradeService {
	//交易支付
    ResultTradePay TradePay(ParametersTradePay builder);  
}
