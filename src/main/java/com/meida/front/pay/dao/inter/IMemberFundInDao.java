package com.meida.front.pay.dao.inter;

import java.io.Serializable;

import com.meida.base.dao.inter.IBaseDao;
import com.meida.front.pay.domain.po.MemberFundIn;

public interface IMemberFundInDao extends IBaseDao<MemberFundIn> {

	long isExistOrderNo(String orderNo);

	MemberFundIn getObjectByOrderNo(Serializable orderNo);
}
