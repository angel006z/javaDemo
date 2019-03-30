package com.meida.backend.basic.dto;

import com.meida.base.vo.Pagination;

public class UserListDto {
	public UserListDto() {

	}

	private String userCode;
	
	private String userName;

	private Pagination pagination;

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
}
