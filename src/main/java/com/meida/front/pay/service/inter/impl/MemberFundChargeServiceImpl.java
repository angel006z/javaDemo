package com.meida.front.pay.service.inter.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meida.common.util.StringUtils;
import com.meida.front.pay.dao.inter.IMemberFundChargeDao;
import com.meida.front.pay.domain.po.AlipayNotify;
import com.meida.front.pay.domain.po.MemberFundCharge;
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

	@Override
	public boolean isExistOrderNo(String orderNo) {		
		return memberFundChargeDao.isExistOrderNo(orderNo)>0;
	}

	@Override
	public MemberFundCharge getObjectByOrderNo(Serializable orderNo) {
		return memberFundChargeDao.getObjectByOrderNo(orderNo);
	}

	@Override
	public boolean handleAlipayTradeSuccess(AlipayNotify alipayNotify) {
	    if(alipayNotify==null)
	    	return false;
	    if(StringUtils.isEmpty(alipayNotify.getOut_trade_no()))
	    	return false;

	    
		return false;
	}

}
