package com.meida.front.pay.dao.inter;

import java.io.Serializable;
import java.util.List;

import com.meida.backend.base.dao.inter.BaseDao;
import com.meida.front.pay.po.Recharge;
import com.meida.front.pay.vo.RechargeVo;

public interface AccountRechargeDao extends BaseDao<Recharge> {

	List<RechargeVo> getListWhereByPage(Object item);

	long isExistOrderNo(String orderNo);

	Recharge getObjectByOrderNo(Serializable orderNo);

	int updateByOrderNo(Recharge item);
}
