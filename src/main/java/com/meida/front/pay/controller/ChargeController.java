package com.meida.front.pay.controller;

import com.meida.base.domain.vo.ResultMessage;
import com.meida.common.util.JsonUtils;
import com.meida.common.util.RequestParameters;
import com.meida.front.pay.domain.dto.BuildChargeOrderDto;
import com.meida.front.pay.service.inter.IPayService;
import com.meida.pay.pojo.EPayChannel;
import com.meida.pay.pojo.EPayType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;

@Controller
@RequestMapping(value = "/front/pay/charge")
public class ChargeController {
	@Autowired
	private IPayService payService;

	@RequestMapping(value = "/index")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}

	@RequestMapping(value = "/jump")
	public ModelAndView jump() {
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}

	@RequestMapping(value = "/confirmCharge")
	@ResponseBody
	public String confirmCharge() {
		String payChannel = RequestParameters.getString("pay_channel");
		String payType = "other";
		if (payChannel.equals(EPayChannel.Alipay_PAGE)) {
			payType = EPayType.Alipay;
		} else if (payChannel.equals(EPayChannel.Alipay_NATIVE)) {
			payType = EPayType.Alipay;
		} else if (payChannel.equals(EPayChannel.Weixin_NATIVE)) {
			payType = EPayType.Weixin;
		} else {
			payType = "other";
			payChannel = "other";
		}

		BigDecimal total_fee = RequestParameters.getDecimal("total_fee");

		BuildChargeOrderDto buildChargeOrderDto = new BuildChargeOrderDto();
		buildChargeOrderDto.setPayType(payType);
		buildChargeOrderDto.setPayChannel(payChannel);
		buildChargeOrderDto.setTotal_fee(total_fee);
		ResultMessage resultMessage = payService.buildChargeOrder(buildChargeOrderDto);
		System.out.println(resultMessage.getMessage());
		return JsonUtils.toJSONString(resultMessage);
	}
}
