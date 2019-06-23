package com.meida.pay.alipay.page.pojo;

import com.meida.common.util.StringUtils;

import java.math.BigDecimal;

public class AlipayPageParametersTradeRefund extends AlipayPageParametersBuilder {
    public AlipayPageParametersTradeRefund() {
        refund_currency="CNY";
        refund_reason="正常退款";
    }

    @Override
    public boolean Validate() {
        // 支付宝参数验证
        if (StringUtils.isEmpty(out_trade_no)) {
            return false;
        }
        if (!StringUtils.isEmpty(out_trade_no)) {
            if (out_trade_no.length() > 64) {
                return false;
            }
        }

        if (StringUtils.isEmpty(trade_no)) {
            return false;
        }
        if (!StringUtils.isEmpty(trade_no)) {
            if (out_trade_no.length() > 64) {
                return false;
            }
        }

        return true;
    }

    /**
     * 订单支付时传入的商户订单号,不能和 trade_no同时为空。
     */
    private String out_trade_no;

    /**
     * 支付宝交易号，和商户订单号不能同时为空
     */
    private String trade_no;

    /**
     * 需要退款的金额，该金额不能大于订单金额,单位为元，支持两位小数
     */
    private BigDecimal refund_amount;

    /**
     * 订单退款币种信息 CNY
     */
    private String refund_currency;

    /**
     * 退款的原因说明
     */
    private String refund_reason;

    /**
     * 标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传。
     */
    private String out_request_no;

    /**
     * 商户的操作员编号
     */
    private String operator_id;

    /**
     * 商户的门店编号
     */
    private String store_id;

    /**
     * 商户的终端编号
     */
    private String terminal_id;

    /**
     * 退款包含的商品列表信息，Json格式。
     * 其它说明详见：“商品明细说明”
     */
    private String goods_detail;

    /**
     * 退分账明细信息
     */
    private String refund_royalty_parameters;

    /**
     * 商户的终端编号
     */
    private String org_pid;

}
