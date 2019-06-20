package com.meida.front.pay.domain.po;

import java.util.Date;

public class AlipayNotify {   
	
      private  Long alipayNotifyId;
     
	 //通知时间
      private  Date notify_time;
     
	 //通知类型
      private  String notify_type;
     
	 //通知校验ID
      private  String notify_id;
     
	 //编码格式
      private  String charset;
     
	 //接口版本
      private  String version;
     
	 //签名类型
      private  String sign_type;
     
	 //签名
      private  String sign;
     
	 //授权方的app_id
      private  String auth_app_id;
     
	 //支付宝交易号
      private  String trade_no;
     
	 //开发者的app_id
      private  String app_id;
     
	 //商户订单号
      private  String out_trade_no;
     
	 //商户业务号
      private  String out_biz_no;
     
	 //买家支付宝用户号
      private  String buyer_id;
     
	 //卖家支付宝用户号
      private  String seller_id;
     
	 //交易状态
      private  String trade_status;
     
	 //订单金额
      private  Double total_amount;
     
	 //实收金额
      private  Double receipt_amount;
     
	 //开票金额
      private  Double invoice_amount;
     
	 //付款金额
      private  Double buyer_pay_amount;
     
	 //集分宝金额
      private  Double point_amount;
     
	 //总退款金额
      private  Double refund_fee;
     
	 //订单标题
      private  String subject;
     
	 //商品描述
      private  String body;
     
	 //交易创建时间	
      private  Date gmt_create;
     
	 //交易付款时间
      private  Date gmt_payment;
     
	 //交易退款时间	
      private  Date gmt_refund;
     
	 //交易结束时间	
      private  Date gmt_close;
     
	 //支付金额信息	
      private  String fund_bill_list;
     
	 //优惠券信息
      private  String voucher_detail_list;
     
	 //回传参数
      private  String passback_params;
     
	 //创建时间
      private  Date createDate;
     
	 //修改时间
      private  Date operateDate;
     
	 //状态1有效
      private  Integer isValid;
     
	 //备注
      private  String remark;
     
      
      public  Long  getAlipayNotifyId(){
      		return  this.alipayNotifyId;
      };
      public  void  setAlipayNotifyId(Long alipayNotifyId){
      	this.alipayNotifyId=alipayNotifyId;
      }  
     
      public  Date  getNotify_time(){
      		return  this.notify_time;
      };
      public  void  setNotify_time(Date notify_time){
      	this.notify_time=notify_time;
      }  
     
      public  String  getNotify_type(){
      		return  this.notify_type;
      };
      public  void  setNotify_type(String notify_type){
      	this.notify_type=notify_type;
      }  
     
      public  String  getNotify_id(){
      		return  this.notify_id;
      };
      public  void  setNotify_id(String notify_id){
      	this.notify_id=notify_id;
      }  
     
      public  String  getCharset(){
      		return  this.charset;
      };
      public  void  setCharset(String charset){
      	this.charset=charset;
      }  
     
      public  String  getVersion(){
      		return  this.version;
      };
      public  void  setVersion(String version){
      	this.version=version;
      }  
     
      public  String  getSign_type(){
      		return  this.sign_type;
      };
      public  void  setSign_type(String sign_type){
      	this.sign_type=sign_type;
      }  
     
      public  String  getSign(){
      		return  this.sign;
      };
      public  void  setSign(String sign){
      	this.sign=sign;
      }  
     
      public  String  getAuth_app_id(){
      		return  this.auth_app_id;
      };
      public  void  setAuth_app_id(String auth_app_id){
      	this.auth_app_id=auth_app_id;
      }  
     
      public  String  getTrade_no(){
      		return  this.trade_no;
      };
      public  void  setTrade_no(String trade_no){
      	this.trade_no=trade_no;
      }  
     
      public  String  getApp_id(){
      		return  this.app_id;
      };
      public  void  setApp_id(String app_id){
      	this.app_id=app_id;
      }  
     
