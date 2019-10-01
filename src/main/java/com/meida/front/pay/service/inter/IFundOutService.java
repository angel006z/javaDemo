package com.meida.front.pay.service.inter;

import java.io.Serializable;

import com.meida.front.pay.po.FundOut;

public interface IFundOutService {
	
	boolean addOrUpdate(FundOut item, boolean isAdd);
	
	 boolean isExistOrderNo(String orderNo);
	
	 FundOut getObjectByOrderNo(Serializable orderNo);
}
