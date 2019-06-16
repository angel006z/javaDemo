package com.meida.pay.alipay.config;

public class Config {
	/// <summary>
	///注意要将rsa公钥上传到支付上。上传到支付宝后，支付宝会生成支付宝公钥。
    /// 开发者应用私钥。java配置PKCS8格式，PHP/.Net语言配置rsa_private_key.pem文件中原始私钥。
    /// </summary>
    public final static String RSA_RRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCzIgPMq16pOvMXcudtnNKC+hTnQg0djAiGsQcU0VJyhvT9735CqJ767NNSi3reUfmu28W06Fpoz4cNDQ6soFO1BvFWzb06B1KFqPYc3AzfVIwHmP5//zzBrmsd6nJgjp6bRpQlGxoIaLIHaJ/m2gEjLMRFYIbHqIj2NJggxLV3Z2Sx5sHP55MYU0qQen7bXlhwuBCKoXOi0fDBSZ0nWsGkIOM2nLfocjPJ7J212FGVmVqgp9KxtMU1MDShroFl7cFYkU4t2luKFjWhmqbizOxpVHqYEyTy6gCKVxjJ+LIjf3yHXPfmPMxxextS9r8usBQeyCXPiu+CoZc23meCAAdXAgMBAAECgf814QVDikfunsx/QRBa59BplM60wCkxuezWlmiK2mryB6Wou2A+rHklABaPvWQ3enCfJ3tP7pNC8yirxEGlqpOC35O3iCpiSdGIYkmkZIt6oPN9VYc2+I5NsyiE1v+QC1WpXpEwl/Y8kUD6+X0e8nNlIRHnzCC97PNFGoynToNGZlH9Up9vTHPlBdgWW0idnfqwvfX2m8MRH0xVH8l3PDd/YPGO9lW+f1d6xVvEf6dOr57IpuOUuQn4mMX9ByvU7++oLcKs3IvmLkSERky/OsxCYb1PDZC6/hFNptZ5eyBHuhfQMN8lffC3D6djUMcq7vfI8nPf+u4YnTo5yME1qGECgYEA3aYE4IFIf9HCLD3XUwKZwF7HWKLSJXM91/QQm5CelSG5PWOVZm1DYr6cySpLmTeDS/xQ6BU9EbzqHM5yt9aNMC8g7Or76AJjh+kklFXMVTD2uPzqKXw5I8zLWM8LjjwCMpeZbjNd7KE8ycIsNTBWORGYwJjQ70f1oi0KBDJ0wCcCgYEAzuUsT9PenkT8YHM3jFrCnbJzilb0ykc8cQ5P4bSVVUs69dNXdwdgZrslJd8kQVL7s1BRWuBAcf540A+860XfrRvCG+2vKy7Wsg0Zl3dLPyclPaXS9YjIznVWFgCEFBMy57mgA5cQdLxKZAVBrPVWFIHU1tgyVmq7gTUC/5dQzVECgYEAxskzzIoGlKRyai1YZZmKfW5DiSoWIQJXL8wz7OFqaMPjF7ytbDyIWBIuGXj2bkbMWaTEhzEtITB2IznAx0IERHewJuuk23LxAx7E8Mh2Rt1wGX6zIFvJ/2KX6WFfssP9EJTIRX6KOURueWDNNzLP0cUHY4xqrn+yPC36ssLx1LcCgYAlTvpAouogDDyef+hibPNvUIC5T5jw6QujL3uELfZH3TsSnTvGgRQHMnQuciCtjvNIkJE7G/lDFSpgCpZ8rqr5ImpMcv4FgXrBvM8Cm0Z8auiyRzbVhAcCA/K0IP+wRw4E71pFi3YyNsoLY09Giy8sn4TsASyCnNiXIiIaSQUPIQKBgQColCHDslNSjUPyPAFAzs/MKwoZgqzjOto9Icsy4t2Tx6JYiuCJNXTae0TwOuAErVWVOQcrR+lmAB8PPYlRnKHuwZ+sERbAadLfx2jrMg+C4GJD9xhyZz9GtUJNAP4fNKJV1IjGdvNxp6H2kcWH0pcfJy0F/r35c7xoyIfIXc/uHQ==";
    
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
    public final static String SIGNTYPE = "RSA2";
    public final static boolean KEYFFROMFILE = false;//秘钥是否是文件形式

    /// <summary>
    ///注意这里是支付宝公钥，而非rsa的公钥
    /// 支付宝公钥，用于获取同步返回信息后进行验证，验证是否是支付宝发送的信息。
    /// </summary>
    public final static String ALIPAY_PUBLIC_KEY ="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArPN0nI469dIeEUQsDSIyjPU+1E8OPl3HWP5RuWfhRLeVb9UW53AGJKRhKf9avfN6BHTIPW2uN+Ca0Ek09UWnAdZE7AWloqBfcjMGX6PZkR8CeiWI7Izr1eq0QFiZjVzZl01wunvFim4MJ5OfaQ8EhKTvKD1Q9G5jvdmCGLxUZTx5ctD6r6oOU33lDGh+DMlveR2OZMhNYFzSl4N51LVT0DDhr78/UgNq8KRtcm53f2mkVkgNylBKS9PaHBeHu9TL6iUcBwpSID0soSoii+0Wi4dNSUmSenoKe+n3VTXoNMD0wUfQTFIN38pMtNTyelomzfsh5XKa8wMEER/AjGTLWwIDAQAB";

    		
    //支付宝回跳通知地址
    public final static String ALIPAY_NotifyUrl = "http://localhost:8088/javaDemo/front/alipaynotify/index";

    //支付宝同步返回地址，HTTP/HTTPS开头字符串
    public final static String ALIPAY_ReturnUrl = "http://localhost:8088/javaDemo/front/alipayreturn/index";

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
