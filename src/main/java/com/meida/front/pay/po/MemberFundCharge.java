package com.meida.front.pay.po;

import java.math.BigDecimal;
import java.util.Date;

public class MemberFundCharge {
		
      private  Long memberFundChargeId;
     
	 //会员id
      private  Long memberId;
     
	 //订单号
      private  String orderNo;
     
	 //充值金额 千亿保留2位小数，单位RMB(元)
      private  BigDecimal chargeMoney;
     
	 //充值时间
      private  Date chargeDate;
     
	 //充值方式:alipay、weixin、banks
      private  String payType;
     
	 //充值方式
      private  String payChannel;
     
	 //是否已支付完成 yes no
      private  String isPay;
     
	 //创建时间
      private  Date createDate;
     
	 //修改时间
      private  Date operateDate;
     
	 //修改人
      private  Long operatorId;
     
	 //状态1有效
      private  Integer isValid;
     
	 //备注
      private  String remark;
     
	 //签名
      private  String signature;
     
      
      public  Long  getMemberFundChargeId(){
      		return  this.memberFundChargeId;
      };
      public  void  setMemberFundChargeId(Long memberFundChargeId){
      	this.memberFundChargeId=memberFundChargeId;
      }  
     
      public  Long  getMemberId(){
      		return  this.memberId;
      };
      public  void  setMemberId(Long memberId){
      	this.memberId=memberId;
      }  
     
      public  String  getOrderNo(){
      		return  this.orderNo;
      };
      public  void  setOrderNo(String orderNo){
      	this.orderNo=orderNo;
      }  
     
      public  BigDecimal  getChargeMoney(){
      		return  this.chargeMoney;
      };
      public  void  setChargeMoney(BigDecimal chargeMoney){
      	this.chargeMoney=chargeMoney;
      }  
     
      public  Date  getChargeDate(){
      		return  this.chargeDate;
      };
      public  void  setChargeDate(Date chargeDate){
      	this.chargeDate=chargeDate;
      }  
     
      public  String  getPayType(){
      		return  this.payType;
      };
      public  void  setPayType(String payType){
      	this.payType=payType;
      }  
     
      public  String  getPayChannel(){
      		return  this.payChannel;
      };
      public  void  setPayChannel(String payChannel){
      	this.payChannel=payChannel;
      }  
     
      public  String  getIsPay(){
      		return  this.isPay;
      };
      public  void  setIsPay(String isPay){
      	this.isPay=isPay;
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
     
      public  Long  getOperatorId(){
      		return  this.operatorId;
      };
      public  void  setOperatorId(Long operatorId){
      	this.operatorId=operatorId;
      }  
     
      public  Integer  getIsValid(){
      		return  this.isValid;
      };
      public  void  setIsValid(Integer isValid){
      	this.isValid=isValid;
      }  
     
      public  String  getRemark(){
      		return  this.remark;
      };
      public  void  setRemark(String remark){
      	this.remark=remark;
      }  
     
      public  String  getSignature(){
      		return  this.signature;
      };
      public  void  setSignature(String signature){
      	this.signature=signature;
      }  
     
    
}
