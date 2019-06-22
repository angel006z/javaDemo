package com.meida.pay.alipay.pcweb.service.inter;

import com.meida.base.domain.vo.ResultMessage;
import com.meida.pay.alipay.pcweb.pojo.AlipayPcwebParametersTradePay;
import com.meida.pay.alipay.pcweb.pojo.AlipayPcwebResultTradePay;
import com.meida.pay.alipay.pcweb.pojo.AlipayPcwebParametersTradeQuery;
import com.meida.pay.alipay.pcweb.pojo.AlipayPcwebResultTradeQuery;

public interface IAlipayPcwebTradeService {
    //交易支付
    AlipayPcwebResultTradePay tradePay(AlipayPcwebParametersTradePay builder);
    
    //交易查询
	//ResultTradeQuery tradeQuery(ParametersTradeQuery builder);
	//是否交易成功
	ResultMessage tradeIsPaySuccess(AlipayPcwebParametersTradeQuery builder);
}
