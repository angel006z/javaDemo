package com.meida.front.pay.service.inter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meida.front.pay.dao.inter.IMemberFundChargeDao;
import com.meida.front.pay.po.MemberFundCharge;
import com.meida.front.pay.service.inter.IMemberFundChargeService;
@Service
public class MemberFundChargeServiceImpl implements IMemberFundChargeService  {
	@Autowired
	private IMemberFundChargeDao memberFundChargeDao;
	
	@Override
	public boolean addOrUpdate(MemberFundCharge item, boolean isAdd) {
		if (isAdd) {
			return memberFundChargeDao.save(item) > 0;
		} else {
			return memberFundChargeDao.update(item) > 0;
		}
	}

}
