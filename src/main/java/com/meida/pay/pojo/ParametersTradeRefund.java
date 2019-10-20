package com.meida.pay.pojo;

import com.meida.common.util.StringUtils;

import java.math.BigDecimal;

public class ParametersTradeRefund extends ParametersBuilder {
    public ParametersTradeRefund() {
        payType = EPayType.Alipay;
        payChannel = EPayChannel.Alipay_PAGE;
        refund_reason = "正常退款";
    }

    /**
     * 在线支付类型
     */
    private String payType;
    /**
     * 详细支付方式
     */
    private String payChannel;

    private String out_trade_no;

    private String trade_no;

    private String refund_reason;

    private BigDecimal refund_amount;

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getPayChannel() {
        return payChannel;
    }

    public void setPayChannel(String payChannel) {
        this.payChannel = payChannel;
    }

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

    public String getRefund_reason() {
        return refund_reason;
    }

    public void setRefund_reason(String refund_reason) {
        this.refund_reason = refund_reason;
    }

    public BigDecimal getRefund_amount() {
        return refund_amount;
    }

    public void setRefund_amount(BigDecimal refund_amount) {
        this.refund_amount = refund_amount;
    }

    @Override
    public boolean Validate() {
        if (StringUtils.isEmpty(out_trade_no)) {
            return false;
        }
        if (StringUtils.isEmpty(trade_no)) {
            return false;
        }
        if (refund_amount.signum() <= 0) {
            return false;
        }
        return true;
    }

}
