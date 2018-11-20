package com.meida.pay.alipay.pcweb.service.impl;

import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.meida.pay.alipay.config.AlipayClientFactory;
import com.meida.pay.alipay.config.Config;
import com.meida.pay.alipay.pcweb.pojo.ParametersTradePay;
import com.meida.pay.alipay.pcweb.pojo.ResultTradePay;
import com.meida.pay.alipay.pcweb.service.inter.IAlipayTradeService;

public class AlipayTradeServiceImpl implements IAlipayTradeService {
	AlipayClient client;

	public AlipayTradeServiceImpl() {
		client = AlipayClientFactory.getAlipayClientInstance();
	}

	public ResultTradePay TradePay(ParametersTradePay builder) {
		ResultTradePay result = new ResultTradePay();
		try {
			AlipayTradePagePayRequest payRequest = new AlipayTradePagePayRequest();
			payRequest.setReturnUrl(Config.ALIPAY_ReturnUrl);
			payRequest.setNotifyUrl(Config.ALIPAY_NotifyUrl);
			payRequest.setBizContent(builder.GetParameters());
			result.setForm(client.pageExecute(payRequest).getBody());
			result.setErrorType(1);
			result.setMessageContent("操作成功.");
			return result;
		} catch (Exception ex) {
			// 请求支付过程中异常，请重新操作.
			result.setErrorType(0);
			result.setMessageContent("提交支付宝表单异常，请返回重新操作。");
			return result;
		}
	}

}
