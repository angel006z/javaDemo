package com.meida.pay.alipay.page.service.impl;

import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AccountLogItemResult;
import com.alipay.api.request.*;
import com.alipay.api.response.*;
import com.meida.base.vo.ResultMessage;
import com.meida.common.util.JsonUtils;
import com.meida.common.constant.EErrorCode;
import com.meida.pay.alipay.config.AlipayClientFactory;
import com.meida.pay.alipay.config.AlipayConfig;
import com.meida.pay.alipay.constant.AlipayRefundStatus;
import com.meida.pay.alipay.constant.AlipayTradeStatus;
import com.meida.pay.alipay.page.pojo.*;
import com.meida.pay.alipay.page.service.inter.IAlipayPageTradeService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

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
    public AlipayPageResultTradePrecreate tradePrecreate(AlipayPageParametersTradePrecreate builder) {
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
    public ResultMessage tradePayIsSuccess(AlipayPageParametersTradeQuery builder) {
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

    @Override
    public AlipayPageResultTradeRefund tradeRefund(AlipayPageParametersTradeRefund builder) {
        AlipayPageResultTradeRefund result = new AlipayPageResultTradeRefund();
        try {
            AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
            request.setBizContent(builder.GetParameters());
            AlipayTradeRefundResponse response = alipayClient.execute(request);
            if (response.isSuccess()) {
                result.setTrade_no(response.getTradeNo());
                result.setOut_trade_no(response.getOutTradeNo());
                result.setBuyer_logon_id(response.getBuyerLogonId());
                result.setFund_change(response.getFundChange());
                result.setRefund_fee(response.getRefundFee());
                result.setRefund_currency(response.getRefundCurrency());
                result.setGmt_refund_pay(response.getGmtRefundPay());
                result.setRefund_detail_item_list(JsonUtils.toJSONString(response.getRefundDetailItemList()));
                result.setStore_name(response.getStoreName());
                result.setBuyer_user_id(response.getBuyerUserId());
                result.setRefund_preset_paytool_list(JsonUtils.toJSONString(response.getRefundPresetPaytoolList()));
                result.setRefund_settlement_id(response.getRefundSettlementId());
                result.setPresent_refund_buyer_amount(response.getPresentRefundBuyerAmount());
                result.setPresent_refund_discount_amount(response.getPresentRefundDiscountAmount());
                result.setPresent_refund_mdiscount_amount(response.getPresentRefundMdiscountAmount());
                result.setCode(EErrorCode.Success);
                result.setMessage("操作成功.");
            } else {
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
    public AlipayPageResultBillDownloadurlQuery billDownloadurlQuery(AlipayPageParametersBillDownloadurlQuery builder) {
        AlipayPageResultBillDownloadurlQuery result = new AlipayPageResultBillDownloadurlQuery();
        try {
            AlipayDataDataserviceBillDownloadurlQueryRequest request = new AlipayDataDataserviceBillDownloadurlQueryRequest();
            request.setBizContent(builder.GetParameters());
            AlipayDataDataserviceBillDownloadurlQueryResponse response = alipayClient.execute(request);
            if (response.isSuccess()) {
                result.setCode(EErrorCode.Success);
                result.setMessage("查询对账单下载地址成功.");
                result.setBillDownloadUrl(response.getBillDownloadUrl());
            } else {
                result.setCode(EErrorCode.Error);
                result.setMessage("查询对账单下载地址失败.");
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
    public AlipayPageResultBillAccountlogQuery billAccountlogQuery(AlipayPageParametersBillAccountlogQuery builder) {
        AlipayPageResultBillAccountlogQuery result = new AlipayPageResultBillAccountlogQuery();
        try {
            AlipayDataBillAccountlogQueryRequest request = new AlipayDataBillAccountlogQueryRequest();
            request.setBizContent(builder.GetParameters());
            AlipayDataBillAccountlogQueryResponse response = alipayClient.execute(request);
            if (response.isSuccess()) {
                result.setCode(EErrorCode.Success);
                result.setMessage("支付宝商家账户账务明细查询成功.");
                result.setPage_no(response.getPageNo());
                result.setPage_size(response.getPageSize());
                result.setTotal_size(response.getTotalSize());
                List<AccountLogItemResult> listApiAccountLogItemResult = response.getDetailList();
                AlipayPageResultAccountLogItem accountLogItem;
                List<AlipayPageResultAccountLogItem> listAccountLogItemResult = new ArrayList<AlipayPageResultAccountLogItem>();
                for (AccountLogItemResult item : listApiAccountLogItemResult) {
                    accountLogItem = new AlipayPageResultAccountLogItem();
                    listAccountLogItemResult.add(accountLogItem);
                    accountLogItem.setTrans_dt(item.getTransDt());
                    accountLogItem.setAccount_log_id(item.getAccountLogId());
                    accountLogItem.setAlipay_order_no(item.getAlipayOrderNo());
                    accountLogItem.setMerchant_order_no(item.getMerchantOrderNo());
                    accountLogItem.setTrans_amount(item.getTransAmount());
                    accountLogItem.setBalance(item.getBalance());
                    accountLogItem.setType(item.getType());
                    accountLogItem.setOther_account(item.getOtherAccount());
                    accountLogItem.setTrans_memo(item.getTransMemo());
                    accountLogItem.setDirection(item.getDirection());
                    accountLogItem.setBill_source(item.getBillSource());
                    accountLogItem.setBiz_nos(item.getBizNos());
                    accountLogItem.setBiz_orig_no(item.getBizOrigNo());
                    accountLogItem.setBiz_desc(item.getBizDesc());
                    listAccountLogItemResult.add(accountLogItem);
                }
                result.setListAccountLogItemResult(listAccountLogItemResult);
            } else {
                result.setCode(EErrorCode.Error);
                result.setMessage("支付宝商家账户账务明细查询失败.");
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
    public AlipayPageResultBillBalanceQuery billBalanceQuery() {
        AlipayPageResultBillBalanceQuery result = new AlipayPageResultBillBalanceQuery();
        try {
            AlipayDataBillBalanceQueryRequest request = new AlipayDataBillBalanceQueryRequest();
            AlipayDataBillBalanceQueryResponse response = alipayClient.execute(request);
            if (response.isSuccess()) {
                result.setCode(EErrorCode.Success);
                result.setMessage("支付宝商家账户当前余额查询成功.");
                result.setTotalAmount(response.getTotalAmount());
                result.setAvailableAmount(response.getAvailableAmount());
                result.setFreezeAmount(response.getFreezeAmount());
            } else {
                result.setCode(EErrorCode.Error);
                result.setMessage("支付宝商家账户当前余额查询失败.");
            }
            return result;
        } catch (Exception ex) {
            logger.warn(String.format("调用支付宝接口 异常信息：%s", ex.getMessage()));
            // 请求支付过程中异常，请重新操作.
            result.setCode(EErrorCode.Error);
            result.setMessage("调用支付宝接口异常，请返回重新操作。");
            return result;
        }
    }

    @Override
    public ResultMessage tradeRefundIsSuccess(AlipayPageParametersTradeFastpayRefundQuery builder) {
        ResultMessage result = new ResultMessage();
        try {
            AlipayTradeFastpayRefundQueryRequest request = new AlipayTradeFastpayRefundQueryRequest();
            request.setBizContent(builder.GetParameters());
            AlipayTradeFastpayRefundQueryResponse response = alipayClient.execute(request);
            if (response.isSuccess()) {
                if (response.getRefundStatus().equals(AlipayRefundStatus.REFUND_SUCCESS)) {
                    result.setCode(EErrorCode.Success);
                    result.setMessage("退款成功");
                } else {
                    result.setCode(EErrorCode.Error);
                    result.setMessage("退款失败");
                }
            } else {
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
