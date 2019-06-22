package com.meida.front.pay.domain.vo;

import java.math.BigDecimal;
import java.util.Date;

public class MemberFundAmountVo {	   
	
      private  Long memberFundAmountId;
     
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
     
      
      public  Long  getMemberFundAmountId(){
      		return  this.memberFundAmountId;
      };
      public  void  setMemberFundAmountId(Long memberFundAmountId){
      	this.memberFundAmountId=memberFundAmountId;
      }  
     
      public  Long  getMemberId(){
      		return  this.memberId;
      };
      public  void  setMemberId(Long memberId){
      	this.memberId=memberId;
      }  
     
      public  BigDecimal  getTotalMoney(){
      		return  this.totalMoney;
      };
      public  void  setTotalMoney(BigDecimal totalMoney){
      	this.totalMoney=totalMoney;
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