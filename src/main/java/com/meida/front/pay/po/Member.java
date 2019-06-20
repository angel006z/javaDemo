package com.meida.front.pay.po;

import java.util.Date;

public class Member {
	 //会员id
      private  Long memberId;
     
	 //会员昵称
      private  String nickname;
     
	 //账号
      private  String account;
     
	 //手机号码
      private  String telphone;
     
	 //密码
      private  String password;
     
	 //邮件
      private  String email;
     
	 //微信号
      private  String weixin;
     
	 //qq号
      private  String qq;
     
	 //支付宝号
      private  String alipay;
     
	 //微博号
      private  String weibo;
     
	 //创建时间
      private  Date createDate;
     
	 //修改时间
      private  Date operateDate;
     
	 //状态1有效
      private  Long isValid;
     
	 //备注
      private  String remark;
     
      
      public  Long  getMemberId(){
      		return  this.memberId;
      };
      public  void  setMemberId(Long memberId){
      	this.memberId=memberId;
      }  
     
      public  String  getNickname(){
      		return  this.nickname;
      };
      public  void  setNickname(String nickname){
      	this.nickname=nickname;
      }  
     
      public  String  getAccount(){
      		return  this.account;
      };
      public  void  setAccount(String account){
      	this.account=account;
      }  
     
      public  String  getTelphone(){
      		return  this.telphone;
      };
      public  void  setTelphone(String telphone){
      	this.telphone=telphone;
      }  
     
      public  String  getPassword(){
      		return  this.password;
      };
      public  void  setPassword(String password){
      	this.password=password;
      }  
     
      public  String  getEmail(){
      		return  this.email;
      };
      public  void  setEmail(String email){
      	this.email=email;
      }  
     
      public  String  getWeixin(){
      		return  this.weixin;
      };
      public  void  setWeixin(String weixin){
      	this.weixin=weixin;
      }  
     
      public  String  getQq(){
      		return  this.qq;
      };
      public  void  setQq(String qq){
      	this.qq=qq;
      }  
     
      public  String  getAlipay(){
      		return  this.alipay;
      };
      public  void  setAlipay(String alipay){
      	this.alipay=alipay;
      }  
     
      public  String  getWeibo(){
      		return  this.weibo;
      };
      public  void  setWeibo(String weibo){
      	this.weibo=weibo;
      }  
     
      public  Date  getCreateDate(){
      		return  this.createDate;
      };
      public  void  setCreateDate(Date createDate){
      	this.createDate=createDate;
      }  
     
      public  Date  getOperateDate(){
      		return  this.operateDate;
      };
      public  void  setOperateDate(Date operateDate){
      	this.operateDate=operateDate;
      }  
     
      public  Long  getIsValid(){
      		return  this.isValid;
      };
      public  void  setIsValid(Long isValid){
      	this.isValid=isValid;
      }  
     
      public  String  getRemark(){
      		return  this.remark;
      };
      public  void  setRemark(String remark){
      	this.remark=remark;
      }  
     
    
}