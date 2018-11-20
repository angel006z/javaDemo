package com.meida.pay.alipay.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;

public class AlipayClientFactory {
	// SDK 公共请求类，包含公共请求参数，以及封装了签名与验签，开发者无需关注签名与验签
	private static AlipayClient client  = new DefaultAlipayClient(Config.URL,
            Config.APPID, 
            Config.RSA_RRIVATE_KEY, 
            Config.FORMAT,
            Config.CHARSET,
            Config.ALIPAY_PUBLIC_KEY,
            Config.SIGNTYPE            
            );
	
    public static AlipayClient getAlipayClientInstance()
    {
        return client ;        
    }
}
