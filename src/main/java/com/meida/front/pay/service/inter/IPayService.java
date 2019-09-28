package com.meida.front.pay.service.inter;

import com.meida.base.domain.vo.ResultMessage;
import com.meida.front.pay.domain.dto.BuildChargeOrderDto;

public interface IPayService {
	/**
	 * 构建充值订单
	 * 产生订单号，返回相应支付路径
	 */
	ResultMessage buildChargeOrder(BuildChargeOrderDto chargeDto);

	/**
	 * 订单号
	 *
	 * @return 返回20位订单号
	 */
	String getOrderNoByCharge();
}
