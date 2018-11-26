package com.meida.test.spring;

public class HelloWorld {
	public HelloWorld() {
		System.out.println("初始化构造器");
	}
	public HelloWorld(String s) {
		System.out.println("s:" + s);
	}

	private String name;

	public void setName(String name) {
		System.out.println("调用了设置属性");
		this.name = name;
	}	

	public void sayHello() {
		System.out.println("Hello: " + name);
	}
}