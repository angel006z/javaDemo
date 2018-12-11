package com.meida.test.proxy;

/**
静态代理
静态代理的实现比较简单，代理类通过实现与目标对象相同的接口，
并在类中维护一个代理对象。通过构造器塞入目标对象，赋值给代理对象，
进而执行代理对象实现的接口方法，并实现前拦截，后拦截等所需的业务功能。
静态代理的总结
优点：可以做到不对目标对象进行修改的前提下，对目标对象进行功能的扩展和拦截。
缺点：因为代理对象，需要实现与目标对象一样的接口，会导致代理类十分繁多，不易维护，
同时一旦接口增加方法，则目标对象和代理类都需要维护。
*/
public class ChildProxy implements Parent {
	private Parent parent;
	public ChildProxy(Parent parent) {
        this.parent = parent;
    }
	@Override
	public void sayHello() {
		System.out.println("前拦截...");
		this.parent.sayHello();
		System.out.println("后拦截...");
	}

	@Override
	public void sayByeBye() {
		System.out.println("前拦截...");
		this.parent.sayByeBye();
		System.out.println("后拦截...");
	}
	
	
}
