package com.meida.front.pay.service.inter;

import java.io.Serializable;

import com.meida.front.pay.domain.po.AlipayNotify;

public interface IAlipayNotifyService {
	
	boolean addOrUpdate(AlipayNotify item, boolean isAdd);
	
	 boolean isExistOrderNo(String orderNo);
}
