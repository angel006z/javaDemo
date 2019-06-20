package com.meida.front.pay.service.inter;

import java.io.Serializable;

import com.meida.front.pay.domain.po.AlipayNotify;
import com.meida.front.pay.domain.po.MemberFundCharge;

public interface IMemberFundChargeService {
	/**
	 * true:保存单条记录 false:更新单条记录
	 * 
	 * @param item
	 * @param isAdd
	 * @return
	 */
	boolean addOrUpdate(MemberFundCharge item, boolean isAdd);
	/**
	 * 订单号是否存在
	 * @param orderNo
	 * @return
	 */
	 boolean isExistOrderNo(String orderNo);
	
	 boolean handleAlipayTradeSuccess(AlipayNotify alipayNotify);
	
	 MemberFundCharge getObjectByOrderNo(Serializable orderNo);
}
