package com.meida.front.pay.dto;

import com.meida.backend.base.dto.BaseParamDto;
import com.meida.base.vo.Pagination;

import java.util.Date;
import java.math.BigDecimal;
/**
 * AlipayNotifyListParamDto
 * 支付宝支付成功通知
 * @author BING
 * @date 2019-10-19 15:27:58
 */
public class AlipayNotifyListParamDto extends BaseParamDto{
	private static final long serialVersionUID=1L;

    private Pagination pagination;

						/**
	 * 通知时间
	 */
	private Date notifyTime;
				/**
	 * 通知类型
	 */
	private String notifyType;
				/**
	 * 通知校验ID
	 */
	private String notifyId;
				/**
	 * 编码格式
	 */
	private String charset;
				/**
	 * 接口版本
	 */
	private String version;
				/**
	 * 签名类型
	 */
	private String signType;
				/**
	 * 签名
	 */
	private String sign;
				/**
	 * 授权方的app_id
	 */
	private String authAppId;
				/**
	 * 支付宝交易号
	 */
	private String tradeNo;
				/**
	 * 开发者的app_id
	 */
	private String appId;
				/**
	 * 商户订单号
	 */
	private String outTradeNo;
				/**
	 * 商户业务号
	 */
	private String outBizNo;
				/**
	 * 买家支付宝用户号
	 */
	private String buyerId;
				/**
	 * 卖家支付宝用户号
	 */
	private String sellerId;
				/**
	 * 交易状态
	 */
	private String tradeStatus;
				/**
	 * 订单金额
	 */
	private BigDecimal totalAmount;
				/**
	 * 实收金额
	 */
	private BigDecimal receiptAmount;
				/**
	 * 开票金额
	 */
	private BigDecimal invoiceAmount;
				/**
	 * 付款金额
	 */
	private BigDecimal buyerPayAmount;
				/**
	 * 集分宝金额
	 */
	private BigDecimal pointAmount;
				/**
	 * 总退款金额
	 */
	private BigDecimal refundFee;
				/**
	 * 订单标题
	 */
	private String subject;
				/**
	 * 商品描述
	 */
	private String body;
				/**
	 * 交易创建时间	
	 */
	private Date gmtCreate;
				/**
	 * 交易付款时间
	 */
	private Date gmtPayment;
				/**
	 * 交易退款时间	
	 */
	private Date gmtRefund;
				/**
	 * 交易结束时间	
	 */
	private Date gmtClose;
				/**
	 * 支付金额信息	
	 */
	private String fundBillList;
				/**
	 * 优惠券信息
	 */
	private String voucherDetailList;
				/**
	 * 回传参数
	 */
	private String passbackParams;
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
	 * 获取  通知时间
	 */
	public Date getNotifyTime(){
        return notifyTime;
	}

/**
	 * 设置  通知时间
	 */
	public void setNotifyTime(Date notifyTime){
	    this.notifyTime = notifyTime;
	}
	    				        
/**
	 * 获取  通知类型
	 */
	public String getNotifyType(){
        return notifyType;
	}

/**
	 * 设置  通知类型
	 */
	public void setNotifyType(String notifyType){
	    this.notifyType = notifyType;
	}
	    				        
/**
	 * 获取  通知校验ID
	 */
	public String getNotifyId(){
        return notifyId;
	}

/**
	 * 设置  通知校验ID
	 */
	public void setNotifyId(String notifyId){
	    this.notifyId = notifyId;
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
	 * 获取  接口版本
	 */
	public String getVersion(){
        return version;
	}

/**
	 * 设置  接口版本
	 */
	public void setVersion(String version){
	    this.version = version;
	}
	    				        
/**
	 * 获取  签名类型
	 */
	public String getSignType(){
        return signType;
	}

/**
	 * 设置  签名类型
	 */
	public void setSignType(String signType){
	    this.signType = signType;
	}
	    				        
/**
	 * 获取  签名
	 */
	public String getSign(){
        return sign;
	}

/**
	 * 设置  签名
	 */
	public void setSign(String sign){
	    this.sign = sign;
	}
	    				        
/**
	 * 获取  授权方的app_id
	 */
	public String getAuthAppId(){
        return authAppId;
	}

/**
	 * 设置  授权方的app_id
	 */
	public void setAuthAppId(String authAppId){
	    this.authAppId = authAppId;
	}
	    				        
/**
	 * 获取  支付宝交易号
	 */
	public String getTradeNo(){
        return tradeNo;
	}

/**
	 * 设置  支付宝交易号
	 */
	public void setTradeNo(String tradeNo){
	    this.tradeNo = tradeNo;
	}
	    				        
/**
	 * 获取  开发者的app_id
	 */
	public String getAppId(){
        return appId;
	}

/**
	 * 设置  开发者的app_id
	 */
	public void setAppId(String appId){
	    this.appId = appId;
	}
	    				        
/**
	 * 获取  商户订单号
	 */
	public String getOutTradeNo(){
        return outTradeNo;
	}

/**
	 * 设置  商户订单号
	 */
	public void setOutTradeNo(String outTradeNo){
	    this.outTradeNo = outTradeNo;
	}
	    				        
/**
	 * 获取  商户业务号
	 */
	public String getOutBizNo(){
        return outBizNo;
	}

/**
	 * 设置  商户业务号
	 */
	public void setOutBizNo(String outBizNo){
	    this.outBizNo = outBizNo;
	}
	    				        
/**
	 * 获取  买家支付宝用户号
	 */
	public String getBuyerId(){
        return buyerId;
	}

/**
	 * 设置  买家支付宝用户号
	 */
	public void setBuyerId(String buyerId){
	    this.buyerId = buyerId;
	}
	    				        
/**
	 * 获取  卖家支付宝用户号
	 */
	public String getSellerId(){
        return sellerId;
	}

/**
	 * 设置  卖家支付宝用户号
	 */
	public void setSellerId(String sellerId){
	    this.sellerId = sellerId;
	}
	    				        
/**
	 * 获取  交易状态
	 */
	public String getTradeStatus(){
        return tradeStatus;
	}

/**
	 * 设置  交易状态
	 */
	public void setTradeStatus(String tradeStatus){
	    this.tradeStatus = tradeStatus;
	}
	    				        
/**
	 * 获取  订单金额
	 */
	public BigDecimal getTotalAmount(){
        return totalAmount;
	}

/**
	 * 设置  订单金额
	 */
	public void setTotalAmount(BigDecimal totalAmount){
	    this.totalAmount = totalAmount;
	}
	    				        
/**
	 * 获取  实收金额
	 */
	public BigDecimal getReceiptAmount(){
        return receiptAmount;
	}

/**
	 * 设置  实收金额
	 */
	public void setReceiptAmount(BigDecimal receiptAmount){
	    this.receiptAmount = receiptAmount;
	}
	    				        
/**
	 * 获取  开票金额
	 */
	public BigDecimal getInvoiceAmount(){
        return invoiceAmount;
	}

/**
	 * 设置  开票金额
	 */
	public void setInvoiceAmount(BigDecimal invoiceAmount){
	    this.invoiceAmount = invoiceAmount;
	}
	    				        
/**
	 * 获取  付款金额
	 */
	public BigDecimal getBuyerPayAmount(){
        return buyerPayAmount;
	}

/**
	 * 设置  付款金额
	 */
	public void setBuyerPayAmount(BigDecimal buyerPayAmount){
	    this.buyerPayAmount = buyerPayAmount;
	}
	    				        
/**
	 * 获取  集分宝金额
	 */
	public BigDecimal getPointAmount(){
        return pointAmount;
	}

/**
	 * 设置  集分宝金额
	 */
	public void setPointAmount(BigDecimal pointAmount){
	    this.pointAmount = pointAmount;
	}
	    				        
/**
	 * 获取  总退款金额
	 */
	public BigDecimal getRefundFee(){
        return refundFee;
	}

/**
	 * 设置  总退款金额
	 */
	public void setRefundFee(BigDecimal refundFee){
	    this.refundFee = refundFee;
	}
	    				        
/**
	 * 获取  订单标题
	 */
	public String getSubject(){
        return subject;
	}

/**
	 * 设置  订单标题
	 */
	public void setSubject(String subject){
	    this.subject = subject;
	}
	    				        
/**
	 * 获取  商品描述
	 */
	public String getBody(){
        return body;
	}

/**
	 * 设置  商品描述
	 */
	public void setBody(String body){
	    this.body = body;
	}
	    				        
/**
	 * 获取  交易创建时间	
	 */
	public Date getGmtCreate(){
        return gmtCreate;
	}

/**
	 * 设置  交易创建时间	
	 */
	public void setGmtCreate(Date gmtCreate){
	    this.gmtCreate = gmtCreate;
	}
	    				        
/**
	 * 获取  交易付款时间
	 */
	public Date getGmtPayment(){
        return gmtPayment;
	}

/**
	 * 设置  交易付款时间
	 */
	public void setGmtPayment(Date gmtPayment){
	    this.gmtPayment = gmtPayment;
	}
	    				        
/**
	 * 获取  交易退款时间	
	 */
	public Date getGmtRefund(){
        return gmtRefund;
	}

/**
	 * 设置  交易退款时间	
	 */
	public void setGmtRefund(Date gmtRefund){
	    this.gmtRefund = gmtRefund;
	}
	    				        
/**
	 * 获取  交易结束时间	
	 */
	public Date getGmtClose(){
        return gmtClose;
	}

/**
	 * 设置  交易结束时间	
	 */
	public void setGmtClose(Date gmtClose){
	    this.gmtClose = gmtClose;
	}
	    				        
/**
	 * 获取  支付金额信息	
	 */
	public String getFundBillList(){
        return fundBillList;
	}

/**
	 * 设置  支付金额信息	
	 */
	public void setFundBillList(String fundBillList){
	    this.fundBillList = fundBillList;
	}
	    				        
/**
	 * 获取  优惠券信息
	 */
	public String getVoucherDetailList(){
        return voucherDetailList;
	}

/**
	 * 设置  优惠券信息
	 */
	public void setVoucherDetailList(String voucherDetailList){
	    this.voucherDetailList = voucherDetailList;
	}
	    				        
/**
	 * 获取  回传参数
	 */
	public String getPassbackParams(){
        return passbackParams;
	}

/**
	 * 设置  回传参数
	 */
	public void setPassbackParams(String passbackParams){
	    this.passbackParams = passbackParams;
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
	    		
	
    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

}
