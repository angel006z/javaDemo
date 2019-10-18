package com.meida.front.pay.dao.inter;

import com.meida.backend.base.dao.inter.BaseDao;
import com.meida.front.pay.po.FundAmount;

public interface AccountAmountDao extends BaseDao<FundAmount> {

	FundAmount getObjectByMemberId(Long memberId);

	long isExistMemberId(Long memberId);

	int updateByMemberId(FundAmount fundAmount);
}
