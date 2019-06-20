package com.meida.front.pay.dao.inter;

import java.io.Serializable;

import com.meida.base.dao.inter.IBaseDao;
import com.meida.front.pay.domain.po.MemberFundOut;

public interface IMemberFundOutDao extends IBaseDao<MemberFundOut> {

	long isExistOrderNo(String orderNo);
	
	MemberFundOut getObjectByOrderNo(Serializable orderNo);
}
