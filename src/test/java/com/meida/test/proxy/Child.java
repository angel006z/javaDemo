package com.meida.test.proxy;

public class Child implements Parent {

	@Override
	public void sayHello() {
		System.out.println("say hello!");
		
	}

	@Override
	public void sayByeBye() {
		System.out.println("say byebye!");
		
	}

}
