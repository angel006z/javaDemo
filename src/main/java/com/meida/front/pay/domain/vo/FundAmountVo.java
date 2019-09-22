package com.meida.front.pay.domain.vo;

import java.math.BigDecimal;
import java.util.Date;

public class FundAmountVo {
	
      private  Long fundAmountId;
     
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
     
      

    
}