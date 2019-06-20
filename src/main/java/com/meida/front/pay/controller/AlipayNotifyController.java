package com.meida.front.pay.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.meida.common.util.JsonUtils;
import com.meida.pay.alipay.config.AlipayConfig;
import com.meida.pay.alipay.pcweb.pojo.AlipayNotifyParam;

/**
 * 服务器异步通知页面特性

必须保证服务器异步通知页面（notify_url）上无任何字符，如空格、HTML标签、开发系统自带抛出的异常提示信息等；
支付宝是用POST方式发送通知信息，因此该页面中获取参数的方式，如：request.Form(“out_trade_no”)、$_POST[‘out_trade_no’]；
支付宝主动发起通知，该方式才会被启用；
只有在支付宝的交易管理中存在该笔交易，且发生了交易状态的改变，支付宝才会通过该方式发起服务器通知（即时到账交易状态为“等待买家付款”的状态默认是不会发送通知的）；
服务器间的交互，不像页面跳转同步通知可以在页面上显示出来，这种交互方式是不可见的；
第一次交易状态改变（即时到账中此时交易状态是交易完成）时，不仅会返回同步处理结果，而且服务器异步通知页面也会收到支付宝发来的处理结果通知；
程序执行完后必须打印输出“success”（不包含引号）。如果商户反馈给支付宝的字符不是success这7个字符，支付宝服务器会不断重发通知，直到超过24小时22分钟。一般情况下，25小时以内完成8次通知（通知的间隔频率一般是：4m,10m,10m,1h,2h,6h,15h）；
程序执行完成后，该页面不能执行页面跳转。如果执行页面跳转，支付宝会收不到success字符，会被支付宝服务器判定为该页面程序运行出现异常，而重发处理结果通知；
cookies、session等在此页面会失效，即无法获取这些数据；
该方式的调试与运行必须在服务器上，即互联网上能访问；
该方式的作用主要防止订单丢失，即页面跳转同步通知没有处理订单更新，它则去处理；
当商户收到服务器异步通知并打印出success时，服务器异步通知参数notify_id才会失效。也就是说在支付宝发送同一条异步通知时（包含商户并未成功打印出success导致支付宝重发数次通知），服务器异步通知参数notify_id是不变的。
 *
 */
@Controller
@RequestMapping(value = "/front/pay/alipaynotify")
public class AlipayNotifyController {
	private static Logger logger = LoggerFactory.getLogger(AlipayNotifyController.class);
	
	
	private ExecutorService executorService = Executors.newFixedThreadPool(20);

