package com.meida.front.pay.dao.inter;

import com.meida.base.dao.inter.IBaseDao;
import com.meida.front.pay.po.FundAmount;

public interface IFundAmountDao extends IBaseDao<FundAmount> {

	FundAmount getObjectByMemberId(Long memberId);

	long isExistMemberId(Long memberId);

	int updateByMemberId(FundAmount fundAmount);
}
