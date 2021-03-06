package com.meida.test.pay;

import com.meida.base.vo.ResultMessage;
import com.meida.basefront.util.FrontUtils;
import com.meida.common.util.JsonUtils;
import com.meida.common.constant.EErrorCode;
import com.meida.pay.dto.OriginalRefundDto;
import com.meida.pay.service.inter.AccountRefundService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mvc.xml", "classpath:spring-mybatis.xml", "classpath:mybatis-config.xml",})
public class AccountRefundServiceTest {
    @Autowired
    private AccountRefundService accountRefundService;

    @Test
    public void test_originalRefund() {
        OriginalRefundDto originalRefundDto = new OriginalRefundDto();
        originalRefundDto.setRefundMemberId(FrontUtils.getCurrentMemberDto().getMemberId());
        originalRefundDto.setOrderNo("D2019092800666483187");
        originalRefundDto.setRefundReason("测试退款");
        ResultMessage resultMessage = accountRefundService.originalRefund(originalRefundDto);
        System.out.println("resultMessage:"+JsonUtils.toJSONString(resultMessage));
        Assert.assertEquals(resultMessage.getCode(),EErrorCode.Success);
    }
}
