package com.meida.pay.service.inter;

import com.meida.pay.pojo.ParametersTradeBillDownload;
import com.meida.pay.pojo.ParametersTradeClose;
import com.meida.pay.pojo.ParametersTradeNotifyVerify;
import com.meida.pay.pojo.ParametersTradePay;
import com.meida.pay.pojo.ParametersTradeQuery;
import com.meida.pay.pojo.ParametersTradeRefund;
import com.meida.pay.pojo.ParametersTradeRefundQuery;
import com.meida.pay.pojo.ResultTradeBillDownload;
import com.meida.pay.pojo.ResultTradeClose;
import com.meida.pay.pojo.ResultTradePay;
import com.meida.pay.pojo.ResultTradeQuery;
import com.meida.pay.pojo.ResultTradeRefund;
import com.meida.pay.pojo.ResultTradeRefundQuery;

public interface ITradeService {
	/**
	 * 交易支付
	 */
	ResultTradePay TradePay(ParametersTradePay builderParameters);

	/**
	 * 交易查询
	 */
	ResultTradeQuery TradeQuery(ParametersTradeQuery builderParameters);

	/**
	 * 交易退款
	 * 
	 */
	ResultTradeRefund TradeRefund(ParametersTradeRefund builderParameters);

	/**
	 * 交易退款查询
	 */
	ResultTradeRefundQuery TradeRefundQuery(ParametersTradeRefundQuery builderParameters);

	/**
	 * 交易关闭
	 */
	ResultTradeClose TradeClose(ParametersTradeClose builderParameters);

	/**
	 * 交易对账单
	 */
	ResultTradeBillDownload TradeBillDownload(ParametersTradeBillDownload builderParameters);

	/**
	 * 交易验证回跳通知是否合法
	 */
	boolean TradeNotifyVerify(ParametersTradeNotifyVerify builderParameters);

}
