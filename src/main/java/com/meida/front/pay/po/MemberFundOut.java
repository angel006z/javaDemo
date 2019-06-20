package com.meida.front.pay.po;

import java.util.Date;

public class MemberFundOut {
	
      private  Long memberFundOutId;
     
	 //会员id
      private  Long memberId;
     
	 //订单号
      private  String orderNo;
     
	 //支出时间
      private  Date outDate;
     
	 //支出金额 亿保留2位小数，单位RMB(元)
      private  Double outMoney;
     
	 //支出方式
      private  String outWay;
     
	 //创建时间
      private  Date createDate;
     
	 //修改时间
      private  Date operateDate;
     
	 //修改人
      private  Long operatorId;
     
	 //状态1有效
      private  Long isValid;
     
	 //备注
      private  String remark;
     
	 //签名
      private  String signature;
     
      
      public  Long  getMemberFundOutId(){
      		return  this.memberFundOutId;
      };
      public  void  setMemberFundOutId(Long memberFundOutId){
      	this.memberFundOutId=memberFundOutId;
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
     
      public  Date  getOutDate(){
      		return  this.outDate;
      };
      public  void  setOutDate(Date outDate){
      	this.outDate=outDate;
      }  
     
      public  Double  getOutMoney(){
      		return  this.outMoney;
      };
      public  void  setOutMoney(Double outMoney){
      	this.outMoney=outMoney;
      }  
     
      public  String  getOutWay(){
      		return  this.outWay;
      };
      public  void  setOutWay(String outWay){
      	this.outWay=outWay;
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
     
      public  String  getSignature(){
      		return  this.signature;
      };
      public  void  setSignature(String signature){
      	this.signature=signature;
      }  
     
    
}
