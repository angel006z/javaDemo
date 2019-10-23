package com.meida.pay.dto;

import com.meida.basefront.dto.CurrentMemberDto;
import com.meida.base.vo.Pagination;

import java.util.Date;
import java.math.BigDecimal;

/**
 * AlipayReturnListDto
 * 支付宝同步通知
 * @author BING
 * @date 2019-10-19 15:27:58
 */
public class AlipayReturnListDto{
	private static final long serialVersionUID=1L;

    private CurrentMemberDto currentMemberDto;

    private Pagination pagination;

						/**
	 * 商户网站唯一订单号	
	 */
	private String outTradeNo;
					/**
	 * 该交易在支付宝系统中的交易流水号。最长64位。
	 */
	private String tradeNo;
					/**
	 * 	支付宝分配给开发者的应用Id。
	 */
	private String appId;
					/**
	 * 该笔订单的资金总额，单位为RMB-Yuan。取值范围为[0.01,100000000.00]，精确到小数点后两位。	
	 */
	private BigDecimal totalAmount;
					/**
	 * 收款支付宝账号对应的支付宝唯一用户号。以2088开头的纯16位数字	
	 */
	private String sellerId;
					/**
	 * 处理结果的描述，信息来自于code返回结果的描述	
	 */
	private String msg;
					/**
	 * 编码格式	
	 */
	private String charset;
					/**
	 * 时间	
	 */
	private String timestamp;
					/**
	 * 结果码	
	 */
	private String code;
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
	 * 获取  商户网站唯一订单号	
	 */
	public String getOutTradeNo(){
        return outTradeNo;
	}

/**
	 * 设置  商户网站唯一订单号	
	 */
	public void setOutTradeNo(String outTradeNo){
	    this.outTradeNo = outTradeNo;
	}
	    			        /**
	 * 获取  该交易在支付宝系统中的交易流水号。最长64位。
	 */
	public String getTradeNo(){
        return tradeNo;
	}

/**
	 * 设置  该交易在支付宝系统中的交易流水号。最长64位。
	 */
	public void setTradeNo(String tradeNo){
	    this.tradeNo = tradeNo;
	}
	    			        /**
	 * 获取  	支付宝分配给开发者的应用Id。
	 */
	public String getAppId(){
        return appId;
	}

/**
	 * 设置  	支付宝分配给开发者的应用Id。
	 */
	public void setAppId(String appId){
	    this.appId = appId;
	}
	    			        /**
	 * 获取  该笔订单的资金总额，单位为RMB-Yuan。取值范围为[0.01,100000000.00]，精确到小数点后两位。	
	 */
	public BigDecimal getTotalAmount(){
        return totalAmount;
	}

/**
	 * 设置  该笔订单的资金总额，单位为RMB-Yuan。取值范围为[0.01,100000000.00]，精确到小数点后两位。	
	 */
	public void setTotalAmount(BigDecimal totalAmount){
	    this.totalAmount = totalAmount;
	}
	    			        /**
	 * 获取  收款支付宝账号对应的支付宝唯一用户号。以2088开头的纯16位数字	
	 */
	public String getSellerId(){
        return sellerId;
	}

/**
	 * 设置  收款支付宝账号对应的支付宝唯一用户号。以2088开头的纯16位数字	
	 */
	public void setSellerId(String sellerId){
	    this.sellerId = sellerId;
	}
	    			        /**
	 * 获取  处理结果的描述，信息来自于code返回结果的描述	
	 */
	public String getMsg(){
        return msg;
	}

/**
	 * 设置  处理结果的描述，信息来自于code返回结果的描述	
	 */
	public void setMsg(String msg){
	    this.msg = msg;
	}
	    			        /**
	 * 获取  编码格式	
	 */
	public String getCharset(){
        return charset;
	}

/**
	 * 设置  编码格式	
	 */
	public void setCharset(String charset){
	    this.charset = charset;
	}
	    			        /**
	 * 获取  时间	
	 */
	public String getTimestamp(){
        return timestamp;
	}

/**
	 * 设置  时间	
	 */
	public void setTimestamp(String timestamp){
	    this.timestamp = timestamp;
	}
	    			        /**
	 * 获取  结果码	
	 */
	public String getCode(){
        return code;
	}

/**
	 * 设置  结果码	
	 */
	public void setCode(String code){
	    this.code = code;
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
