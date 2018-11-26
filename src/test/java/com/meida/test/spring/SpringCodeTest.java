package com.meida.test.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringCodeTest {
	public static void main(String[] args) {
	
		//JavaConfig上下文，加载配置文件
		AnnotationConfigApplicationContext ac = 
				new AnnotationConfigApplicationContext(HelloWorld02.class);
		HelloWorld ip=(HelloWorld) ac.getBean("aa");
		ip.sayHello();
		
	}
}
