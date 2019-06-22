package com.meida.pay.service.inter;

import com.meida.base.domain.vo.ResultMessage;
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
	 * 交易对账单
	 */
	ResultTradeBillDownload tradeBillDownload(ParametersTradeBillDownload builderParameters);

	/**
	 * 交易验证回跳通知是否合法
	 */
	boolean tradeNotifyVerify(ParametersTradeNotifyVerify builderParameters);

}
