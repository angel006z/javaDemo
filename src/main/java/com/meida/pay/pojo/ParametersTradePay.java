package com.meida.pay.pojo;

import java.math.BigDecimal;

public class ParametersTradePay extends ParametersBuilder {
	public ParametersTradePay() {
		payType = EPayType.Alipay;// 默认支付宝
		payChannel = EPayChannel.Alipay_PC_WEB;// 电脑网站
		subject = "";
		body = "";
		attach = "";
		out_trade_no = "";
		total_fee = "0.00";
	}

	/**
	 * 在线支付类型
	 */
	private int payType;
	/**
	 * 详细支付方式
	 */
	private int payChannel;
	/**
	 * 商品标题
	 */
	private String subject;

	/**
	 * 商品描述
	 */
	private String body;
	/**
	 * 附加数据
	 */
	private String attach;
	/**
	 * 订单号
	 */
	private String out_trade_no;
	/**
	 * 总金额（人民币单位元）
	 */
	private String total_fee;

	@Override
	public boolean Validate() {
		return true;
	}

	public int getPayType() {
		return payType;
	}

	public void setPayType(int payType) {
		this.payType = payType;
	}

	public int getPayChannel() {
		return payChannel;
	}

	public void setPayChannel(int payChannel) {
		this.payChannel = payChannel;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}

	

}
