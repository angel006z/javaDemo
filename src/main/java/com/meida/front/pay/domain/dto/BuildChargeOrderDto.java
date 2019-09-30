package com.meida.front.pay.domain.dto;

import java.math.BigDecimal;

public class BuildChargeOrderDto {
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

	/**
	 * 充值会员id
	 */
	private Long chargeMemberId;

	public Long getChargeMemberId() {
		return chargeMemberId;
	}

	public void setChargeMemberId(Long chargeMemberId) {
		this.chargeMemberId = chargeMemberId;
	}

	private CurrentMemberDto currentMember;

	public CurrentMemberDto getCurrentMember() {
		return currentMember;
	}


	public void setCurrentMember(CurrentMemberDto currentMember) {
		this.currentMember = currentMember;
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

	public BigDecimal getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(BigDecimal total_fee) {
		this.total_fee = total_fee;
	}
}
