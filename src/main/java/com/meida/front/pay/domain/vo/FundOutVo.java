package com.meida.front.pay.domain.vo;

import java.util.Date;

public class FundOutVo {
	
      private  Long fundOutId;
     
	 //会员id
      private  Long memberId;
     
	 //订单号
      private  String orderNo;
     
	 //支出时间
      private  Date outDate;
     
	 //支出金额 亿保留2位小数，单位RMB(元)
      private  Double outMoney;
     
	 //支出方式
      private  String outWay;
     
	 //创建时间
      private  Date createDate;
     
	 //修改时间
      private  Date operateDate;
     
	 //修改人
      private  Long operatorId;
     
	 //状态1有效
      private  Long isValid;
     
	 //备注
      private  String remark;
     
	 //签名
      private  String signature;

    public Long getFundOutId() {
        return fundOutId;
    }

    public void setFundOutId(Long fundOutId) {
        this.fundOutId = fundOutId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Date getOutDate() {
        return outDate;
    }

    public void setOutDate(Date outDate) {
        this.outDate = outDate;
    }

    public Double getOutMoney() {
        return outMoney;
    }

    public void setOutMoney(Double outMoney) {
        this.outMoney = outMoney;
    }

    public String getOutWay() {
        return outWay;
    }

    public void setOutWay(String outWay) {
        this.outWay = outWay;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getOperateDate() {
        return operateDate;
    }

    public void setOperateDate(Date operateDate) {
        this.operateDate = operateDate;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public Long getIsValid() {
        return isValid;
    }

    public void setIsValid(Long isValid) {
        this.isValid = isValid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
