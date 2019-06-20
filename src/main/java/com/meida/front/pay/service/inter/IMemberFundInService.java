package com.meida.front.pay.service.inter;

import java.io.Serializable;

import com.meida.front.pay.domain.po.MemberFundIn;

public interface IMemberFundInService {
	
	boolean addOrUpdate(MemberFundIn item, boolean isAdd);
	
	 boolean isExistOrderNo(String orderNo);
	
	 MemberFundIn getObjectByOrderNo(Serializable orderNo);
}