      public  String  getOut_trade_no(){
      		return  this.out_trade_no;
      };
      public  void  setOut_trade_no(String out_trade_no){
      	this.out_trade_no=out_trade_no;
      }  
     
      public  String  getOut_biz_no(){
      		return  this.out_biz_no;
      };
      public  void  setOut_biz_no(String out_biz_no){
      	this.out_biz_no=out_biz_no;
      }  
     
      public  String  getBuyer_id(){
      		return  this.buyer_id;
      };
      public  void  setBuyer_id(String buyer_id){
      	this.buyer_id=buyer_id;
      }  
     
      public  String  getSeller_id(){
      		return  this.seller_id;
      };
      public  void  setSeller_id(String seller_id){
      	this.seller_id=seller_id;
      }  
     
      public  String  getTrade_status(){
      		return  this.trade_status;
      };
      public  void  setTrade_status(String trade_status){
      	this.trade_status=trade_status;
      }  
     
      public  Double  getTotal_amount(){
      		return  this.total_amount;
      };
      public  void  setTotal_amount(Double total_amount){
      	this.total_amount=total_amount;
      }  
     
      public  Double  getReceipt_amount(){
      		return  this.receipt_amount;
      };
      public  void  setReceipt_amount(Double receipt_amount){
      	this.receipt_amount=receipt_amount;
      }  
     
      public  Double  getInvoice_amount(){
      		return  this.invoice_amount;
      };
      public  void  setInvoice_amount(Double invoice_amount){
      	this.invoice_amount=invoice_amount;
      }  
     
      public  Double  getBuyer_pay_amount(){
      		return  this.buyer_pay_amount;
      };
      public  void  setBuyer_pay_amount(Double buyer_pay_amount){
      	this.buyer_pay_amount=buyer_pay_amount;
      }  
     
      public  Double  getPoint_amount(){
      		return  this.point_amount;
      };
      public  void  setPoint_amount(Double point_amount){
      	this.point_amount=point_amount;
      }  
     
      public  Double  getRefund_fee(){
      		return  this.refund_fee;
      };
      public  void  setRefund_fee(Double refund_fee){
      	this.refund_fee=refund_fee;
      }  
     
      public  String  getSubject(){
      		return  this.subject;
      };
      public  void  setSubject(String subject){
      	this.subject=subject;
      }  
     
      public  String  getBody(){
      		return  this.body;
      };
      public  void  setBody(String body){
      	this.body=body;
      }  
     
      public  Date  getGmt_create(){
      		return  this.gmt_create;
      };
      public  void  setGmt_create(Date gmt_create){
      	this.gmt_create=gmt_create;
      }  
     
      public  Date  getGmt_payment(){
      		return  this.gmt_payment;
      };
      public  void  setGmt_payment(Date gmt_payment){
      	this.gmt_payment=gmt_payment;
      }  
     
      public  Date  getGmt_refund(){
      		return  this.gmt_refund;
      };
      public  void  setGmt_refund(Date gmt_refund){
      	this.gmt_refund=gmt_refund;
      }  
     
      public  Date  getGmt_close(){
      		return  this.gmt_close;
      };
      public  void  setGmt_close(Date gmt_close){
      	this.gmt_close=gmt_close;
      }  
     
      public  String  getFund_bill_list(){
      		return  this.fund_bill_list;
      };
      public  void  setFund_bill_list(String fund_bill_list){
      	this.fund_bill_list=fund_bill_list;
      }  
     
      public  String  getVoucher_detail_list(){
      		return  this.voucher_detail_list;
      };
      public  void  setVoucher_detail_list(String voucher_detail_list){
      	this.voucher_detail_list=voucher_detail_list;
      }  
     
      public  String  getPassback_params(){
      		return  this.passback_params;
      };
      public  void  setPassback_params(String passback_params){
      	this.passback_params=passback_params;
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
     
    
}