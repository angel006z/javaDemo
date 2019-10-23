package com.meida.test.pay;

import com.meida.base.vo.ResultMessage;
import com.meida.basefront.util.FrontUtils;
import com.meida.common.util.JsonUtils;
import com.meida.common.constant.EErrorCode;
import com.meida.pay.dto.AlipayNotifyParamDto;
import com.meida.pay.dto.BuildRechargeOrderDto;
import com.meida.pay.service.inter.AccountRechargeService;
import com.meida.paysdk.pojo.EPayChannel;
import com.meida.paysdk.pojo.EPayType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mvc.xml", "classpath:spring-mybatis.xml", "classpath:mybatis-config.xml",})
public class AccountRechargeServiceTest {
    @Autowired
    private AccountRechargeService accountRechargeService;
    @Test
    public void test_handleAlipayNotify() {

        AlipayNotifyParamDto alipayNotifyParamDto = new AlipayNotifyParamDto();
        alipayNotifyParamDto.setTrade_no("支付宝交易号_"+ accountRechargeService.getOrderNoByRecharge());
        alipayNotifyParamDto.setOut_trade_no("D2019092801341846297");
        alipayNotifyParamDto.setTotal_amount(new BigDecimal(10));
        ResultMessage resultMessage = accountRechargeService.handleAlipayNotify(alipayNotifyParamDto);
        System.out.println("resultMessage:"+JsonUtils.toJSONString(resultMessage));
        Assert.assertEquals(resultMessage.getCode(),EErrorCode.Success);
    }

    @Test
    public void test_buildChargeOrder() {
        BuildRechargeOrderDto buildChargeOrderDto = new BuildRechargeOrderDto();
        buildChargeOrderDto.setRechargeMemberId(FrontUtils.getCurrentMemberDto().getMemberId());
        buildChargeOrderDto.setPayType(EPayType.Alipay);
        buildChargeOrderDto.setPayChannel(EPayChannel.Alipay_PAGE);
        buildChargeOrderDto.setTotalFee(new BigDecimal(10));
        buildChargeOrderDto.setCurrentMemberDto(FrontUtils.getCurrentMemberDto());

        ResultMessage resultMessage = accountRechargeService.buildRechargeOrder(buildChargeOrderDto);
        System.out.println("resultMessage:"+JsonUtils.toJSONString(resultMessage));
        Assert.assertEquals(resultMessage.getCode(),EErrorCode.Success);
    }

    @Test
    public void test_getOrderNoByRecharge() {
        for (int i=0 ;i<100;i++)
        {
            System.out.println( accountRechargeService.getOrderNoByRecharge());
        }
    }


}
