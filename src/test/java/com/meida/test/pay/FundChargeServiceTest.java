package com.meida.test.pay;

import com.meida.base.domain.vo.ResultMessage;
import com.meida.common.util.JsonUtils;
import com.meida.common.util.constant.EErrorCode;
import com.meida.front.pay.domain.dto.AlipayNotifyParamDto;
import com.meida.front.pay.service.inter.IFundChargeService;
import com.meida.front.pay.service.inter.IPayService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mvc.xml", "classpath:spring-mybatis.xml", "classpath:mybatis-config.xml",})
public class FundChargeServiceTest {
    @Autowired
    private IPayService payService;
    @Autowired
    private IFundChargeService fundChargeService;
    @Test
    public void test_handleAlipayNotify() {

        AlipayNotifyParamDto alipayNotifyParamDto = new AlipayNotifyParamDto();
        alipayNotifyParamDto.setTrade_no("支付宝交易号_"+payService.getOrderNoByCharge());
        alipayNotifyParamDto.setOut_trade_no("D2019092801341846297");
        alipayNotifyParamDto.setTotal_amount(new BigDecimal(10));
        ResultMessage resultMessage = fundChargeService.handleAlipayNotify(alipayNotifyParamDto);
        System.out.println("resultMessage:"+JsonUtils.toJSONString(resultMessage));
        Assert.assertEquals(resultMessage.getCode(),EErrorCode.Success);
    }
}
