package com.meida.pay.pojo;

import java.math.BigDecimal;

public class ParametersTradePay extends ParametersBuilder {
	public ParametersTradePay() {
		payType = EPayType.Alipay;// 默认支付宝
		payChannel = EPayChannel.Alipay_PAGE;// 电脑网站
		subject = "";
		body = "";
		attach = "";
		out_trade_no = "";
		total_fee = new BigDecimal(0.00);
	}

	/**
	 * 在线支付类型
	 */
	private String payType;
	/**
	 * 详细支付方式
	 */
	private String payChannel;
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
	private BigDecimal total_fee;

	@Override
	public boolean Validate() {
		return true;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getPayChannel() {
		return payChannel;
	}

	public void setPayChannel(String payChannel) {
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

	public BigDecimal getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(BigDecimal total_fee) {
		this.total_fee = total_fee;
	}

	

}
