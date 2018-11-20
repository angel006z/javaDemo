package com.meida.common.util.config;

public class SystemConfigUtils {
	private static SystemConfigUtils instance;
	/**
	 * 不让外部采用new的方式调用本类，只允许使用单例模式
	 */
	private SystemConfigUtils() {
		
	}
	
	/**
	 * 获取当前对象的实例
	 * @return
	 */
	public static SystemConfigUtils getInstance() {
		if (instance == null) {
			synchronized (SystemConfigUtils.class) {
				instance = new SystemConfigUtils();
			}
		}
		return instance;
	}
	
	
}
