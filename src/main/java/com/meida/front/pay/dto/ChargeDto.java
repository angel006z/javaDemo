package com.meida.front.pay.dto;

import java.math.BigDecimal;

public class ChargeDto {
	/**
	 * 在线支付类型
	 */
	private int payType;
	/**
	 * 详细支付方式
	 */
	private int payChannel;
	/**
	 * 总金额（人民币单位元）
	 */
	private BigDecimal total_fee;

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

	public BigDecimal getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(BigDecimal total_fee) {
		this.total_fee = total_fee;
	}
}
