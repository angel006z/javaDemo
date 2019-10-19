package com.meida.front.pay.po;

import java.util.Date;

/**
 * MemberInfo
 * 
 * @author BING
 * @date 2019-10-19 15:27:58
 */
public class MemberInfo{

	private static final long serialVersionUID=1L;

	/**
	 * 会员id
	 */
	private Long memberId;
	/**
	 * 会员昵称
	 */
	private String nickname;
	/**
	 * 账号
	 */
	private String account;
	/**
	 * 手机号码
	 */
	private String telphone;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 邮件
	 */
	private String email;
	/**
	 * 微信号
	 */
	private String weixin;
	/**
	 * qq号
	 */
	private String qq;
	/**
	 * 支付宝号
	 */
	private String alipay;
	/**
	 * 微博号
	 */
	private String weibo;
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
	 * 获取  会员昵称
	 */
	public String getNickname(){
        return nickname;
	}

	/**
	 * 设置  会员昵称
	 */
	public void setNickname(String nickname){
	    this.nickname = nickname;
	}
	    	        
	/**
	 * 获取  账号
	 */
	public String getAccount(){
        return account;
	}

	/**
	 * 设置  账号
	 */
	public void setAccount(String account){
	    this.account = account;
	}
	    	        
	/**
	 * 获取  手机号码
	 */
	public String getTelphone(){
        return telphone;
	}

	/**
	 * 设置  手机号码
	 */
	public void setTelphone(String telphone){
	    this.telphone = telphone;
	}
	    	        
	/**
	 * 获取  密码
	 */
	public String getPassword(){
        return password;
	}

	/**
	 * 设置  密码
	 */
	public void setPassword(String password){
	    this.password = password;
	}
	    	        
	/**
	 * 获取  邮件
	 */
	public String getEmail(){
        return email;
	}

	/**
	 * 设置  邮件
	 */
	public void setEmail(String email){
	    this.email = email;
	}
	    	        
	/**
	 * 获取  微信号
	 */
	public String getWeixin(){
        return weixin;
	}

	/**
	 * 设置  微信号
	 */
	public void setWeixin(String weixin){
	    this.weixin = weixin;
	}
	    	        
	/**
	 * 获取  qq号
	 */
	public String getQq(){
        return qq;
	}

	/**
	 * 设置  qq号
	 */
	public void setQq(String qq){
	    this.qq = qq;
	}
	    	        
	/**
	 * 获取  支付宝号
	 */
	public String getAlipay(){
        return alipay;
	}

	/**
	 * 设置  支付宝号
	 */
	public void setAlipay(String alipay){
	    this.alipay = alipay;
	}
	    	        
	/**
	 * 获取  微博号
	 */
	public String getWeibo(){
        return weibo;
	}

	/**
	 * 设置  微博号
	 */
	public void setWeibo(String weibo){
	    this.weibo = weibo;
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
