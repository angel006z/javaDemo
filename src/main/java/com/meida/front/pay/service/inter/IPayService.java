package com.meida.front.pay.service.inter;

import com.meida.base.domain.vo.ResultMessage;
import com.meida.front.pay.dto.ChargeDto;

public interface IPayService {
	ResultMessage charge(ChargeDto chargeDto);

}