    /**
     * <pre>
     * 第一步:验证签名,签名通过后进行第二步
     * 第二步:按一下步骤进行验证
         * 1、商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
         * 2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
         * 3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email），
         * 4、验证app_id是否为该商户本身。上述1、2、3、4有任何一个验证不通过，则表明本次通知是异常通知，务必忽略。
         * 在上述验证通过后商户必须根据支付宝不同类型的业务通知，正确的进行不同的业务处理，并且过滤重复的通知结果数据。
         * 在支付宝的业务通知中，只有交易通知状态为TRADE_SUCCESS或TRADE_FINISHED时，支付宝才会认定为买家付款成功。
     * </pre>
     * 
     * @param params
     * @return
     */
    @RequestMapping("/index")
    @ResponseBody
    public String index(HttpServletRequest request) {
        Map<String, String> params = convertRequestParamsToMap(request); // 将异步通知中收到的待验证所有参数都存放到map中
        String paramsJson = JsonUtils.toJSONString(params);
        logger.info("支付宝回调，{}", paramsJson);
        System.out.println(paramsJson);
        try {
           
            // 调用SDK验证签名
            boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY,
            		AlipayConfig.CHARSET, AlipayConfig.SIGNTYPE);
            if (signVerified) {
                logger.info("支付宝回调签名认证成功");
                // 按照支付结果异步通知中的描述，对支付结果中的业务内容进行1\2\3\4二次校验，校验成功后在response中返回success，校验失败返回failure
                this.check(params);
                // 另起线程处理业务
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        AlipayNotifyParam param = buildAlipayNotifyParam(params);
                        String trade_status = param.getTradeStatus();
//                        // 支付成功
//                        if (trade_status.equals(AlipayTradeStatus.TRADE_SUCCESS.getStatus())
//                                || trade_status.equals(AlipayTradeStatus.TRADE_FINISHED.getStatus())) {
//                            // 处理支付成功逻辑
//                            try {
//                                /*
//                                    // 处理业务逻辑。。。
//                                    myService.process(param);
//                                */                      
//
//                            } catch (Exception e) {
//                                logger.error("支付宝回调业务处理报错,params:" + paramsJson, e);
//                            }
//                        } else {
//                            logger.error("没有处理支付宝回调业务，支付宝交易状态：{},params:{}",trade_status,paramsJson);
//                        }
                    }
                });
                // 如果签名验证正确，立即返回success，后续业务另起线程单独处理
                // 业务处理失败，可查看日志进行补偿，跟支付宝已经没多大关系。
                return "success";
            } else {
                logger.info("支付宝回调签名认证失败，signVerified=false, paramsJson:{}", paramsJson);
                return "failure";
            }
        } catch (AlipayApiException e) {
            logger.error("支付宝回调签名认证失败,paramsJson:{},errorMsg:{}", paramsJson, e.getMessage());
            return "failure";
        }
    }

    // 将request中的参数转换成Map
    private static Map<String, String> convertRequestParamsToMap(HttpServletRequest request) {
        Map<String, String> retMap = new HashMap<String, String>();

        Set<Entry<String, String[]>> entrySet = request.getParameterMap().entrySet();

        for (Entry<String, String[]> entry : entrySet) {
            String name = entry.getKey();
            String[] values = entry.getValue();
            int valLen = values.length;

            if (valLen == 1) {
                retMap.put(name, values[0]);
            } else if (valLen > 1) {
                StringBuilder sb = new StringBuilder();
                for (String val : values) {
                    sb.append(",").append(val);
                }
                retMap.put(name, sb.toString().substring(1));
            } else {
                retMap.put(name, "");
            }
        }

        return retMap;
    }

    private AlipayNotifyParam buildAlipayNotifyParam(Map<String, String> params) {
        String json = JsonUtils.toJSONString(params);
        return JSON.parseObject(json, AlipayNotifyParam.class);
    }

    /**
     * 1、商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
     * 2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
     * 3、校验通知中的seller_id（或者seller_email)是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email），
     * 4、验证app_id是否为该商户本身。上述1、2、3、4有任何一个验证不通过，则表明本次通知是异常通知，务必忽略。
     * 在上述验证通过后商户必须根据支付宝不同类型的业务通知，正确的进行不同的业务处理，并且过滤重复的通知结果数据。
     * 在支付宝的业务通知中，只有交易通知状态为TRADE_SUCCESS或TRADE_FINISHED时，支付宝才会认定为买家付款成功。
     * 
     * @param params
     * @throws AlipayApiException
     */
    private void check(Map<String, String> params) throws AlipayApiException {      
        String outTradeNo = params.get("out_trade_no");

        // 1、商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
//        Order order = getOrderByOutTradeNo(outTradeNo); // 这个方法自己实现
//        if (order == null) {
//            throw new AlipayApiException("out_trade_no错误");
//        }       

//        // 2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
//        long total_amount = new BigDecimal(params.get("total_amount")).multiply(new BigDecimal(100)).longValue();       
//        if (total_amount != order.getPayPrice().longValue()) {
//            throw new AlipayApiException("error total_amount");
//        }

        // 3、校验通知中的seller_id（或者seller_email)是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email），
       

        // 4、验证app_id是否为该商户本身。
        if (!params.get("app_id").equals(com.meida.pay.alipay.config.AlipayConfig.APPID)) {
            throw new AlipayApiException("app_id不一致");
        }
    }
    
    
}
