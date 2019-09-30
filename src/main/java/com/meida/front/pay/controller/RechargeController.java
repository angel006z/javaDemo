package com.meida.front.pay.controller;

import com.meida.base.domain.vo.ResultMessage;
import com.meida.common.util.FrontUtils;
import com.meida.common.util.JsonUtils;
import com.meida.front.pay.domain.dto.BuildRechargeOrderDto;
import com.meida.front.pay.domain.dto.RechargeParamDto;
import com.meida.front.pay.service.inter.IRechargeService;
import com.meida.pay.pojo.EPayChannel;
import com.meida.pay.pojo.EPayType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/front/pay/recharge")
public class RechargeController {
    @Autowired
    private IRechargeService rechargeService;

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

    @RequestMapping(value = "/rechargeOperate")
    @ResponseBody
    public String rechargeOperate(@RequestBody RechargeParamDto paramDto) {
        String payChannel = paramDto.getPayChannel();
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

        BuildRechargeOrderDto buildChargeOrderDto = new BuildRechargeOrderDto();
        buildChargeOrderDto.setRechargeMemberId(FrontUtils.getCurrentMemberDto().getMemberId());
        buildChargeOrderDto.setPayType(payType);
        buildChargeOrderDto.setPayChannel(payChannel);
        buildChargeOrderDto.setTotal_fee(paramDto.getTotalFee());
        buildChargeOrderDto.setCurrentMemberDto(FrontUtils.getCurrentMemberDto());

        ResultMessage resultMessage = rechargeService.buildRechargeOrder(buildChargeOrderDto);
        return JsonUtils.toJSONString(resultMessage);
    }
}
