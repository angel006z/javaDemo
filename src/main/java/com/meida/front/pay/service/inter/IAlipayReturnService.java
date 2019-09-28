package com.meida.front.pay.service.inter;

import com.meida.front.pay.domain.po.AlipayReturn;

public interface IAlipayReturnService {
	
	boolean addOrUpdate(AlipayReturn item, boolean isAdd);
	
	 boolean isExistOrderNo(String orderNo);
}
