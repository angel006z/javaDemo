package com.meida.front.pay.dao.inter;

import java.io.Serializable;
import java.util.List;

import com.meida.base.dao.inter.IBaseDao;
import com.meida.front.pay.domain.po.FundCharge;
import com.meida.front.pay.domain.vo.FundChargeVo;

public interface IFundChargeDao extends IBaseDao<FundCharge> {

	List<FundChargeVo> getListWhereByPage(Object item);

	long isExistOrderNo(String orderNo);

	FundCharge getObjectByOrderNo(Serializable orderNo);

	int updateByOrderNo(FundCharge item);
}
