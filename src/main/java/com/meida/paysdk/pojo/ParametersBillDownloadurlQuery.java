package com.meida.paysdk.pojo;

import com.meida.common.util.DateUtils;

public class ParametersBillDownloadurlQuery {
    public ParametersBillDownloadurlQuery() {
        payType = EPayType.Alipay;// 默认支付宝
        payChannel = EPayChannel.Alipay_PAGE;// 电脑网站
        billDate=DateUtils.formatDate(DateUtils.addMonths(DateUtils.now(),-1),"yyyy-MM");
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
     * 账单时间：日账单格式为yyyy-MM-dd，最早可下载2016年1月1日开始的日账单；
     * 月账单格式为yyyy-MM，最早可下载2016年1月开始的月账单。
     */
    private String billDate;

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

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }
}
