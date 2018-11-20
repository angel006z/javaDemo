package com.meida.pay.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.meida.common.util.JsonUtils;
import com.meida.common.util.StringUtils;
import com.meida.pay.pojo.EPayType;
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
import com.meida.pay.service.inter.ITradeService;

public class TradeServiceImpl implements ITradeService {
	private static final Logger logger = LoggerFactory.getLogger(TradeServiceImpl.class);// slf4j日志记录器

	@Override
	public ResultTradePay TradePay(ParametersTradePay builderParameters) {
		var validateResult = Validate(builderParameters);
		if (validateResult.getErrorType() != 1) {
			String logMsg = String.format("ClassName：%s;MethodName：%s;MethodParameters：%s;Message:返回结果：%s",
					this.getClass().getName(), "TradePay", JsonUtils.toJSONString(builderParameters), JsonUtils.toJSONString(validateResult));
			logger.info(logMsg);
			return validateResult;
		}
		if (builderParameters.getPayType() == EPayType.Alipay) {
			var builder = new com.meida.pay.alipay.pcweb.pojo.ParametersTradePay();
			builder.setOut_trade_no(builderParameters.getOut_trade_no());
			builder.setSubject(builderParameters.getSubject());
			builder.setBody(builderParameters.getBody());
			builder.setTotal_amount(builderParameters.getTotal_fee());
			boolean isValidateParameters = builder.Validate();
			if (!isValidateParameters) {
				var prrModel = new ResultTradePay();
				prrModel.setErrorType(0);
				prrModel.setMessageContent("参数不符合支付宝基本参数要求，请返回重新操作。");
				return prrModel;
			} else {
				var tradeServiceImpl = new com.meida.pay.alipay.pcweb.service.impl.AlipayTradeServiceImpl();
				var result = tradeServiceImpl.TradePay(builder);
				var prrModel = new ResultTradePay();
				prrModel.setErrorType(result.getErrorType());
				prrModel.setMessageContent(result.getMessageContent());
				prrModel.setForm(result.getForm());
				return prrModel;
			}
		} else if (builderParameters.getPayType() == EPayType.Weixin) {
			var builder = new com.meida.pay.weixin.wxnative.pojo.ParametersTradePay();
			builder.setOut_trade_no(builderParameters.getOut_trade_no());
			builder.setBody(builderParameters.getSubject());
			builder.setDetail(builderParameters.getBody());
			builder.setTotal_fee((int) Double.parseDouble(builderParameters.getTotal_fee()) * 100);
			boolean isValidateParameters = builder.Validate();
			if (!isValidateParameters) {
				var prrModel = new ResultTradePay();
				prrModel.setErrorType(0);
				prrModel.setMessageContent("参数不符合微信基本参数要求，请返回重新操作。");
				return prrModel;
			} else {
				var tradeServiceImpl = new com.meida.pay.weixin.wxnative.service.impl.WeixinTradeServiceImpl();
				var result = tradeServiceImpl.TradePay(builder);
				var prrModel = new ResultTradePay();
				prrModel.setErrorType(result.getErrorType());
				prrModel.setMessageContent(result.getMessageContent());
				prrModel.setForm(result.getForm());
				return prrModel;
			}
		} else if (builderParameters.getPayType() == EPayType.Banks) {
			var prrModel = new ResultTradePay();
			prrModel.setErrorType(0);
			prrModel.setMessageContent("暂不支持银行支付方式.");
			return prrModel;
		} else {
			var prrModel = new ResultTradePay();
			prrModel.setErrorType(0);
			prrModel.setMessageContent("在线支付类型（PayType）参数错误.");
			return prrModel;
		}
	}

	private ResultTradePay Validate(ParametersTradePay builderParameters) {
		var prrModel = new ResultTradePay();
		if (builderParameters == null) {
			prrModel.setErrorType(0);
			prrModel.setMessageContent("ParametersTradePay参数为空.");
			return prrModel;
		}

		if (StringUtils.isEmpty(builderParameters.getOut_trade_no())) {
			prrModel.setErrorType(0);
			prrModel.setMessageContent("订单号不能为空.");
			return prrModel;
		}

		if (builderParameters.getOut_trade_no().length() > 32) {
			prrModel.setErrorType(0);
			prrModel.setMessageContent("订单号长度不能超过32个字符.");
			return prrModel;
		}

		if (StringUtils.isEmpty(builderParameters.getSubject())) {
			prrModel.setErrorType(0);
			prrModel.setMessageContent("商品标题不能为空.");
			return prrModel;

		}

		if (StringUtils.isEmpty(builderParameters.getBody())) {
			prrModel.setErrorType(0);
			prrModel.setMessageContent("商品描述不能为空.");
			return prrModel;

		}

		if (builderParameters.getBody().length() > 128) {
			prrModel.setErrorType(0);
			prrModel.setMessageContent("商品描述长度不能超过128个字符.");
			return prrModel;

		}

//		if (builderParameters.getTotal_fee() < 10 || builderParameters.getTotal_fee() > 1000000) {
//			prrModel.setErrorType(0);
//			prrModel.setMessageContent("总金额范围只能在10-1000000元之间.");
//			return prrModel;
//		}
		prrModel.setErrorType(1);
		prrModel.setMessageContent("参数合法.");
		return prrModel;

	}

	@Override
	public ResultTradeQuery TradeQuery(ParametersTradeQuery builderParameters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultTradeRefund TradeRefund(ParametersTradeRefund builderParameters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultTradeRefundQuery TradeRefundQuery(ParametersTradeRefundQuery builderParameters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultTradeClose TradeClose(ParametersTradeClose builderParameters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultTradeBillDownload TradeBillDownload(ParametersTradeBillDownload builderParameters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean TradeNotifyVerify(ParametersTradeNotifyVerify builderParameters) {
		// TODO Auto-generated method stub
		return false;
	}

}
