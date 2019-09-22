package com.meida.front.pay.domain.dto;

import com.meida.base.domain.vo.Pagination;

public class FundAmountListDto {
	public FundAmountListDto() {

	}

	private Long memberId;
	

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


	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}
}
