package com.meida.front.pay.dto;

import java.math.BigDecimal;

public class RechargeParamDto {
    private String payChannel;
    private BigDecimal totalFee;

    public String getPayChannel() {
        return payChannel;
    }

    public void setPayChannel(String payChannel) {
        this.payChannel = payChannel;
    }

    public BigDecimal getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }
}
