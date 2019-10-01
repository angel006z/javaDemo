package com.meida.front.pay.service.impl;

import com.meida.front.pay.dao.inter.IAlipayReturnDao;
import com.meida.front.pay.po.AlipayReturn;
import com.meida.front.pay.service.inter.IAlipayReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlipayReturnServiceImpl implements IAlipayReturnService {
	@Autowired
	private IAlipayReturnDao alipayReturnDao;
	
	@Override
	public boolean addOrUpdate(AlipayReturn item, boolean isAdd) {
		if (isAdd) {
			return alipayReturnDao.save(item) > 0;
		} else {
			return alipayReturnDao.update(item) > 0;
		}
	}

	@Override
	public boolean isExistOrderNo(String orderNo) {
		return alipayReturnDao.isExistOrderNo(orderNo)>0;
	}

}
