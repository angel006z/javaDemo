package com.meida.paysdk.pojo;

public class ParametersTradeRefundQuery {
    public ParametersTradeRefundQuery() {
        payType = EPayType.Alipay;
        payChannel = EPayChannel.Alipay_PAGE;
    }
    /**
     * 在线支付类型
     */
    private String payType;
    /**
     * 详细支付方式
     */
    private String payChannel;
    /**
     * 	支付宝交易号，和商户订单号不能同时为空
     */
    private String trade_no;
    /**
     * 订单支付时传入的商户订单号,和支付宝交易号不能同时为空。 trade_no,out_trade_no如果同时存在优先取trade_no
     */
    private String out_trade_no;


    /**
     * 	请求退款接口时，传入的退款请求号，如果在退款请求时未传入，则该值为创建交易时的外部交易号
     */
    private String out_request_no;

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

    public String getTrade_no() {
        return trade_no;
    }

    public void setTrade_no(String trade_no) {
        this.trade_no = trade_no;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getOut_request_no() {
        return out_request_no;
    }

    public void setOut_request_no(String out_request_no) {
        this.out_request_no = out_request_no;
    }
}
