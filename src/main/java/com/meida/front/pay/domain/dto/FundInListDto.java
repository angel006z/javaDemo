package com.meida.front.pay.domain.dto;

import com.meida.base.domain.vo.Pagination;

public class FundInListDto {
	public FundInListDto() {

	}

	private Long memberId;
	
	private String orderNo;

	private Pagination pagination;

	public Pagination getPagination() {
		return pagination;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}
}
