package com.meida.pay.pojo;

/**
 * 详细支付方式
 * Alipay
 * Weixin
 *
 */
public class EPayChannel {
	public final static String Alipay_PAGE = "Alipay_PAGE";
	public final static String Alipay_WAP = "Alipay_WAP";
	public final static String Alipay_App_Android = "Alipay_App_Android";
	public final static String Alipay_App_iOS = "Alipay_App_iOS";
	/*
	 * 微信扫码支付模式一和模式二的区别 模式一和模式二提供了两种不同的能力，适用于不同的场景，看商户具体的需求。 两种模式，在支付的流程中，有一定的共同的流程：
	 * 1，生成订单。 2，用户支付。 差别在于： 模式一，先扫码，再生成订单。 模式二，先生成订单，再扫码。
	 */
	/**
	 * 扫码支付
	 */
	public final static String Weixin_NATIVE = "Weixin_NATIVE";
}
