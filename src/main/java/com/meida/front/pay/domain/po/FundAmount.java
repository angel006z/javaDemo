package com.meida.front.pay.domain.po;

import java.math.BigDecimal;
import java.util.Date;

public class FundAmount {
	
      private  Long fundAmountId;
     
	 //会员id
      private  Long memberId;
     
	 //总金额 千亿保留2位小数，单位RMB(元)
      private  BigDecimal totalMoney;
     
	
      private  Date createDate;
     
	
      private  Date operateDate;
     
	
      private  Long operatorId;
     
	
      private  Integer isValid;
     
	
      private  String remark;
     
	
      private  String signature;


    public Long getFundAmountId() {
        return fundAmountId;
    }

    public void setFundAmountId(Long fundAmountId) {
        this.fundAmountId = fundAmountId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
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

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
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