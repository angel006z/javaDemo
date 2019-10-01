package com.meida.front.pay.service.inter;

import com.meida.front.pay.po.AlipayNotify;

public interface IAlipayNotifyService {
	
	boolean addOrUpdate(AlipayNotify item, boolean isAdd);
	
	 boolean isExistOrderNo(String orderNo);
}
