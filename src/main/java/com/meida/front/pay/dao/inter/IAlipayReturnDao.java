package com.meida.front.pay.dao.inter;

import com.meida.base.dao.inter.IBaseDao;
import com.meida.front.pay.domain.po.AlipayReturn;

import java.io.Serializable;

public interface IAlipayReturnDao extends IBaseDao<AlipayReturn> {
	long isExistOrderNo(String orderNo);

	AlipayReturn getObjectByOrderNo(Serializable orderNo);
}
