package com.meida.backend.pay.po;

import java.util.Date;
import java.math.BigDecimal;

/**
 * AccountRechargeInfo
 * 
 * @author BING
 * @date 2019-10-19 18:56:35
 */
public class AccountRechargeInfo{

	private static final long serialVersionUID=1L;

	/**
	 * 
	 */
	private Long accountRechargeId;
	/**
	 * 会员id
	 */
	private Long memberId;
	/**
	 * 订单号
	 */
	private String orderNo;
	/**
	 * 充值金额 千亿保留2位小数，单位RMB(元)
	 */
	private BigDecimal rechargeAmount;
	/**
	 * 充值时间
	 */
	private Date rechargeDate;
	/**
	 * 充值方式:alipay、weixin、banks
	 */
	private String rechargeType;
	/**
	 * 充值方式
	 */
	private String rechargeChannel;
	/**
	 * 充值状态 yes no
	 */
	private String rechargeStatus;
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
	 * 创建人id
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
	
	        
	/**
	 * 获取  
	 */
	public Long getAccountRechargeId(){
        return accountRechargeId;
	}

	/**
	 * 设置  
	 */
	public void setAccountRechargeId(Long accountRechargeId){
	    this.accountRechargeId = accountRechargeId;
	}
	    	        
	/**
	 * 获取  会员id
	 */
	public Long getMemberId(){
        return memberId;
	}

	/**
	 * 设置  会员id
	 */
	public void setMemberId(Long memberId){
	    this.memberId = memberId;
	}
	    	        
	/**
	 * 获取  订单号
	 */
	public String getOrderNo(){
        return orderNo;
	}

	/**
	 * 设置  订单号
	 */
	public void setOrderNo(String orderNo){
	    this.orderNo = orderNo;
	}
	    	        
	/**
	 * 获取  充值金额 千亿保留2位小数，单位RMB(元)
	 */
	public BigDecimal getRechargeAmount(){
        return rechargeAmount;
	}

	/**
	 * 设置  充值金额 千亿保留2位小数，单位RMB(元)
	 */
	public void setRechargeAmount(BigDecimal rechargeAmount){
	    this.rechargeAmount = rechargeAmount;
	}
	    	        
	/**
	 * 获取  充值时间
	 */
	public Date getRechargeDate(){
        return rechargeDate;
	}

	/**
	 * 设置  充值时间
	 */
	public void setRechargeDate(Date rechargeDate){
	    this.rechargeDate = rechargeDate;
	}
	    	        
	/**
	 * 获取  充值方式:alipay、weixin、banks
	 */
	public String getRechargeType(){
        return rechargeType;
	}

	/**
	 * 设置  充值方式:alipay、weixin、banks
	 */
	public void setRechargeType(String rechargeType){
	    this.rechargeType = rechargeType;
	}
	    	        
	/**
	 * 获取  充值方式
	 */
	public String getRechargeChannel(){
        return rechargeChannel;
	}

	/**
	 * 设置  充值方式
	 */
	public void setRechargeChannel(String rechargeChannel){
	    this.rechargeChannel = rechargeChannel;
	}
	    	        
	/**
	 * 获取  充值状态 yes no
	 */
	public String getRechargeStatus(){
        return rechargeStatus;
	}

	/**
	 * 设置  充值状态 yes no
	 */
	public void setRechargeStatus(String rechargeStatus){
	    this.rechargeStatus = rechargeStatus;
	}
	    	        
	/**
	 * 获取  创建时间
	 */
	public Date getCreateDate(){
        return createDate;
	}

	/**
	 * 设置  创建时间
	 */
	public void setCreateDate(Date createDate){
	    this.createDate = createDate;
	}
	    	        
	/**
	 * 获取  创建人id
	 */
	public String getCreateUserId(){
        return createUserId;
	}

	/**
	 * 设置  创建人id
	 */
	public void setCreateUserId(String createUserId){
	    this.createUserId = createUserId;
	}
	    	        
	/**
	 * 获取  创建人
	 */
	public String getCreateUser(){
        return createUser;
	}

	/**
	 * 设置  创建人
	 */
	public void setCreateUser(String createUser){
	    this.createUser = createUser;
	}
	    	        
	/**
	 * 获取  修改时间
	 */
	public Date getUpdateDate(){
        return updateDate;
	}

	/**
	 * 设置  修改时间
	 */
	public void setUpdateDate(Date updateDate){
	    this.updateDate = updateDate;
	}
	    	        
	/**
	 * 获取  创建人id
	 */
	public String getUpdateUserId(){
        return updateUserId;
	}

	/**
	 * 设置  创建人id
	 */
	public void setUpdateUserId(String updateUserId){
	    this.updateUserId = updateUserId;
	}
	    	        
	/**
	 * 获取  修改人
	 */
	public String getUpdateUser(){
        return updateUser;
	}

	/**
	 * 设置  修改人
	 */
	public void setUpdateUser(String updateUser){
	    this.updateUser = updateUser;
	}
	    	        
	/**
	 * 获取  状态1有效
	 */
	public Integer getIsValid(){
        return isValid;
	}

	/**
	 * 设置  状态1有效
	 */
	public void setIsValid(Integer isValid){
	    this.isValid = isValid;
	}
	    	        
	/**
	 * 获取  备注
	 */
	public String getRemark(){
        return remark;
	}

	/**
	 * 设置  备注
	 */
	public void setRemark(String remark){
	    this.remark = remark;
	}
	    	        
	/**
	 * 获取  签名
	 */
	public String getSignature(){
        return signature;
	}

	/**
	 * 设置  签名
	 */
	public void setSignature(String signature){
	    this.signature = signature;
	}
	    	
	}
