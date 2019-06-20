package com.meida.front.pay.dao.inter;

import java.io.Serializable;

import com.meida.base.dao.inter.IBaseDao;
import com.meida.front.pay.domain.po.AlipayNotify;
import com.meida.front.pay.domain.po.MemberFundOut;

public interface IAlipayNotifyDao extends IBaseDao<AlipayNotify> {
	long isExistOrderNo(String orderNo);

	AlipayNotify getObjectByOrderNo(Serializable orderNo);
}
