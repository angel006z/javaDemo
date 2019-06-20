package com.meida.front.pay.dao.inter;

import java.io.Serializable;

import com.meida.base.dao.inter.IBaseDao;
import com.meida.front.pay.domain.po.MemberFundAmount;

public interface IMemberFundAmountDao extends IBaseDao<MemberFundAmount> {
	MemberFundAmount getObjectByMemberId(Long memberId);
	long isExistMemberId(Long memberId);

	int updateByMemberId(MemberFundAmount memberFundAmount);
}
