package com.meida.paysdk.alipay.page.pojo;

import com.meida.common.util.StringUtils;

public class AlipayPageParametersTradeFastpayRefundQuery extends AlipayPageParametersBuilder {
    @Override
    public boolean Validate() {
        if (StringUtils.isEmpty(trade_no) && StringUtils.isEmpty(out_trade_no))
            return false;
        if (StringUtils.isEmpty(out_request_no))
            return false;
        if (!StringUtils.isEmpty(trade_no)) {
            if (trade_no.length() > 64)
                return false;
        }
        if (!StringUtils.isEmpty(out_trade_no)) {
            if (trade_no.length() > 64)
                return false;
        }
        if (out_request_no.length() > 64)
            return false;
        if (!StringUtils.isEmpty(org_pid)) {
            if (trade_no.length() > 16)
                return false;
        }
        return false;
    }
    /**
     * 支付宝交易号，和商户订单号不能同时为空
     */
    private String trade_no;
    /**
     * 订单支付时传入的商户订单号,和支付宝交易号不能同时为空。 trade_no,out_trade_no如果同时存在优先取trade_no
     */
    private String out_trade_no;


    /**
     * 请求退款接口时，传入的退款请求号，如果在退款请求时未传入，则该值为创建交易时的外部交易号
     */
    private String out_request_no;

    /**
     * 银行间联模式下有用，其它场景请不要使用；
     * 双联通过该参数指定需要查询的交易所属收单机构的pid;
     */
    private String org_pid;



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

    public String getOrg_pid() {
        return org_pid;
    }

    public void setOrg_pid(String org_pid) {
        this.org_pid = org_pid;
    }
}
