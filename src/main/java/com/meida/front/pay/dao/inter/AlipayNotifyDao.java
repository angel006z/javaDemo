package com.meida.front.pay.dao.inter;

import java.io.Serializable;

import com.meida.backend.base.dao.inter.BaseDao;
import com.meida.front.pay.po.AlipayNotify;

public interface AlipayNotifyDao extends BaseDao<AlipayNotify> {
	long isExistOrderNo(String orderNo);

	AlipayNotify getObjectByOrderNo(Serializable orderNo);
}
