package com.meida.pay.service.impl;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.meida.base.domain.vo.ResultMessage;
import com.meida.common.util.JsonUtils;
import com.meida.common.util.StringUtils;
import com.meida.common.util.constant.EErrorCode;
import com.meida.pay.alipay.pcweb.pojo.AlipayPcwebParametersTradePay;
import com.meida.pay.alipay.pcweb.pojo.AlipayPcwebParametersTradeQuery;
import com.meida.pay.alipay.pcweb.pojo.AlipayPcwebResultTradePay;
import com.meida.pay.alipay.pcweb.service.impl.AlipayPcwebTradeServiceImpl;
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

@Service
public class TradeServiceImpl implements ITradeService {
	private static final Logger logger = LoggerFactory.getLogger(TradeServiceImpl.class);// slf4j日志记录器

	@Override
	public ResultTradePay tradePay(ParametersTradePay builderParameters) {
		ResultTradePay validateResult = Validate(builderParameters);
		if (!validateResult.getCode().equals(EErrorCode.Success)) {
			String logMsg = String.format("ClassName：%s;MethodName：%s;MethodParameters：%s;Message:返回结果：%s",
					this.getClass().getName(), "TradePay", JsonUtils.toJSONString(builderParameters),
					JsonUtils.toJSONString(validateResult));
			logger.info(logMsg);
			return validateResult;
		}
		if (builderParameters.getPayType().equals(EPayType.Alipay)) {
			AlipayPcwebParametersTradePay builder = new AlipayPcwebParametersTradePay();
			builder.setOut_trade_no(builderParameters.getOut_trade_no());
			builder.setSubject(builderParameters.getSubject());
			builder.setBody(builderParameters.getBody());
			builder.setTotal_amount(builderParameters.getTotal_fee());
			boolean isValidateParameters = builder.Validate();
			if (!isValidateParameters) {
				ResultTradePay resultTradePay = new ResultTradePay();
				resultTradePay.setCode(EErrorCode.Error);
				resultTradePay.setMessage("参数不符合支付宝基本参数要求，请返回重新操作。");
				return resultTradePay;
			} else {
				AlipayPcwebTradeServiceImpl tradeServiceImpl = new AlipayPcwebTradeServiceImpl();
				AlipayPcwebResultTradePay result = tradeServiceImpl.tradePay(builder);
				ResultTradePay resultTradePay = new ResultTradePay();
				resultTradePay.setCode(result.getCode());
				resultTradePay.setMessage(result.getMessage());
				resultTradePay.setForm(result.getForm());

				return resultTradePay;
			}
		} else if (builderParameters.getPayType().equals(EPayType.Weixin)) {
			com.meida.pay.weixin.wxnative.pojo.ParametersTradePay builder = new com.meida.pay.weixin.wxnative.pojo.ParametersTradePay();
			builder.setOut_trade_no(builderParameters.getOut_trade_no());
			builder.setBody(builderParameters.getSubject());
			builder.setDetail(builderParameters.getBody());
			builder.setTotal_fee((builderParameters.getTotal_fee().multiply(new BigDecimal(100)).longValue()));
			boolean isValidateParameters = builder.Validate();
			if (!isValidateParameters) {
				ResultTradePay resultTradePay = new ResultTradePay();
				resultTradePay.setCode(EErrorCode.Error);
				resultTradePay.setMessage("参数不符合微信基本参数要求，请返回重新操作。");
				return resultTradePay;
			} else {
				com.meida.pay.weixin.wxnative.service.impl.WeixinTradeServiceImpl tradeServiceImpl = new com.meida.pay.weixin.wxnative.service.impl.WeixinTradeServiceImpl();
				com.meida.pay.weixin.wxnative.pojo.ResultTradePay result = tradeServiceImpl.tradePay(builder);
				ResultTradePay resultTradePay = new ResultTradePay();
				resultTradePay.setCode(result.getCode());
				resultTradePay.setMessage(result.getMessage());
				resultTradePay.setForm(result.getForm());
				return resultTradePay;
			}
		} else if (builderParameters.getPayType().equals(EPayType.Banks)) {
			ResultTradePay resultTradePay = new ResultTradePay();
			resultTradePay.setCode(EErrorCode.Error);
			resultTradePay.setMessage("暂不支持银行支付方式.");
			return resultTradePay;
		} else {
			ResultTradePay resultTradePay = new ResultTradePay();
			resultTradePay.setCode(EErrorCode.Error);
			resultTradePay.setMessage("在线支付类型（PayType）参数错误.");
			return resultTradePay;
		}
	}

