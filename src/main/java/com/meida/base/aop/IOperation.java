package com.meida.base.aop;

import java.lang.reflect.Method;

/** 
*操作日志
 */
public interface IOperation {
	/**
     * 方法执行之前的操作
     * @param method
     */
    void start(Method method);
    /**
     * 方法执行之后的操作
     * @param method
     */
    void end(Method method);
}
