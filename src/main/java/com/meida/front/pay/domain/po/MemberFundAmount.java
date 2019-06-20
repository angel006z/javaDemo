package com.meida.front.pay.domain.po;

import java.util.Date;

public class MemberFundAmount {	   
	
      private  Long memberFundAmountId;
     
	 //会员id
      private  Long memberId;
     
	 //总金额 千亿保留2位小数，单位RMB(元)
      private  Double totalMoney;
     
	
      private  Date createDate;
     
	
      private  Date operateDate;
     
	
      private  Long operatorId;
     
	
      private  Date isValid;
     
	
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
     
      public  Double  getTotalMoney(){
      		return  this.totalMoney;
      };
      public  void  setTotalMoney(Double totalMoney){
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
     
      public  Date  getIsValid(){
      		return  this.isValid;
      };
      public  void  setIsValid(Date isValid){
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