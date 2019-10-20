package com.meida.pay.alipay.page.pojo;

import com.meida.common.util.StringUtils;

public class AlipayPageParametersBillAccountlogQuery extends AlipayPageParametersBuilder {

    @Override
    public boolean Validate() {
        if(StringUtils.isEmpty(start_time))
            return false;
        if(start_time.length()>20)
            return false;
        if(StringUtils.isEmpty(end_time))
            return false;
        if(end_time.length()>20)
            return false;
        return false;
    }

    /**
     * 账务流水创建时间的起始范围
     */
    private String start_time;
    /**
     * 账务流水创建时间的结束范围。与起始时间间隔不超过31天。查询结果为起始时间至结束时间的左闭右开区间
     */
    private String end_time;
    /**
     * 支付宝订单号，通过支付宝订单号精确查询相关的流水明细，商户订单号与支付宝订单号互斥
     */
    private String alipay_order_no;
    /**
     * 商户订单号，通过商户订单号精确查询相关的流水明细，商户订单号与支付宝订单号互斥
     */
    private String merchant_order_no;
    /**
     * 分页号，从1开始
     */
    private String page_no;
    /**
     * 分页大小1000-2000，默认2000
     */
    private String page_size;

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getAlipay_order_no() {
        return alipay_order_no;
    }

    public void setAlipay_order_no(String alipay_order_no) {
        this.alipay_order_no = alipay_order_no;
    }

    public String getMerchant_order_no() {
        return merchant_order_no;
    }

    public void setMerchant_order_no(String merchant_order_no) {
        this.merchant_order_no = merchant_order_no;
    }

    public String getPage_no() {
        return page_no;
    }

    public void setPage_no(String page_no) {
        this.page_no = page_no;
    }

    public String getPage_size() {
        return page_size;
    }

    public void setPage_size(String page_size) {
        this.page_size = page_size;
    }
}
