package com.meida.front.pay.dto;

public class OriginalRefundDto {
    /**
     * 订单号
     */
     private String orderNo;

    /**
     * 退款会员id
     */
    private Long refundMemberId;
    /**
     * 退款原因
     */
    private String refundReason;


    public String getRefundReason() {
        return refundReason;
    }

    public void setRefundReason(String refundReason) {
        this.refundReason = refundReason;
    }



    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Long getRefundMemberId() {
        return refundMemberId;
    }

    public void setRefundMemberId(Long refundMemberId) {
        this.refundMemberId = refundMemberId;
    }
}
