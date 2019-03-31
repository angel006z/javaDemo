package com.meida.backend.basic.domain.dto;

import com.meida.base.domain.vo.Pagination;

public class RoleListDto {
	public RoleListDto() {

	}

	private String roleCode;
	
	private String roleName;

	private Pagination pagination;

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
}
