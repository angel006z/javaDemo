package com.meida.front.pay.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meida.base.domain.vo.ResultMessage;
import com.meida.common.util.DateUtils;
import com.meida.common.util.JsonUtils;
import com.meida.common.util.constant.EErrorCode;
import com.meida.front.pay.dto.ChargeDto;
import com.meida.front.pay.service.IPayService;
import com.meida.pay.pojo.EPayChannel;
import com.meida.pay.pojo.EPayType;
import com.meida.pay.pojo.ParametersTradePay;
import com.meida.pay.pojo.ResultTradePay;
import com.meida.pay.service.inter.ITradeService;

@Service
public class PayServiceImpl implements IPayService {
	@Autowired
	private ITradeService tradeService;

	@Override
	public ResultMessage charge(ChargeDto chargeDto) {

		ResultMessage resultMessage = new ResultMessage();
		String out_trade_no ="D" +DateUtils.formatDate(DateUtils.now(), "yyyyMMddHHmmsss") + DateUtils.nowTimeMillis();
		String total_fee = chargeDto.getTotal_fee()+"";
				
		//系统后台产生订单
		
		
		
		
		ParametersTradePay builderParameters = new ParametersTradePay();
		builderParameters.setPayType(chargeDto.getPayType());
		builderParameters.setPayChannel(chargeDto.getPayChannel());
		builderParameters.setOut_trade_no(out_trade_no);
		builderParameters.setSubject(total_fee+"元");
		builderParameters.setAttach(total_fee+"元");
		builderParameters.setBody(total_fee+"元");
		builderParameters.setTotal_fee(total_fee);
		ResultTradePay resultTradePay = tradeService.tradePay(builderParameters);
		
		boolean isFlag =resultTradePay.getCode().equals(EErrorCode.Success) ;
		if (isFlag) {
			resultMessage.setCode(EErrorCode.Success);
			resultMessage.setMessage(resultTradePay.getForm());
			return resultMessage;
		} else {
			resultMessage.setCode(EErrorCode.Error);
			resultMessage.setMessage(resultTradePay.getMessage());
			return resultMessage;
		}
	}
	
	

}
