package com.meida.front.pay.domain.po;

import java.math.BigDecimal;
import java.util.Date;

public class FundAmount {
	
      private  Long fundAmountId;
     
	 //会员id
      private  Long memberId;
     
	 //总金额 千亿保留2位小数，单位RMB(元)
      private  BigDecimal totalMoney;


    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 创建人id
     */
    private String createUserId;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 修改时间
     */
    private Date updateDate;

    /**
     * 修改人id
     */
    private String updateUserId;

    /**
     * 修改人
     */
    private String updateUser;

    /**
     * 状态1有效
     */
    private Integer isValid;

    /**
     * 备注
     */
    private String remark;

    /**
     * 签名
     */
    private String signature;

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

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
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