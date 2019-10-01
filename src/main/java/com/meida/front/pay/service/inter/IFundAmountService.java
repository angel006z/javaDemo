package com.meida.front.pay.service.inter;

import com.meida.front.pay.po.FundAmount;

public interface IFundAmountService {
	
	boolean addOrUpdate(FundAmount item, boolean isAdd);
	
}
