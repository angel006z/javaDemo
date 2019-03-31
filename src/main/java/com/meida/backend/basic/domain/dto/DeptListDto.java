package com.meida.backend.basic.domain.dto;

import com.meida.base.domain.vo.Pagination;

public class DeptListDto {
	public DeptListDto() {

	}

	private String deptCode;
	
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

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
}
