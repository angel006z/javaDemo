package com.meida.paysdk.service.inter;

import com.meida.base.vo.ResultMessage;
import com.meida.paysdk.pojo.*;

/**
 * 支付接口
 */
public interface ITradeService {
    /**
     * 交易支付
     */
    ResultTradePay tradePay(ParametersTradePay builderParameters);

    /**
     * (交易查询)是否交易成功
     */
    ResultMessage tradePayIsSuccess(ParametersTradeQuery builder);

    /**
     * 交易退款
     */
    ResultTradeRefund tradeRefund(ParametersTradeRefund builderParameters);

    /**
     * (交易退款查询)是否退款成功
     */
    ResultMessage tradeRefundIsSuccess(ParametersTradeRefundQuery builderParameters);

    /**
     * 查询对账单下载地址
     */
    ResultBillDownloadurlQuery billDownloadurlQuery(ParametersBillDownloadurlQuery builderParameters);

    /**
     * 交易验证回跳通知是否合法
     */
    boolean tradeNotifyVerify(ParametersTradeNotifyVerify builderParameters);

    /**
     * 商家账户当前余额查询
     */
    ResultBillBalanceQuery billBalanceQuery(ParametersBillBalanceQuery builderParameters);
}
