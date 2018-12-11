package com.meida.test.proxy;

/**
 * Java静态代理和动态代理
 * 
 * 动态代理技术就是用来产生一个对象的代理对象的。 明确代理对象的两个概念： 1、代理对象存在的价值主要用于拦截对真实业务对象的访问。
 * 2、代理对象应该具有和目标对象(真实业务对象)相同的方法。
 */
public class TestMain {
	public static void main(String[] args) {
		ChildProxy childProxy = new ChildProxy(new Child());
		childProxy.sayHello();

		ProxyFactory logProxy =new ProxyFactory();
		Parent c=new Child();
		Parent p=(Parent)logProxy.getProxyInstance(c);
		c.sayByeBye();
		System.out.println(">>>>>>>>>>");
		p.sayByeBye();
	}
}
