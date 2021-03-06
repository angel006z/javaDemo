package com.meida.pay.dto;

import com.meida.basefront.dto.CurrentMemberDto;

import java.math.BigDecimal;

public class BuildRechargeOrderDto {
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
	private BigDecimal totalFee;

	/**
	 * 充值会员id
	 */
	private Long rechargeMemberId;

	private CurrentMemberDto currentMemberDto;

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

	public BigDecimal getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(BigDecimal totalFee) {
		this.totalFee = totalFee;
	}

	public Long getRechargeMemberId() {
		return rechargeMemberId;
	}

	public void setRechargeMemberId(Long rechargeMemberId) {
		this.rechargeMemberId = rechargeMemberId;
	}

	public CurrentMemberDto getCurrentMemberDto() {
		return currentMemberDto;
	}

	public void setCurrentMemberDto(CurrentMemberDto currentMemberDto) {
		this.currentMemberDto = currentMemberDto;
	}
}
