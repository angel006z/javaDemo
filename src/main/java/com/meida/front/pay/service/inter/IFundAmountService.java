package com.meida.front.pay.service.inter;

import com.meida.front.pay.domain.po.FundAmount;

public interface IFundAmountService {
	
	boolean addOrUpdate(FundAmount item, boolean isAdd);
	
}
