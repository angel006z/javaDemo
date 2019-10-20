package com.meida.pay.pojo;

import com.meida.common.constant.EErrorCode;

public class ResultBillBalanceQuery {
    public ResultBillBalanceQuery()
    {
        code = EErrorCode.Error;
        message = "操作失败.";
    }
    private String code ;
    private String message;
    private String totalAmount;
    private String availableAmount;
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
