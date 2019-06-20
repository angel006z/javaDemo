package com.meida.front.pay.service.inter;

import java.io.Serializable;

import com.meida.front.pay.domain.po.MemberFundAmount;

public interface IMemberFundAmountService {
	
	boolean addOrUpdate(MemberFundAmount item, boolean isAdd);
	
}
