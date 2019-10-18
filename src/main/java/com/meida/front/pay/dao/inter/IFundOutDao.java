package com.meida.front.pay.dao.inter;

import java.io.Serializable;

import com.meida.backend.base.dao.inter.BaseDao;
import com.meida.front.pay.po.FundOut;

public interface IFundOutDao extends BaseDao<FundOut> {

	long isExistOrderNo(String orderNo);

	FundOut getObjectByOrderNo(Serializable orderNo);
}
