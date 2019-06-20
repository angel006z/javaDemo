package com.meida.front.pay.service.inter;

import com.meida.front.pay.po.MemberFundCharge;

public interface IMemberFundChargeService {
	/**
	 * true:保存单条记录 false:更新单条记录
	 * 
	 * @param item
	 * @param isAdd
	 * @return
	 */
	public boolean addOrUpdate(MemberFundCharge item, boolean isAdd);
}
