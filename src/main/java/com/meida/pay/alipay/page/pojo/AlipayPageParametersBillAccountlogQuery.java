package com.meida.pay.alipay.page.pojo;

public class AlipayPageParametersBillAccountlogQuery extends AlipayPageParametersBuilder {

    @Override
    public boolean Validate() {
        return false;
    }

    private String start_time;
    private String end_time;
    private String alipay_order_no;
    private String merchant_order_no;
    private String page_no;
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
