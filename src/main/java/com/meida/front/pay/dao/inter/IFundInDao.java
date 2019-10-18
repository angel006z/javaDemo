package com.meida.front.pay.dao.inter;

import java.io.Serializable;

import com.meida.backend.base.dao.inter.BaseDao;
import com.meida.front.pay.po.FundIn;

public interface IFundInDao extends BaseDao<FundIn> {

	long isExistOrderNo(String orderNo);

	FundIn getObjectByOrderNo(Serializable orderNo);
}
