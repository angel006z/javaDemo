package com.meida.front.pay.dto;

import java.math.BigDecimal;

public class ChargeDto {
	/**
	 * 在线支付类型
	 */
	private String payType;
	/**
	 * 详细支付方式
	 */
	private String payChannel;
	/**
	 * 总金额（人民币单位元）
	 */
	private BigDecimal total_fee;

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

	public BigDecimal getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(BigDecimal total_fee) {
		this.total_fee = total_fee;
	}
}
