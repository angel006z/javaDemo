package com.meida.paysdk.alipay.page.service.inter;

import com.meida.base.vo.ResultMessage;
import com.meida.paysdk.alipay.page.pojo.*;

public interface IAlipayPageTradeService {
    /**
     * 统一收单下单并支付页面接口
     * PC场景下单并支付
     * @param builder
     * @return
     */
    AlipayPageResultTradePay tradePay(AlipayPageParametersTradePay builder);

    /**
     * 统一收单线下交易预创建
     * 收银员通过收银台或商户后台调用支付宝接口，生成二维码后，展示给用户，由用户扫描二维码完成订单支付。
     * @param builder
     * @return
     */
     AlipayPageResultTradePrecreate tradePrecreate(AlipayPageParametersTradePrecreate builder);

    
    //交易查询
	//ResultTradeQuery tradeQuery(ParametersTradeQuery builder);

    /**
     * 查询支付宝支付是否已成功
     * @param builder
     * @return
     */
	ResultMessage tradePayIsSuccess(AlipayPageParametersTradeQuery builder);

    /**
     * 统一收单交易退款接口
     * 当交易发生之后一段时间内，由于买家或者卖家的原因需要退款时，
     * 卖家可以通过退款接口将支付款退还给买家，支付宝将在收到退款
     * 请求并且验证成功之后，按照退款规则将支付款按原路退到买家帐
     * 号上。 交易超过约定时间（签约时设置的可退款时间）的订单无法
     * 进行退款 支付宝退款支持单笔交易分多次退款，多次退款需要提交
     * 原支付订单的商户订单号和设置不同的退款单号。一笔退款失败后重
     * 新提交，要采用原来的退款单号。总退款金额不能超过用户实际支付金额
     * @param builder
     * @return
     */
    AlipayPageResultTradeRefund tradeRefund(AlipayPageParametersTradeRefund builder);

    /**
     * 查询对账单下载地址
     * 为方便商户快速查账，支持商户通过本接口获取商户离线账单下载地址
     * @param builder
     * @return
     */
    AlipayPageResultBillDownloadurlQuery billDownloadurlQuery(AlipayPageParametersBillDownloadurlQuery builder);

    /**
     * 支付宝商家账户账务明细查询
     * 为支付宝商家提供支付宝账户的账务流水信息，供对账使用。
     * @param builder
     * @return
     */
    AlipayPageResultBillAccountlogQuery billAccountlogQuery(AlipayPageParametersBillAccountlogQuery builder);

    /**
     * 支付宝商家账户当前余额查询
     * 为支付宝商家提供支付宝账户当前余额信息，包括冻结金额，可用余额和总额，供对账使用。
     * @return
     */
    AlipayPageResultBillBalanceQuery billBalanceQuery();

    /**
     * 查询支付宝退款是否已成功
     * @param builder
     * @return
     */
    ResultMessage tradeRefundIsSuccess(AlipayPageParametersTradeFastpayRefundQuery builder);
}
