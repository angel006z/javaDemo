package com.meida.pay.alipay.pcweb.service.inter;

import com.meida.pay.alipay.pcweb.pojo.ParametersTradePay;
import com.meida.pay.alipay.pcweb.pojo.ResultTradePay;

public interface IAlipayTradeService {
    //交易支付
    ResultTradePay tradePay(ParametersTradePay builder);

}
