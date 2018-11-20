package com.meida.pay.alipay.config;

public class Config {
	/// <summary>
    /// 开发者应用私钥。java配置PKCS8格式，PHP/.Net语言配置rsa_private_key.pem文件中原始私钥。
    /// </summary>
    public final static String RSA_RRIVATE_KEY = "MIICXQIBAAKBgQC04PrcvzTCMxcQ4wP30n4Ta0Qwohdj3NT9LwwvCeI317O1jmCk" +
                                        "X4+p/k7Er6pizoLc6Z11+UCC/2lpZKmUADjlv4sP06W2TwCfm7LmcS6oRTJUtswe" +
                                        "mYYEOhcszCtw7wlFDqK068kZtHXj7fH1+zdAIBwxcHtpfeWE1cOTaniNRQIDAQAB" +
                                        "AoGABV+ltEct7xMLfWHxLTygqD0IkpVOMpZN8ZOjPgaCJPyQhzVKo7d/EiLO/kLl" +
                                        "wfe2pY9/MRENnJav8ASgkRgMmKHyEgZ64jilcHf3dADypNel8hHxXWTCrKmFcfYy" +
                                        "p8EU2ZXEeHBJ9YXKIC/s5zz66Wvg945a8VIk1v29qIdNX6ECQQDh8MVZBUkr4aIc" +
                                        "gqidgf+K/GcAhzA0XjsRAKdwwRWSS0nZEatcAPn9jlMZ2FZAZeyiFgl2Cl1KsW2Q" +
                                        "urlul1jdAkEAzPF4CB2jgIUByE3UUt269x2kGG5N98BiLrpJcnm/MYkrHq5UzWVF" +
                                        "vxpSXeI/XfhS/m7kWlwNgOO8eBi5xoKLiQJBAIty8ErGUWgNBeI03l8AejlNGUGh" +
                                        "LqZTtn8C+VVFRswZPcYE+s95JtJRAodEF8/9WHSdx08cjIHqSmZ2aLaSIN0CQQCs" +
                                        "TvUJdcnrG2kVPQKgLVatwBkghVU+UVz7RprMCbqLbnV3lUEtc9FBTGMmFjwKyOcW" +
                                        "jquloTGlRVRHcRp44XdpAkBOPoEOEy2bilV3yDIKQlOhcbFvdDA3QYqjrvphXh3u" +
                                        "2rtO+vwp7vGzakXTNuMzocwACJkOYldn26WV0whxaWJX";

    // 接口请求网关，固定值
    // -----沙箱地址-----
    public final static String URL = "http://openapi.alipaydev.com/gateway.do";
    // -----线上地址-----
    //public const string URL = "https://openapi.alipay.com/gateway.do";
    // 商户应用APPID，只要您的应用中包含服务窗接口且是开通状态，就可以用此应用对应的appid。开发者可登录开放平台-管理中心-对应应用中查看
    //public const string APPID = "2016091701914051";
    //沙箱APPID
    public final static String APPID = "2016091600524045";
    //商户UID
    public final static String UID = "2088102175876856";

    // 编码字符集。默认 utf-8
    public final static String CHARSET = "utf-8";
    // 返回格式。默认json
    public final static String FORMAT = "json";
    public final static String VERSION = "1.0";
    public final static String SIGNTYPE = "RSA";
    public final static boolean KEYFFROMFILE = false;//秘钥是否是文件形式

    /// <summary>
    /// 支付宝公钥，用于获取同步返回信息后进行验证，验证是否是支付宝发送的信息。
    /// </summary>
    public final static String ALIPAY_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC04PrcvzTCMxcQ4wP30n4Ta0Qwohdj3NT9LwwvCeI317O1jmCkX4+p/k7Er6pizoLc6Z11+UCC/2lpZKmUADjlv4sP06W2TwCfm7LmcS6oRTJUtswemYYEOhcszCtw7wlFDqK068kZtHXj7fH1+zdAIBwxcHtpfeWE1cOTaniNRQIDAQAB";

    //支付宝回跳通知地址
    public final static String ALIPAY_NotifyUrl = "http://localhost:3099/Pay/AlipayNotify";

    //支付宝同步返回地址，HTTP/HTTPS开头字符串
    public final static String ALIPAY_ReturnUrl = "http://localhost:3099/Pay/Recharge";

    /*沙箱:
    商家信息
    商家账号bpsbgh9434@sandbox.com
    商户UID2088102175876856
    登录密码111111
    买家信息
    买家账号dlgwjs6183@sandbox.com
    账号名称沙箱买家测试账号
    登录密码111111
    支付密码111111
    账户余额
    99999.00充值         
    */
}
