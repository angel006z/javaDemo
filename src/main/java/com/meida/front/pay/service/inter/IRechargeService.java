package com.meida.front.pay.service.inter;

import java.io.Serializable;

import com.meida.base.domain.vo.ResultMessage;
import com.meida.front.pay.domain.dto.AlipayNotifyParamDto;
import com.meida.front.pay.domain.dto.AlipayReturnParamDto;
import com.meida.front.pay.domain.dto.BuildRechargeOrderDto;
import com.meida.front.pay.domain.po.Recharge;

public interface IRechargeService {
	/**
	 * true:保存单条记录 false:更新单条记录
	 * 
	 * @param item
	 * @param isAdd
	 * @return
	 */
	boolean addOrUpdate(Recharge item, boolean isAdd);
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
	
	 Recharge getObjectByOrderNo(Serializable orderNo);


	/**
	 * 构建充值订单
	 * 产生订单号，返回相应支付路径
	 */
	ResultMessage buildRechargeOrder(BuildRechargeOrderDto buildRechargeOrderDto);

	/**
	 * 充值订单号
	 * @return 返回20位订单号
	 */
	String getOrderNoByRecharge();
}
