package com.meida.front.pay.dao.inter;

import com.meida.backend.base.dao.inter.BaseDao;
import com.meida.front.pay.po.AlipayReturn;

import java.io.Serializable;

public interface AlipayReturnDao extends BaseDao<AlipayReturn> {
	long isExistOrderNo(String orderNo);

	AlipayReturn getObjectByOrderNo(Serializable orderNo);
}
