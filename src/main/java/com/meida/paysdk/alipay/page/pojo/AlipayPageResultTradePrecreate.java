package com.meida.paysdk.alipay.page.pojo;

import com.meida.common.constant.EErrorCode;

public class AlipayPageResultTradePrecreate {
    public AlipayPageResultTradePrecreate() {
        code = EErrorCode.Error;
        message = "操作失败.";
    }

    private String code;
    private String message;
    private String out_trade_no;
    private String qr_code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getQr_code() {
        return qr_code;
    }

    public void setQr_code(String qr_code) {
        this.qr_code = qr_code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}