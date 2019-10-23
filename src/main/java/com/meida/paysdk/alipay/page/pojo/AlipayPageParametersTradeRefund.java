package com.meida.paysdk.alipay.page.pojo;

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

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getTrade_no() {
        return trade_no;
    }

    public void setTrade_no(String trade_no) {
        this.trade_no = trade_no;
    }

    public BigDecimal getRefund_amount() {
        return refund_amount;
    }

    public void setRefund_amount(BigDecimal refund_amount) {
        this.refund_amount = refund_amount;
    }

    public String getRefund_currency() {
        return refund_currency;
    }

    public void setRefund_currency(String refund_currency) {
        this.refund_currency = refund_currency;
    }

    public String getRefund_reason() {
        return refund_reason;
    }

    public void setRefund_reason(String refund_reason) {
        this.refund_reason = refund_reason;
    }

    public String getOut_request_no() {
        return out_request_no;
    }

    public void setOut_request_no(String out_request_no) {
        this.out_request_no = out_request_no;
    }

    public String getOperator_id() {
        return operator_id;
    }

    public void setOperator_id(String operator_id) {
        this.operator_id = operator_id;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getTerminal_id() {
        return terminal_id;
    }

    public void setTerminal_id(String terminal_id) {
        this.terminal_id = terminal_id;
    }

    public String getGoods_detail() {
        return goods_detail;
    }

    public void setGoods_detail(String goods_detail) {
        this.goods_detail = goods_detail;
    }

    public String getRefund_royalty_parameters() {
        return refund_royalty_parameters;
    }

    public void setRefund_royalty_parameters(String refund_royalty_parameters) {
        this.refund_royalty_parameters = refund_royalty_parameters;
    }

    public String getOrg_pid() {
        return org_pid;
    }

    public void setOrg_pid(String org_pid) {
        this.org_pid = org_pid;
    }
}
