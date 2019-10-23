package com.meida.pay.po;

import java.util.Date;
import java.math.BigDecimal;

/**
 * AccountReceivableInfo
 * 
 * @author BING
 * @date 2019-10-19 15:27:58
 */
public class AccountReceivableInfo{

	private static final long serialVersionUID=1L;

	/**
	 * 
	 */
	private Long accountReceivableId;
	/**
	 * 收款单号
	 */
	private String receivableNo;
	/**
	 * 会员id
	 */
	private Long memberId;
	/**
	 * 订单号
	 */
	private String orderNo;
	/**
	 * 收款时间
	 */
	private Date receivableDate;
	/**
	 * 付款金额 亿保留2位小数，单位RMB(元)
	 */
	private BigDecimal receivableAmount;
	/**
	 * 收款原因
	 */
	private String receivableReason;
	/**
	 * 收款方式
	 */
	private String receivableType;
	/**
	 * 收款方式
	 */
	private String receivableChannel;
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
	public Long getAccountReceivableId(){
        return accountReceivableId;
	}

	/**
	 * 设置  
	 */
	public void setAccountReceivableId(Long accountReceivableId){
	    this.accountReceivableId = accountReceivableId;
	}
	    	        
	/**
	 * 获取  收款单号
	 */
	public String getReceivableNo(){
        return receivableNo;
	}

	/**
	 * 设置  收款单号
	 */
	public void setReceivableNo(String receivableNo){
	    this.receivableNo = receivableNo;
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
	 * 获取  收款时间
	 */
	public Date getReceivableDate(){
        return receivableDate;
	}

	/**
	 * 设置  收款时间
	 */
	public void setReceivableDate(Date receivableDate){
	    this.receivableDate = receivableDate;
	}
	    	        
	/**
	 * 获取  付款金额 亿保留2位小数，单位RMB(元)
	 */
	public BigDecimal getReceivableAmount(){
        return receivableAmount;
	}

	/**
	 * 设置  付款金额 亿保留2位小数，单位RMB(元)
	 */
	public void setReceivableAmount(BigDecimal receivableAmount){
	    this.receivableAmount = receivableAmount;
	}
	    	        
	/**
	 * 获取  收款原因
	 */
	public String getReceivableReason(){
        return receivableReason;
	}

	/**
	 * 设置  收款原因
	 */
	public void setReceivableReason(String receivableReason){
	    this.receivableReason = receivableReason;
	}
	    	        
	/**
	 * 获取  收款方式
	 */
	public String getReceivableType(){
        return receivableType;
	}

	/**
	 * 设置  收款方式
	 */
	public void setReceivableType(String receivableType){
	    this.receivableType = receivableType;
	}
	    	        
	/**
	 * 获取  收款方式
	 */
	public String getReceivableChannel(){
        return receivableChannel;
	}

	/**
	 * 设置  收款方式
	 */
	public void setReceivableChannel(String receivableChannel){
	    this.receivableChannel = receivableChannel;
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
