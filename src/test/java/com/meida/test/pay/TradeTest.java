package com.meida.test.pay;

import com.meida.common.constant.EErrorCode;
import com.meida.common.util.DateUtils;
import com.meida.common.util.JsonUtils;
import com.meida.pay.pojo.EPayChannel;
import com.meida.pay.pojo.EPayType;
import com.meida.pay.pojo.ParametersTradeBillDownloadurlQuery;
import com.meida.pay.pojo.ResultTradeBillDownloadurlQuery;
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
        ParametersTradeBillDownloadurlQuery builderParameters=new ParametersTradeBillDownloadurlQuery();
        builderParameters.setPayType(EPayType.Alipay);
        builderParameters.setPayChannel(EPayChannel.Alipay_PAGE);
        builderParameters.setBillDate(DateUtils.formatDate(DateUtils.addMonths(DateUtils.now(),-1),"yyyy-MM"));
        ResultTradeBillDownloadurlQuery result = tradeService.tradeBillDownloadurlQuery(builderParameters);
        System.out.println("result:"+JsonUtils.toJSONString(result));
        Assert.assertEquals(result.getCode(),EErrorCode.Success);
    }
}
