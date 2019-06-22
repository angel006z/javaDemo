package com.meida.front.pay.service.inter;

import java.io.Serializable;

import com.meida.base.domain.vo.ResultMessage;
import com.meida.front.pay.domain.dto.AlipayNotifyParamDto;
import com.meida.front.pay.domain.dto.AlipayReturnParamDto;
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
	 /**
	  * 处理支付宝异步通知
	  * @param alipayNotifyParamDto
	  * @return
	  */
	 ResultMessage handleAlipayNotify(AlipayNotifyParamDto alipayNotifyParamDto);
	 
	 /**
	  * 处理支付宝同步通知
	  */
	 ResultMessage handleAlipayReturn(AlipayReturnParamDto alipayReturnParamDto);
	
	 MemberFundCharge getObjectByOrderNo(Serializable orderNo);
}
