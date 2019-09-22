package com.meida.front.pay.dao.inter;

import java.io.Serializable;

import com.meida.base.dao.inter.IBaseDao;
import com.meida.front.pay.domain.po.FundIn;

public interface IFundInDao extends IBaseDao<FundIn> {

	long isExistOrderNo(String orderNo);

	FundIn getObjectByOrderNo(Serializable orderNo);
}
