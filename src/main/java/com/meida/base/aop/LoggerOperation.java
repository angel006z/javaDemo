package com.meida.base.aop;

import java.lang.reflect.Method;

/** 
 * 添加日誌
 */
public class LoggerOperation implements IOperation {

	/** 方法执行开始
	 * @param method 
	 */
	@Override
	public void start(Method method) {
		System.out.println(method.getName()+"方法执行了开始了1111111111");

	}

	/** 方法执行结束
	 * @param method 
	 */
	@Override
	public void end(Method method) {
		System.out.println(method.getName()+"方法执行了结束了222222222");

	}

}
