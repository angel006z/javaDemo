package com.meida.pay.alipay.page.service.inter;

import com.meida.base.domain.vo.ResultMessage;
import com.meida.pay.alipay.page.pojo.AlipayPageParametersTradePay;
import com.meida.pay.alipay.page.pojo.AlipayPageResultTradePay;
import com.meida.pay.alipay.page.pojo.AlipayPageParametersTradeQuery;

public interface IAlipayPageTradeService {
    //交易支付
    AlipayPageResultTradePay tradePay(AlipayPageParametersTradePay builder);
    
    //交易查询
	//ResultTradeQuery tradeQuery(ParametersTradeQuery builder);
	//是否交易成功
	ResultMessage tradeIsPaySuccess(AlipayPageParametersTradeQuery builder);
}
