package com.meida.common.cache;
/** 
 * 缓存对象
 */
public class Cache {
	
	/**
	 * 缓存的id
	 */
	private String key;
	
	/**
	 * 缓存内容
	 */
	private Object value;
	
	/**
	 * 更新时间
	 */
	private Long timeout;
	
	/**
	 * 是否终止
	 */
	private boolean expired;
	

	/**
	 * 无参构造器
	 */
	public Cache() {
		super();
		setTimeout(System.currentTimeMillis()+1000*60*60*24);
		setExpired(false);
	}

	/**
	 * 有参构造器
	 * @param key
	 * @param value
	 * @param timeout
	 * @param expired
	 */
	public Cache(String key, String value, Long timeout, boolean expired) {
		super();
		this.key = key;
		this.value = value;
		this.timeout = timeout;
		this.expired = expired;
	}

	/**
	 * 获取 缓存的id
	 */
	public String getKey() {
		return key;
	}

	/**
	 * 设置 缓存的id
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * 获取 更新时间
	 */
	public Long getTimeout() {
		return timeout;
	}

	/**
	 * 设置 更新时间
	 */
	public void setTimeout(Long timeout) {
		this.timeout = timeout;
	}

	/**
	 * 获取 是否终止
	 */
	public boolean isExpired() {
		return expired;
	}

	/**
	 * 设置 是否终止
	 */
	public void setExpired(boolean expired) {
		this.expired = expired;
	}

	/**
	 * 获取 缓存内容
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * 设置 缓存内容
	 */
	public void setValue(Object value) {
		this.value = value;
	}
	

}
