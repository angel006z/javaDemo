package com.meida.front.pay.dto;

import com.meida.backend.base.dto.BaseParamDto;
import com.meida.base.vo.Pagination;

import java.util.Date;
import java.math.BigDecimal;
/**
 * AccountRechargeListParamDto
 * 
 * @author BING
 * @date 2019-10-19 15:27:58
 */
public class AccountRechargeListParamDto extends BaseParamDto{
	private static final long serialVersionUID=1L;

    private Pagination pagination;

	private String timeType;

	public String getTimeType() {
		return timeType;
	}

	public void setTimeType(String timeType) {
		this.timeType = timeType;
	}

	public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

}
