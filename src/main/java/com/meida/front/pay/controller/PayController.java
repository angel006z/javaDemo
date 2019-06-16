package com.meida.front.pay.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.meida.common.util.JsonUtils;
import com.meida.front.pay.dto.ChargeDto;
import com.meida.front.pay.service.IPayService;
import com.meida.pay.pojo.EPayChannel;
import com.meida.pay.pojo.EPayType;

@Controller
@RequestMapping(value = "/front/pay")
public class PayController {
	@Autowired
	private IPayService payService;
	
	@RequestMapping(value = "/index")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }
	
	@RequestMapping(value = "/confirmCharge")
    @ResponseBody
	public String confirmCharge() {
		BigDecimal total_fee=new BigDecimal("1.00");
		ChargeDto chargeDto =new ChargeDto();
		chargeDto.setPayType(EPayType.Alipay);
		chargeDto.setPayChannel(EPayChannel.Alipay_PC_WEB);
		chargeDto.setTotal_fee(total_fee);
		return JsonUtils.toJSONString(payService.Charge(chargeDto)) ;
	}
}