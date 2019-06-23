package com.meida.front.pay.service.inter;

import com.meida.base.domain.vo.ResultMessage;
import com.meida.front.pay.domain.dto.BuildChargeOrderDto;

public interface IPayService {
	ResultMessage buildChargeOrder(BuildChargeOrderDto chargeDto);

}
