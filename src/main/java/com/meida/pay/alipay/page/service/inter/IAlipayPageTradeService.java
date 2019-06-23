package com.meida.pay.alipay.page.service.inter;

import com.meida.base.domain.vo.ResultMessage;
import com.meida.pay.alipay.page.pojo.*;

public interface IAlipayPageTradeService {
    /**
     * 统一收单下单并支付页面接口
     * PC场景下单并支付
     * @param builder
     * @return
     */
    AlipayPageResultTradePay tradePay(AlipayPageParametersTradePay builder);

    /**
     * 统一收单线下交易预创建
     * 收银员通过收银台或商户后台调用支付宝接口，生成二维码后，展示给用户，由用户扫描二维码完成订单支付。
     * @param builder
     * @return
     */
     AlipayPageResultTradePrecreate tradePay(AlipayPageParametersTradePrecreate builder);

    
    //交易查询
	//ResultTradeQuery tradeQuery(ParametersTradeQuery builder);

    /**
     * 查询支付宝支付是否已成功
     * @param builder
     * @return
     */
	ResultMessage tradeIsPaySuccess(AlipayPageParametersTradeQuery builder);


}
