package com.meida.front.pay.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meida.common.util.StringUtils;
import com.meida.front.pay.dao.inter.IAlipayNotifyDao;
import com.meida.front.pay.dao.inter.IMemberFundInDao;
import com.meida.front.pay.domain.po.AlipayNotify;
import com.meida.front.pay.domain.po.MemberFundIn;
import com.meida.front.pay.service.inter.IAlipayNotifyService;
import com.meida.front.pay.service.inter.IMemberFundInService;
@Service
public class AlipayNotifyServiceImpl implements IAlipayNotifyService  {
	@Autowired
	private IAlipayNotifyDao alipayNotifyDao;
	
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
