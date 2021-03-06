package com.meida.paysdk.service.impl;

import com.meida.base.vo.ResultMessage;
import com.meida.common.util.JsonUtils;
import com.meida.common.util.StringUtils;
import com.meida.common.constant.EErrorCode;
import com.meida.paysdk.alipay.page.pojo.*;
import com.meida.paysdk.alipay.page.service.impl.AlipayPageTradeServiceImpl;
import com.meida.paysdk.pojo.*;
import com.meida.paysdk.service.inter.ITradeService;
import com.meida.paysdk.weixin.wxnative.pojo.WxnativeParametersTradePay;
import com.meida.paysdk.weixin.wxnative.pojo.WxnativeResultTradePay;
import com.meida.paysdk.weixin.wxnative.service.impl.WeixinWxnativeTradeServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

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
            if (builderParameters.getPayChannel().equals(EPayChannel.Alipay_PAGE)) {
                System.out.println("Alipay_PAGE");
                AlipayPageParametersTradePay builder = new AlipayPageParametersTradePay();
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
                    AlipayPageTradeServiceImpl tradeServiceImpl = new AlipayPageTradeServiceImpl();
                    AlipayPageResultTradePay result = tradeServiceImpl.tradePay(builder);
                    ResultTradePay resultTradePay = new ResultTradePay();
                    resultTradePay.setCode(result.getCode());
                    resultTradePay.setMessage(result.getMessage());
                    resultTradePay.setForm(result.getForm());
                    return resultTradePay;
                }
            }else if(builderParameters.getPayChannel().equals(EPayChannel.Alipay_NATIVE)){
                System.out.println("Alipay_NATIVE");
                AlipayPageParametersTradePrecreate builder = new AlipayPageParametersTradePrecreate();
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
                    AlipayPageTradeServiceImpl tradeServiceImpl = new AlipayPageTradeServiceImpl();
                    AlipayPageResultTradePrecreate result = tradeServiceImpl.tradePrecreate(builder);
                    ResultTradePay resultTradePay = new ResultTradePay();
                    resultTradePay.setCode(result.getCode());
                    resultTradePay.setMessage(result.getMessage());
                    resultTradePay.setOut_trade_no(result.getOut_trade_no());
                    resultTradePay.setQr_code(result.getQr_code());
                    return resultTradePay;
                }
            }else
            {
                ResultTradePay resultTradePay = new ResultTradePay();
                resultTradePay.setCode(EErrorCode.Error);
                resultTradePay.setMessage("暂不支持该支付宝支付方式.");
                return resultTradePay;
            }
        } else if (builderParameters.getPayType().equals(EPayType.Weixin)) {
            WxnativeParametersTradePay builder = new WxnativeParametersTradePay();
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
                WeixinWxnativeTradeServiceImpl tradeServiceImpl = new WeixinWxnativeTradeServiceImpl();
                WxnativeResultTradePay result = tradeServiceImpl.tradePay(builder);
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
    public ResultMessage tradePayIsSuccess(ParametersTradeQuery builderParameters) {
        if (builderParameters.getPayType().equals(EPayType.Alipay)) {
            AlipayPageParametersTradeQuery builder = new AlipayPageParametersTradeQuery();
            builder.setOut_trade_no(builderParameters.getOut_trade_no());
            builder.setTrade_no(builderParameters.getTrade_no());
            AlipayPageTradeServiceImpl tradeServiceImpl = new AlipayPageTradeServiceImpl();
            return tradeServiceImpl.tradePayIsSuccess(builder);

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
    public ResultTradeRefund tradeRefund(ParametersTradeRefund builderParameters) {
        if (builderParameters.getPayType().equals(EPayType.Alipay)) {
                AlipayPageParametersTradeRefund builder = new AlipayPageParametersTradeRefund();
                builder.setOut_trade_no(builderParameters.getOut_trade_no());
                builder.setTrade_no(builderParameters.getTrade_no());
                builder.setRefund_amount(builderParameters.getRefund_amount());
                builder.setRefund_reason(builderParameters.getRefund_reason());
                boolean isValidateParameters = builder.Validate();
                if (!isValidateParameters) {
                    ResultTradeRefund resultTradeRefund = new ResultTradeRefund();
                    resultTradeRefund.setCode(EErrorCode.Error);
                    resultTradeRefund.setMessage("参数不符合支付宝基本参数要求，请返回重新操作。");
                    return resultTradeRefund;
                } else {
                    AlipayPageTradeServiceImpl tradeServiceImpl = new AlipayPageTradeServiceImpl();
                    AlipayPageResultTradeRefund result = tradeServiceImpl.tradeRefund(builder);
                    ResultTradeRefund resultTradePay = new ResultTradeRefund();
                    resultTradePay.setCode(result.getCode());
                    resultTradePay.setMessage(result.getMessage());
                    return resultTradePay;
                }

//        } else if (builderParameters.getPayType().equals(EPayType.Weixin)) {
//            WxnativeParametersTradeRefund builder = new WxnativeParametersTradeRefund();
//            builder.setOut_trade_no(builderParameters.getOut_trade_no());
//            builder.setBody(builderParameters.getSubject());
//            builder.setDetail(builderParameters.getBody());
//            builder.setTotal_fee((builderParameters.getTotal_fee().multiply(new BigDecimal(100)).longValue()));
//            boolean isValidateParameters = builder.Validate();
//            if (!isValidateParameters) {
//                ResultTradeRefund resultTradeRefund = new ResultTradeRefund();
//                resultTradeRefund.setCode(EErrorCode.Error);
//                resultTradeRefund.setMessage("参数不符合微信基本参数要求，请返回重新操作。");
//                return resultTradeRefund;
//            } else {
//                WeixinWxnativeTradeServiceImpl tradeServiceImpl = new WeixinWxnativeTradeServiceImpl();
//                WxnativeResultTradePay result = tradeServiceImpl.tradePay(builder);
//                ResultTradeRefund resultTradePay = new ResultTradeRefund();
//                resultTradePay.setCode(result.getCode());
//                resultTradePay.setMessage(result.getMessage());
//                return resultTradePay;
//            }
        } else if (builderParameters.getPayType().equals(EPayType.Banks)) {
            ResultTradeRefund resultTradeRefund = new ResultTradeRefund();
            resultTradeRefund.setCode(EErrorCode.Error);
            resultTradeRefund.setMessage("暂不支持银行支付方式.");
            return resultTradeRefund;
        } else {
            ResultTradeRefund resultTradeRefund = new ResultTradeRefund();
            resultTradeRefund.setCode(EErrorCode.Error);
            resultTradeRefund.setMessage("在线支付类型（PayType）参数错误.");
            return resultTradeRefund;
        }
    }

    @Override
    public ResultMessage tradeRefundIsSuccess(ParametersTradeRefundQuery builderParameters) {

        if (builderParameters.getPayType().equals(EPayType.Alipay)) {
            AlipayPageParametersTradeFastpayRefundQuery builder =new AlipayPageParametersTradeFastpayRefundQuery();
            builder.setTrade_no(builderParameters.getTrade_no());
            builder.setOut_trade_no(builderParameters.getOut_trade_no());
            builder.setOut_request_no(builderParameters.getOut_request_no());
            AlipayPageTradeServiceImpl tradeServiceImpl = new AlipayPageTradeServiceImpl();
            return tradeServiceImpl.tradeRefundIsSuccess(builder);
        } else if (builderParameters.getPayType().equals(EPayType.Weixin)) {
            ResultMessage resultMessage =new ResultMessage();
            resultMessage.setCode(EErrorCode.Error);
            resultMessage.setMessage("暂不支持微信支付方式.");
            return resultMessage;
        } else if (builderParameters.getPayType().equals(EPayType.Banks)) {
            ResultMessage resultMessage =new ResultMessage();
            resultMessage.setCode(EErrorCode.Error);
            resultMessage.setMessage("暂不支持银行支付方式.");
            return resultMessage;
        } else {
            ResultMessage resultMessage =new ResultMessage();
            resultMessage.setCode(EErrorCode.Error);
            resultMessage.setMessage("在线支付类型（PayType）参数错误.");
            return resultMessage;
        }
    }


    @Override
    public ResultBillDownloadurlQuery billDownloadurlQuery(ParametersBillDownloadurlQuery builderParameters) {
        if (builderParameters.getPayType().equals(EPayType.Alipay)) {
            AlipayPageParametersBillDownloadurlQuery builder = new AlipayPageParametersBillDownloadurlQuery();
            builder.setBill_date(builderParameters.getBillDate());
            AlipayPageTradeServiceImpl tradeServiceImpl = new AlipayPageTradeServiceImpl();
            AlipayPageResultBillDownloadurlQuery result= tradeServiceImpl.billDownloadurlQuery(builder);
            ResultBillDownloadurlQuery resultBillDownloadurlQuery =new ResultBillDownloadurlQuery();
            resultBillDownloadurlQuery.setCode(result.getCode());
            resultBillDownloadurlQuery.setMessage(result.getMessage());
            resultBillDownloadurlQuery.setBillDownloadUrl(result.getBillDownloadUrl());
            return resultBillDownloadurlQuery;
        } else if (builderParameters.getPayType().equals(EPayType.Weixin)) {
            ResultBillDownloadurlQuery resultBillDownloadurlQuery = new ResultBillDownloadurlQuery();
            resultBillDownloadurlQuery.setCode(EErrorCode.Error);
            resultBillDownloadurlQuery.setMessage("暂不支持微信支付方式.");
            return resultBillDownloadurlQuery;
        } else if (builderParameters.getPayType().equals(EPayType.Banks)) {
            ResultBillDownloadurlQuery resultBillDownloadurlQuery = new ResultBillDownloadurlQuery();
            resultBillDownloadurlQuery.setCode(EErrorCode.Error);
            resultBillDownloadurlQuery.setMessage("暂不支持银行支付方式.");
            return resultBillDownloadurlQuery;
        } else {
            ResultBillDownloadurlQuery resultBillDownloadurlQuery = new ResultBillDownloadurlQuery();
            resultBillDownloadurlQuery.setCode(EErrorCode.Error);
            resultBillDownloadurlQuery.setMessage("在线支付类型（PayType）参数错误.");
            return resultBillDownloadurlQuery;
        }
    }

    @Override
    public boolean tradeNotifyVerify(ParametersTradeNotifyVerify builderParameters) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public ResultBillBalanceQuery billBalanceQuery(ParametersBillBalanceQuery builderParameters) {
        if (builderParameters.getPayType().equals(EPayType.Alipay)) {
            AlipayPageTradeServiceImpl tradeServiceImpl = new AlipayPageTradeServiceImpl();
            AlipayPageResultBillBalanceQuery result= tradeServiceImpl.billBalanceQuery();
            ResultBillBalanceQuery resultBillBalanceQuery =new ResultBillBalanceQuery();
            resultBillBalanceQuery.setCode(result.getCode());
            resultBillBalanceQuery.setMessage(result.getMessage());
            resultBillBalanceQuery.setTotalAmount(result.getTotalAmount());
            resultBillBalanceQuery.setAvailableAmount(result.getAvailableAmount());
            resultBillBalanceQuery.setFreezeAmount(result.getFreezeAmount());
            return resultBillBalanceQuery;
        } else if (builderParameters.getPayType().equals(EPayType.Weixin)) {
            ResultBillBalanceQuery resultBillBalanceQuery = new ResultBillBalanceQuery();
            resultBillBalanceQuery.setCode(EErrorCode.Error);
            resultBillBalanceQuery.setMessage("暂不支持微信支付方式.");
            return resultBillBalanceQuery;
        } else if (builderParameters.getPayType().equals(EPayType.Banks)) {
            ResultBillBalanceQuery resultBillBalanceQuery = new ResultBillBalanceQuery();
            resultBillBalanceQuery.setCode(EErrorCode.Error);
            resultBillBalanceQuery.setMessage("暂不支持银行支付方式.");
            return resultBillBalanceQuery;
        } else {
            ResultBillBalanceQuery resultBillDownloadurlQuery = new ResultBillBalanceQuery();
            resultBillDownloadurlQuery.setCode(EErrorCode.Error);
            resultBillDownloadurlQuery.setMessage("在线支付类型（PayType）参数错误.");
            return resultBillDownloadurlQuery;
        }
    }

}
