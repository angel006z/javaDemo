package com.meida.pay.alipay.page.service.impl;

import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.meida.base.domain.vo.ResultMessage;
import com.meida.common.util.JsonUtils;
import com.meida.common.util.constant.EErrorCode;
import com.meida.pay.alipay.config.AlipayClientFactory;
import com.meida.pay.alipay.config.AlipayConfig;
import com.meida.pay.alipay.constant.AlipayTradeStatus;
import com.meida.pay.alipay.page.pojo.*;
import com.meida.pay.alipay.page.service.inter.IAlipayPageTradeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AlipayPageTradeServiceImpl implements IAlipayPageTradeService {
    private static final Logger logger = LoggerFactory.getLogger(AlipayPageTradeServiceImpl.class);// slf4j日志记录器

    AlipayClient alipayClient;

    public AlipayPageTradeServiceImpl() {
        alipayClient = AlipayClientFactory.getAlipayClientInstance();
    }

    public AlipayPageResultTradePay tradePay(AlipayPageParametersTradePay builder) {
        AlipayPageResultTradePay result = new AlipayPageResultTradePay();
        try {
            AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
            request.setReturnUrl(AlipayConfig.ALIPAY_ReturnUrl);
            request.setNotifyUrl(AlipayConfig.ALIPAY_NotifyUrl);
            request.setBizContent(builder.GetParameters());
            AlipayTradePagePayResponse response = alipayClient.pageExecute(request);
            if (response.isSuccess()) {
                result.setForm(response.getBody());
                result.setCode(EErrorCode.Success);
                result.setMessage("操作成功.");
            } else {
                logger.info(String.format("调用支付宝接口失败,参数：%s,支付宝返回信息：%s",JsonUtils.toJSONString(builder),response.getMsg()));
                result.setCode(EErrorCode.Error);
                result.setMessage("调用支付宝接口失败，请返回重新操作.");
            }
            return result;
        } catch (Exception ex) {
            logger.warn(String.format("调用支付宝接口 参数信息：%s,异常信息：%s" + JsonUtils.toJSONString(builder), ex.getMessage()));
            // 请求支付过程中异常，请重新操作.
            result.setCode(EErrorCode.Error);
            result.setMessage("调用支付宝接口异常，请返回重新操作。");
            return result;
        }
    }

    @Override
    public AlipayPageResultTradePrecreate tradePay(AlipayPageParametersTradePrecreate builder) {
        AlipayPageResultTradePrecreate result = new AlipayPageResultTradePrecreate();
        try {
            AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
            request.setBizContent(builder.GetParameters());
            AlipayTradePrecreateResponse response = alipayClient.execute(request);
            if (response.isSuccess()) {
                result.setOut_trade_no(response.getOutTradeNo());
                result.setQr_code(response.getQrCode());
                result.setCode(EErrorCode.Success);
                result.setMessage("操作成功.");
            } else {
                logger.info(String.format("调用支付宝接口失败,参数：%s,支付宝返回信息：%s",JsonUtils.toJSONString(builder),response.getMsg()));
                result.setCode(EErrorCode.Error);
                result.setMessage("调用支付宝接口失败，请重新操作.");
            }
            return result;
        } catch (Exception ex) {
            logger.warn(String.format("调用支付宝接口 参数信息：%s,异常信息：%s" + JsonUtils.toJSONString(builder), ex.getMessage()));
            // 请求支付过程中异常，请重新操作.
            result.setCode(EErrorCode.Error);
            result.setMessage("调用支付宝接口异常，请返回重新操作。");
            return result;
        }
    }

    @Override
    public ResultMessage tradeIsPaySuccess(AlipayPageParametersTradeQuery builder) {
        ResultMessage result = new ResultMessage();
        try {
            AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
            request.setBizContent(builder.GetParameters());
            AlipayTradeQueryResponse response = alipayClient.execute(request);
            if (response.isSuccess()) {
                if (response.getTradeStatus().equals(AlipayTradeStatus.TRADE_SUCCESS) || response.getTradeStatus().equals(AlipayTradeStatus.TRADE_FINISHED)) {
                    result.setCode(EErrorCode.Success);
                    result.setMessage("已支付");
                } else {
                    result.setCode(EErrorCode.Error);
                    result.setMessage("未支付");
                }
            } else {
                logger.info(String.format("调用支付宝接口失败,参数：%s,支付宝返回信息：%s",JsonUtils.toJSONString(builder),response.getMsg()));
                result.setCode(EErrorCode.Error);
                result.setMessage("查询支付宝交易记录失败.");
            }
            return result;
        } catch (Exception ex) {
            logger.warn(String.format("调用支付宝接口 参数信息：%s,异常信息：%s" + JsonUtils.toJSONString(builder), ex.getMessage()));
            // 请求支付过程中异常，请重新操作.
            result.setCode(EErrorCode.Error);
            result.setMessage("调用支付宝接口异常，请返回重新操作。");
            return result;
        }
    }

}
