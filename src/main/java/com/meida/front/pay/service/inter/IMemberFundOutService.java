package com.meida.front.pay.service.inter;

import java.io.Serializable;

import com.meida.front.pay.domain.po.MemberFundOut;

public interface IMemberFundOutService {
	
	boolean addOrUpdate(MemberFundOut item, boolean isAdd);
	
	 boolean isExistOrderNo(String orderNo);
	
	 MemberFundOut getObjectByOrderNo(Serializable orderNo);
}
