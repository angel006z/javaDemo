package com.meida.pay.alipay.page.pojo;

import com.meida.common.constant.EErrorCode;

import java.math.BigDecimal;

public class AlipayPageResultBillBalanceQuery {
    public AlipayPageResultBillBalanceQuery() {
        code = EErrorCode.Error;
        message = "操作失败.";
    }

    private String code;
    private String message;
    /**
     * 支付宝账户余额
     */
    private String totalAmount;
    /**
     * 账户可用余额
     */
    private String availableAmount;
    /**
     * 冻结金额
     */
    private String freezeAmount;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(String availableAmount) {
        this.availableAmount = availableAmount;
    }

    public String getFreezeAmount() {
        return freezeAmount;
    }

    public void setFreezeAmount(String freezeAmount) {
        this.freezeAmount = freezeAmount;
    }
}
