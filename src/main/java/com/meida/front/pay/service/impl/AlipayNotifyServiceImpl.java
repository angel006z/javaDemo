package com.meida.front.pay.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meida.front.pay.dao.inter.AlipayNotifyDao;
import com.meida.front.pay.po.AlipayNotify;
import com.meida.front.pay.service.inter.IAlipayNotifyService;

@Service
public class AlipayNotifyServiceImpl implements IAlipayNotifyService  {
	@Autowired
	private AlipayNotifyDao alipayNotifyDao;
	
	@Override
	public boolean addOrUpdate(AlipayNotify item, boolean isAdd) {
		if (isAdd) {
			return alipayNotifyDao.save(item) > 0;
		} else {
			return alipayNotifyDao.update(item) > 0;
		}
	}

	@Override
	public boolean isExistOrderNo(String orderNo) {
		return alipayNotifyDao.isExistOrderNo(orderNo)>0;
	}

}
