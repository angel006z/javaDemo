package com.meida.pay.alipay.page.pojo;

import com.meida.common.util.StringUtils;

public class AlipayPageParametersBillDownloadurlQuery extends AlipayPageParametersBuilder {
    public AlipayPageParametersBillDownloadurlQuery() {
        bill_type = "signcustomer";
    }

    @Override
    public boolean Validate() {
        // 支付宝参数验证
        if (StringUtils.isEmpty(bill_type)) {
            return false;
        }
        if (bill_type.length() > 10) {
            return false;
        }
        if (!StringUtils.isEmpty(bill_date)) {
            return false;
        }
        if (bill_date.length() > 15) {
            return false;
        }
        return false;
    }

    /**
     * 账单类型，商户通过接口或商户经开放平台授权后其所属服务商通过接口可以获取以下账单类型：trade、signcustomer；
     * trade指商户基于支付宝交易收单的业务账单；
     * signcustomer是指基于商户支付宝余额收入及支出等资金变动的帐务账单。
     */
    private String bill_type;
    /**
     * 账单时间：日账单格式为yyyy-MM-dd，最早可下载2016年1月1日开始的日账单；
     * 月账单格式为yyyy-MM，最早可下载2016年1月开始的月账单。
     */
    private String bill_date;


    public String getBill_type() {
        return bill_type;
    }

    public void setBill_type(String bill_type) {
        this.bill_type = bill_type;
    }

    public String getBill_date() {
        return bill_date;
    }

    public void setBill_date(String bill_date) {
        this.bill_date = bill_date;
    }
}
