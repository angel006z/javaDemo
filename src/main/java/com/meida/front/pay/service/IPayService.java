package com.meida.front.pay.service;

import com.meida.base.domain.vo.ResultMessage;
import com.meida.front.pay.dto.ChargeDto;

public interface IPayService {
	ResultMessage Charge(ChargeDto chargeDto);

}
