package com.meida.pay.service.inter;

import com.meida.base.vo.ResultMessage;
import com.meida.pay.pojo.*;

/**
 * 支付接口
 *
 */
public interface ITradeService {
	/**
	 * 交易支付
	 */
	ResultTradePay tradePay(ParametersTradePay builderParameters);

	/**
	 * 是否交易成功
	 */
		ResultMessage tradeIsPaySuccess(ParametersTradeQuery builder);

	/**
	 * 交易查询
	 */
	ResultTradeQuery tradeQuery(ParametersTradeQuery builderParameters);

	/**
	 * 交易退款
	 * 
	 */
	ResultTradeRefund tradeRefund(ParametersTradeRefund builderParameters);

	/**
	 * 交易退款查询
	 */
	ResultTradeRefundQuery tradeRefundQuery(ParametersTradeRefundQuery builderParameters);

	/**
	 * 交易关闭
	 */
	ResultTradeClose tradeClose(ParametersTradeClose builderParameters);

	/**
	 * 查询对账单下载地址
	 */
	ResultBillDownloadurlQuery billDownloadurlQuery(ParametersBillDownloadurlQuery builderParameters);

	/**
	 * 交易验证回跳通知是否合法
	 */
	boolean tradeNotifyVerify(ParametersTradeNotifyVerify builderParameters);
	/**
	 *商家账户当前余额查询
	 */
	ResultBillBalanceQuery billBalanceQuery(ParametersBillBalanceQuery builderParameters);
}