	private ResultTradePay Validate(ParametersTradePay builderParameters) {
		ResultTradePay resultTradePay = new ResultTradePay();
		if (builderParameters == null) {
			resultTradePay.setCode(EErrorCode.Error);
			resultTradePay.setMessage("ParametersTradePay参数为空.");
			return resultTradePay;
		}

		if (StringUtils.isEmpty(builderParameters.getOut_trade_no())) {
			resultTradePay.setCode(EErrorCode.Error);
			resultTradePay.setMessage("订单号不能为空.");
			return resultTradePay;
		}

		if (builderParameters.getOut_trade_no().length() > 32) {
			resultTradePay.setCode(EErrorCode.Error);
			resultTradePay.setMessage("订单号长度不能超过32个字符.");
			return resultTradePay;
		}

		if (StringUtils.isEmpty(builderParameters.getSubject())) {
			resultTradePay.setCode(EErrorCode.Error);
			resultTradePay.setMessage("商品标题不能为空.");
			return resultTradePay;

		}

		if (StringUtils.isEmpty(builderParameters.getBody())) {
			resultTradePay.setCode(EErrorCode.Error);
			resultTradePay.setMessage("商品描述不能为空.");
			return resultTradePay;

		}

		if (builderParameters.getBody().length() > 128) {
			resultTradePay.setCode(EErrorCode.Error);
			resultTradePay.setMessage("商品描述长度不能超过128个字符.");
			return resultTradePay;

		}

//		if (builderParameters.getTotal_fee() < 10 || builderParameters.getTotal_fee() > 1000000) {
//			prrModel.setErrorType(0);
//			prrModel.setMessageContent("总金额范围只能在10-1000000元之间.");
//			return prrModel;
//		}
		resultTradePay.setCode(EErrorCode.Success);
		resultTradePay.setMessage("参数合法.");
		return resultTradePay;

	}

	@Override
	public ResultMessage tradeIsPaySuccess(ParametersTradeQuery builderParameters) {
		if (builderParameters.getPayType().equals(EPayType.Alipay)) {
			
			AlipayPcwebParametersTradeQuery builder = new AlipayPcwebParametersTradeQuery();
			builder.setOut_trade_no(builderParameters.getOut_trade_no());
			builder.setTrade_no(builderParameters.getTrade_no());
			com.meida.pay.alipay.pcweb.service.impl.AlipayPcwebTradeServiceImpl tradeServiceImpl = new com.meida.pay.alipay.pcweb.service.impl.AlipayPcwebTradeServiceImpl();
			return tradeServiceImpl.tradeIsPaySuccess(builder);
			
		} else if (builderParameters.getPayType().equals(EPayType.Weixin)) {
			ResultMessage resultMessage = new ResultMessage();
			resultMessage.setCode(EErrorCode.Error);
			resultMessage.setMessage("暂不支持微信支付方式.");
			return resultMessage;
		} else if (builderParameters.getPayType().equals(EPayType.Banks)) {
			ResultMessage resultMessage = new ResultMessage();
			resultMessage.setCode(EErrorCode.Error);
			resultMessage.setMessage("暂不支持银行支付方式.");
			return resultMessage;
		} else {
			ResultMessage resultMessage = new ResultMessage();
			resultMessage.setCode(EErrorCode.Error);
			resultMessage.setMessage("在线支付类型（PayType）参数错误.");
			return resultMessage;
		}
	}

	@Override
	public ResultTradeQuery tradeQuery(ParametersTradeQuery builderParameters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultTradeRefund tradeRefund(ParametersTradeRefund builderParameters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultTradeRefundQuery tradeRefundQuery(ParametersTradeRefundQuery builderParameters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultTradeClose tradeClose(ParametersTradeClose builderParameters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultTradeBillDownload tradeBillDownload(ParametersTradeBillDownload builderParameters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean tradeNotifyVerify(ParametersTradeNotifyVerify builderParameters) {
		// TODO Auto-generated method stub
		return false;
	}

}
