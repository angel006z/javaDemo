package com.meida.front.pay.domain.vo;

import java.math.BigDecimal;
import java.util.Date;

public class MemberFundInVo {
	
      private  Long memberFundInId;
     
	 //会员id
      private  Long memberId;
     
	 //订单号
      private  String orderNo;
     
	 //收入时间
      private  Date inDate;
     
	 //收入金额 千亿保留2位小数，单位RMB(元)
      private  BigDecimal inMoney;
     
	 //收入方式
      private  String inWay;
     
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
     
      
      public  Long  getMemberFundInId(){
      		return  this.memberFundInId;
      };
      public  void  setMemberFundInId(Long memberFundInId){
      	this.memberFundInId=memberFundInId;
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
     
      public  Date  getInDate(){
      		return  this.inDate;
      };
      public  void  setInDate(Date inDate){
      	this.inDate=inDate;
      }  
     
      public  BigDecimal  getInMoney(){
      		return  this.inMoney;
      };
      public  void  setInMoney(BigDecimal inMoney){
      	this.inMoney=inMoney;
      }  
     
      public  String  getInWay(){
      		return  this.inWay;
      };
      public  void  setInWay(String inWay){
      	this.inWay=inWay;
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
