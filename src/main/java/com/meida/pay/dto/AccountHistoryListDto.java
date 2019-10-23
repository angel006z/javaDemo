package com.meida.pay.dto;

import com.meida.basefront.dto.CurrentMemberDto;
import com.meida.base.vo.Pagination;

import java.util.Date;
import java.math.BigDecimal;

/**
 * AccountHistoryListDto
 * 
 * @author BING
 * @date 2019-10-19 15:27:58
 */
public class AccountHistoryListDto{
	private static final long serialVersionUID=1L;

    private CurrentMemberDto currentMemberDto;

    private Pagination pagination;

						/**
	 * 收付款号
	 */
	private String inOutNo;
					/**
	 * 会员id
	 */
	private Long memberId;
					/**
	 * 订单号
	 */
	private String orderNo;
					/**
	 * 收付款金额 千亿保留2位小数，单位RMB(元)
	 */
	private BigDecimal inOutAmount;
					/**
	 * 收付款时间
	 */
	private Date inOutDate;
					/**
	 * 收付款方式:alipay、weixin、banks
	 */
	private String inOutType;
					/**
	 * 收付款方式
	 */
	private String inOutChannel;
					/**
	 * 收付款状态 yes no
	 */
	private String inOutStatus;
					/**
	 * 收款in、付款out
	 */
	private String accountHistoryType;
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
	 * 获取  收付款号
	 */
	public String getInOutNo(){
        return inOutNo;
	}

/**
	 * 设置  收付款号
	 */
	public void setInOutNo(String inOutNo){
	    this.inOutNo = inOutNo;
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
	 * 获取  收付款金额 千亿保留2位小数，单位RMB(元)
	 */
	public BigDecimal getInOutAmount(){
        return inOutAmount;
	}

/**
	 * 设置  收付款金额 千亿保留2位小数，单位RMB(元)
	 */
	public void setInOutAmount(BigDecimal inOutAmount){
	    this.inOutAmount = inOutAmount;
	}
	    			        /**
	 * 获取  收付款时间
	 */
	public Date getInOutDate(){
        return inOutDate;
	}

/**
	 * 设置  收付款时间
	 */
	public void setInOutDate(Date inOutDate){
	    this.inOutDate = inOutDate;
	}
	    			        /**
	 * 获取  收付款方式:alipay、weixin、banks
	 */
	public String getInOutType(){
        return inOutType;
	}

/**
	 * 设置  收付款方式:alipay、weixin、banks
	 */
	public void setInOutType(String inOutType){
	    this.inOutType = inOutType;
	}
	    			        /**
	 * 获取  收付款方式
	 */
	public String getInOutChannel(){
        return inOutChannel;
	}

/**
	 * 设置  收付款方式
	 */
	public void setInOutChannel(String inOutChannel){
	    this.inOutChannel = inOutChannel;
	}
	    			        /**
	 * 获取  收付款状态 yes no
	 */
	public String getInOutStatus(){
        return inOutStatus;
	}

/**
	 * 设置  收付款状态 yes no
	 */
	public void setInOutStatus(String inOutStatus){
	    this.inOutStatus = inOutStatus;
	}
	    			        /**
	 * 获取  收款in、付款out
	 */
	public String getAccountHistoryType(){
        return accountHistoryType;
	}

/**
	 * 设置  收款in、付款out
	 */
	public void setAccountHistoryType(String accountHistoryType){
	    this.accountHistoryType = accountHistoryType;
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
	    			

    public CurrentMemberDto getCurrentMemberDto() {
        return currentMemberDto;
    }

    public void setCurrentMemberDto(CurrentMemberDto currentMemberDto) {
        this.currentMemberDto = currentMemberDto;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

}
