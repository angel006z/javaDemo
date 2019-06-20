package com.meida.front.pay.dao.inter;

import java.io.Serializable;
import java.util.List;

import com.meida.base.dao.inter.IBaseDao;
import com.meida.front.pay.domain.po.MemberFundCharge;
import com.meida.front.pay.domain.vo.MemberFundChargeVo;

public interface IMemberFundChargeDao extends IBaseDao<MemberFundCharge> {

	List<MemberFundChargeVo> getListWhereByPage(Object item);

	long isExistOrderNo(String orderNo);
	
	MemberFundCharge getObjectByOrderNo(Serializable orderNo);
	
	int updateByOrderNo(MemberFundCharge item);
}
