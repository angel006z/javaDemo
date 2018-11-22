package com.meida.backend.basic.dto;

import com.meida.base.dto.BaseBackendDto;
import com.meida.common.pojo.Pagination;

public class DeptListDto extends BaseBackendDto {
	
	private String deptName;
	
	private Pagination pagination;
	
	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
}
