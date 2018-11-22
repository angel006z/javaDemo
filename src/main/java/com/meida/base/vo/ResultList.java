package com.meida.base.vo;

import com.meida.common.pojo.Pagination;

public class ResultList extends ResultMessage {
	private Pagination pagination;
	/**
	 * 数据内容
	 */
	private Object data;
	
	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
