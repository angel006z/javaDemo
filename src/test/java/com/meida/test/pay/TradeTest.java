package com.meida.test.pay;

import com.meida.common.constant.EErrorCode;
import com.meida.common.util.DateUtils;
import com.meida.common.util.JsonUtils;
import com.meida.pay.alipay.page.pojo.AlipayPageParametersBillAccountlogQuery;
import com.meida.pay.alipay.page.pojo.AlipayPageResultBillAccountlogQuery;
import com.meida.pay.alipay.page.service.impl.AlipayPageTradeServiceImpl;
import com.meida.pay.pojo.*;
import com.meida.pay.service.inter.ITradeService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mvc.xml", "classpath:spring-mybatis.xml", "classpath:mybatis-config.xml",})
public class TradeTest {

    @Autowired
    private ITradeService tradeService;
    @Test
    public void test_tradeBillDownload() {
        ParametersBillDownloadurlQuery builderParameters=new ParametersBillDownloadurlQuery();
        builderParameters.setPayType(EPayType.Alipay);
        builderParameters.setPayChannel(EPayChannel.Alipay_PAGE);
        builderParameters.setBillDate(DateUtils.formatDate(DateUtils.addMonths(DateUtils.now(),-1),"yyyy-MM"));
        ResultBillDownloadurlQuery result = tradeService.billDownloadurlQuery(builderParameters);
        System.out.println("result:"+JsonUtils.toJSONString(result));
        Assert.assertEquals(result.getCode(),EErrorCode.Success);
    }

    @Test
    public void test_billAccountlogQuery() {
        AlipayPageParametersBillAccountlogQuery builderParameters=new AlipayPageParametersBillAccountlogQuery();
//        builderParameters.setPayType(EPayType.Alipay);
//        builderParameters.setPayChannel(EPayChannel.Alipay_PAGE);
        builderParameters.setStart_time(DateUtils.formatDate(DateUtils.addYears(DateUtils.now(),-1),"yyyy-MM-dd hh:mm:ss"));
        builderParameters.setEnd_time(DateUtils.formatDate(DateUtils.now(),"yyyy-MM-dd hh:mm:ss"));
        builderParameters.setPage_no("1");
        builderParameters.setPage_size("2000");
        AlipayPageResultBillAccountlogQuery result = new AlipayPageTradeServiceImpl().billAccountlogQuery(builderParameters);
        System.out.println("result:"+JsonUtils.toJSONString(result));
        Assert.assertEquals(result.getCode(),EErrorCode.Success);
    }

    @Test
    public void test_billBalanceQuery() {
        ParametersBillBalanceQuery builderParameters=new ParametersBillBalanceQuery();
        builderParameters.setPayType(EPayType.Alipay);
        builderParameters.setPayChannel(EPayChannel.Alipay_PAGE);
        ResultBillBalanceQuery result = tradeService.billBalanceQuery(builderParameters);
        System.out.println("result:"+JsonUtils.toJSONString(result));
        Assert.assertEquals(result.getCode(),EErrorCode.Success);
    }
}
