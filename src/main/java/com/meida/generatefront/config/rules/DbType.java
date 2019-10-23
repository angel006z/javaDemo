package com.meida.generatefront.config.rules;

/**
 * 数据库类型定义
 */
public enum DbType {

	MYSQL("mysql"), ORACLE("oracle"), SQLSERVER("sqlserver");

	private final String value;

	DbType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
