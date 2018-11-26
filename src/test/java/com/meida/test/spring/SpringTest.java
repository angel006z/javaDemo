package com.meida.test.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * spring插件开发包： http://spring.io/tools3/sts/all
 * 
 * Spring中装配bean的三种主要方式
 * 在XML中进行显式配置
 * 在Java中进行显式配置
 * 隐式的bean发现机制和自动装配
 */
public class SpringTest {
	public static void main(String[] args) {
//		创建Spring的IOC容器的对象
//		ApplicationContext ctx= new ClassPathXmlApplicationContext("test/beans.xml");
		
		
		//1、创建Spring的IOC容器的对象
		ApplicationContext ctx= new ClassPathXmlApplicationContext("test/beans.xml");//从类路径下加载配置文件	
		//ApplicationContext ctx= new FileSystemXmlApplicationContext("D:\\eclipse-workspace\\javaDemo\\src\\main\\resources\\test\\beans.xml");//从文件系统中加载配置文件	
		System.out.println("ConfigurableApplicationContext:有close方法"); 
		System.out.println("--------------------");
		System.out.println("说明了在创建SpringIOC容器的时候，就已经对类进行了实例化并对属性进行了赋值");
		System.out.println("--------------------");
		
		// 2、从IOC的容器中获取Bean的实例
		HelloWorld helloWorld = (HelloWorld) ctx.getBean("helloworld2");

		// 3、调用sayHello方法
		helloWorld.sayHello();
		
		
	}
}
