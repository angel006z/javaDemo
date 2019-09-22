package com.meida.front.pay.dao.inter;

import java.io.Serializable;

import com.meida.base.dao.inter.IBaseDao;
import com.meida.front.pay.domain.po.FundOut;

public interface IFundOutDao extends IBaseDao<FundOut> {

	long isExistOrderNo(String orderNo);

	FundOut getObjectByOrderNo(Serializable orderNo);
}
