package com.meida.test.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理 动态代理是指动态的在内存中构建代理对象（需要我们制定要代理的目标对象实现的接口类型），
 * 即利用JDK的API生成指定接口的对象，也称之为JDK代理或者接口代理。
 *
 */
public class ProxyFactory implements InvocationHandler {
	private Object targetObject;

	public Object getProxyInstance(Object targetObject) {
		this.targetObject = targetObject;
		return Proxy.newProxyInstance(this.targetObject.getClass().getClassLoader(),
				this.targetObject.getClass().getInterfaces(), this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		long startTime = System.currentTimeMillis();
		System.out.println("前拦截...");
		System.out.println("Method Name:"+method.getName());
		Object result = method.invoke(this.targetObject, args);
		System.out.println("前拦截...");
		long endTime = System.currentTimeMillis();
		float seconds = (endTime - startTime) / 1000F;

		System.out.println(Float.toString(seconds) + " seconds.");
		return result;
	}

}
