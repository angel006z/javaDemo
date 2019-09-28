package com.meida.test.pay;

import com.meida.backend.basic.dao.inter.IDeptDao;
import com.meida.backend.basic.domain.po.Dept;
import com.meida.base.domain.vo.ResultMessage;
import com.meida.common.util.FrontUtils;
import com.meida.common.util.constant.EErrorCode;
import com.meida.front.pay.domain.dto.BuildChargeOrderDto;
import com.meida.front.pay.service.inter.IPayService;
import com.meida.pay.pojo.EPayChannel;
import com.meida.pay.pojo.EPayType;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mvc.xml", "classpath:spring-mybatis.xml", "classpath:mybatis-config.xml",})
public class PayServiceTest {
    @Autowired
    private IPayService payService;

    @org.junit.Test
    public void testBuildChargeOrder() {
        BuildChargeOrderDto buildChargeOrderDto = new BuildChargeOrderDto();
        buildChargeOrderDto.setChargeMemberId(FrontUtils.getCurrentMember().getMemberId());
        buildChargeOrderDto.setPayType(EPayType.Alipay);
        buildChargeOrderDto.setPayChannel(EPayChannel.Alipay_PAGE);
        buildChargeOrderDto.setTotal_fee(new BigDecimal(10));
        buildChargeOrderDto.setCurrentMember(FrontUtils.getCurrentMember());

        ResultMessage resultMessage = payService.buildChargeOrder(buildChargeOrderDto);
        System.out.println(resultMessage.getCode());
        System.out.println(resultMessage.getMessage());
        Assert.assertEquals(resultMessage.getCode(),EErrorCode.Success);
    }
}
