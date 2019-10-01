package com.meida.front.pay.service.inter;

import java.io.Serializable;

import com.meida.front.pay.po.FundIn;

public interface IFundInService {
	
	boolean addOrUpdate(FundIn item, boolean isAdd);
	
	 boolean isExistOrderNo(String orderNo);
	
	 FundIn getObjectByOrderNo(Serializable orderNo);
}
