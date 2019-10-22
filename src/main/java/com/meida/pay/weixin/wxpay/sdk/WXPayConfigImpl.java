package com.meida.pay.weixin.wxpay.sdk;

import com.meida.common.config.SystemConfigUtils;
import com.meida.common.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

public class WXPayConfigImpl extends WXPayConfig {
    private byte[] certData;
    private static WXPayConfigImpl INSTANCE;
    //微信支付名称
    private static String wechatpaySubject;
    //微信支付appId(应用Id)
    private static String wechatpayAppID;
    //微信支付商户号
    private static String wechatpayMchID;
    //微信支付APIkey
    private static String wechatpayKey;
    //微信支付APPSecret(应用密钥)
    private static String wechatpaySecret;
    private WXPayConfigImpl(HttpServletRequest request) throws Exception{
        String certPath = request.getServletContext().getRealPath("")+ "/WEB-INF/config/cert/wechat/apiclient_cert.p12";
        //String certPath = "D:/CERT/common/apiclient_cert.p12";
        File file = new File(certPath);
        InputStream certStream = new FileInputStream(file);
        this.certData = new byte[(int) file.length()];
        certStream.read(this.certData);
        certStream.close();
    }

    public static WXPayConfigImpl getInstance(HttpServletRequest request) throws Exception{
        if (INSTANCE == null) {
            synchronized (WXPayConfigImpl.class) {
                if (INSTANCE == null) {
                    INSTANCE = new WXPayConfigImpl(request);
                    // 获取微信支付参数
                    String payProperty = SystemConfigUtils.getInstance().getPropertyValueByKey(Constants.WECHAT_PAY_PROPERTY);
                    // 将微信参数根据‘#’字符分割成字符串数组
                    List<String> strList = StringUtils.asList(payProperty, "#");
                    if (!strList.isEmpty() && strList.size() == 4) {
                        //微信支付名称
                        wechatpaySubject = "wechatpaySubject";
                        //微信支付appId(应用Id)
                        wechatpayAppID = strList.get(0);
                        //微信支付商户号
                        wechatpayMchID = strList.get(2);
                        //微信支付APIkey
                        wechatpayKey = strList.get(3);
                        //微信支付APPSecret(应用密钥)
                        wechatpaySecret = strList.get(1);
                    }
                }
            }
        }
        return INSTANCE;
//    	 if (INSTANCE == null) {
//             synchronized (WXPayConfigImpl.class) {
//                 if (INSTANCE == null) {
//                     INSTANCE = new WXPayConfigImpl(request);
//                  // 获取微信支付参数
////                     String payProperty = SystemConfigUtils.getInstance().getPropertyValueByKey(Constants.WECHAT_PAY_PROPERTY);
//             		// 将微信参数根据‘#’字符分割成字符串数组
////             		List<String> strList = StringUtils.asList(payProperty, ",");
////             		if (!strList.isEmpty() && strList.size() == 5) {
////             			//微信支付名称
////             			wechatpaySubject = ;
////             			//微信支付appId(应用Id)
////             			wechatpayAppID = strList.get(1);
////             			//微信支付商户号
////             			wechatpayMchID = strList.get(2);
////             			//微信支付APIkey
////             			wechatpayKey = strList.get(3);
////             			//微信支付APPSecret(应用密钥)
////             			wechatpaySecret = strList.get(4);
////             		}
//                   //微信支付名称
//         			wechatpaySubject = "wechatpaySubject";
//         			//微信支付appId(应用Id)
//         			wechatpayAppID = "wx526b610eec90d5bb";
//         			//微信支付商户号
//         			wechatpayMchID = "1317360501";
//         			//微信支付APIkey
//         			wechatpayKey ="1317360501piaozailvtu17701874485";
//         			//微信支付APPSecret(应用密钥)
//         			wechatpaySecret = "1317360501piaozailvtu17701874485";
//                 }
//             }
//         }
//         return INSTANCE;
    }

    public String getSubject() {
        return "wechatpaySubject";
    }
    public String getAppID() {
        return wechatpayAppID; //"wx526b610eec90d5bb";
    }

    public String getMchID() {
        return wechatpayMchID; //"1317360501";
    }

    public String getKey() {
        return wechatpayKey; // "1317360501piaozailvtu17701874485";
    }

    public InputStream getCertStream() {
        ByteArrayInputStream certBis;
        certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }


    public int getHttpConnectTimeoutMs() {
        return 2000;
    }

    public int getHttpReadTimeoutMs() {
        return 10000;
    }

    public IWXPayDomain getWXPayDomain() {
        return WXPayDomainSimpleImpl.instance();
    }

    public String getPrimaryDomain() {
        return "api.mch.weixin.qq.com";
    }

    public String getAlternateDomain() {
        return "api2.mch.weixin.qq.com";
    }

    @Override
    public int getReportWorkerNum() {
        return 1;
    }

    @Override
    public int getReportBatchSize() {
        return 2;
    }
}
