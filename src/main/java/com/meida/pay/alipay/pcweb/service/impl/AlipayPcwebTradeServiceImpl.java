package com.meida.pay.alipay.pcweb.service.impl;

import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.meida.base.domain.vo.ResultMessage;
import com.meida.common.util.constant.EErrorCode;
import com.meida.pay.alipay.config.AlipayClientFactory;
import com.meida.pay.alipay.config.AlipayConfig;
import com.meida.pay.alipay.constant.AlipayTradeStatus;
import com.meida.pay.alipay.pcweb.pojo.AlipayPcwebParametersTradePay;
import com.meida.pay.alipay.pcweb.pojo.AlipayPcwebParametersTradeQuery;
import com.meida.pay.alipay.pcweb.pojo.AlipayPcwebResultTradePay;
import com.meida.pay.alipay.pcweb.pojo.AlipayPcwebResultTradeQuery;
import com.meida.pay.alipay.pcweb.service.inter.IAlipayPcwebTradeService;

public class AlipayPcwebTradeServiceImpl implements IAlipayPcwebTradeService {
	AlipayClient client;

	public AlipayPcwebTradeServiceImpl() {
		client = AlipayClientFactory.getAlipayClientInstance();
	}

	public AlipayPcwebResultTradePay tradePay(AlipayPcwebParametersTradePay builder) {
		AlipayPcwebResultTradePay result = new AlipayPcwebResultTradePay();
		try {
			AlipayTradePagePayRequest payRequest = new AlipayTradePagePayRequest();
			payRequest.setReturnUrl(AlipayConfig.ALIPAY_ReturnUrl);
			payRequest.setNotifyUrl(AlipayConfig.ALIPAY_NotifyUrl);
			payRequest.setBizContent(builder.GetParameters());
			result.setForm(client.pageExecute(payRequest).getBody());
			result.setCode(EErrorCode.Success);
			result.setMessage("操作成功.");
			return result;
		} catch (Exception ex) {
			// 请求支付过程中异常，请重新操作.
			result.setCode(EErrorCode.Error);
			result.setMessage("提交支付宝表单异常，请返回重新操作。");
			return result;
		}
	}

	@Override
	public ResultMessage tradeIsPaySuccess(AlipayPcwebParametersTradeQuery builder) {
		ResultMessage result=new ResultMessage();
		try {
			AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
			request.setBizContent(builder.GetParameters());
			AlipayTradeQueryResponse response = client.execute(request);			
			if (response.isSuccess()) {
				if(response.getTradeStatus().equals(AlipayTradeStatus.TRADE_SUCCESS)||response.getTradeStatus().equals(AlipayTradeStatus.TRADE_FINISHED)) {
					result.setCode(EErrorCode.Success);
					result.setMessage("已支付");		
				}else {
					result.setCode(EErrorCode.Error);
					result.setMessage("未支付");
				}
			} else {
				result.setCode(EErrorCode.Error);
				result.setMessage("查询支付宝交易记录失败.");				
			}
			return result;
		} catch (Exception ex) {
			// 请求支付过程中异常，请重新操作.
			result.setCode(EErrorCode.Error);
			result.setMessage("提交支付宝表单异常，请返回重新操作。");
			return result;
		}
	}

}
