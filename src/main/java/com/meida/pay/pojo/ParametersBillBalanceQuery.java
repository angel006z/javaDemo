package com.meida.pay.pojo;

import com.meida.common.util.DateUtils;

public class ParametersBillBalanceQuery {
    public ParametersBillBalanceQuery() {
        payType = EPayType.Alipay;// 默认支付宝
        payChannel = EPayChannel.Alipay_PAGE;// 电脑网站
    }

    /**
     * 在线支付类型
     */
    private String payType;
    /**
     * 详细支付方式
     */
    private String payChannel;

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
}
