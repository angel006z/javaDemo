package com.meida.paysdk.alipay.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;

public class AlipayClientFactory {
	// SDK 公共请求类，包含公共请求参数，以及封装了签名与验签，开发者无需关注签名与验签
	private static AlipayClient client  = new DefaultAlipayClient(AlipayConfig.URL,
            AlipayConfig.APPID, 
            AlipayConfig.RSA_RRIVATE_KEY, 
            AlipayConfig.FORMAT,
            AlipayConfig.CHARSET,
            AlipayConfig.ALIPAY_PUBLIC_KEY,
            AlipayConfig.SIGNTYPE            
            );
	
    public static AlipayClient getAlipayClientInstance()
    {
        return client ;        
    }
}
