package com.meida.test.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * spring插件开发包： https://spring.io/tools
 * 
spring的核心思想是IOC和AOP
https://www.cnblogs.com/xdp-gacl/p/4249939.html
Ioc—Inversion of Control，即“控制反转”，不是什么技术，而是一种设计思想。
在Java开发中，Ioc意味着将你设计好的对象交给容器控制，而不是传统的在你的对象内部直接控制。

DI—Dependency Injection，即“依赖注入”：组件之间依赖关系由容器在运行期决定，
形象的说，即由容器动态的将某个依赖关系注入到组件之中。
依赖注入的目的并非为软件系统带来更多功能，而是为了提升组件重用的频率，
并为系统搭建一个灵活、可扩展的平台。通过依赖注入机制，我们只需要通过简单的配置，
而无需任何代码就可指定目标需要的资源，完成自身的业务逻辑，而不需要关心具体的资源来自何处，由谁实现。


理解DI的关键是：“谁依赖谁，为什么需要依赖，谁注入谁，注入了什么”，那我们来深入分析一下：
●谁依赖于谁：当然是应用程序依赖于IoC容器；
●为什么需要依赖：应用程序需要IoC容器来提供对象需要的外部资源；
●谁注入谁：很明显是IoC容器注入应用程序某个对象，应用程序依赖的对象；
●注入了什么：就是注入某个对象所需要的外部资源（包括对象、资源、常量数据）。
IoC和DI由什么关系呢？其实它们是同一个概念的不同角度描述，
由于控制反转概念比较含糊（可能只是理解为容器控制对象这一个层面，很难让人想到谁来维护对象关系），
所以2004年大师级人物Martin Fowler又给出了一个新的名字：
“依赖注入”，相对IoC 而言，“依赖注入”明确描述了“被注入对象依赖IoC容器配置依赖对象”。
 * 
 * 
 * 
 * 
 * 依赖注入的实现方式: 
 * <p>1、构造函数注入（Contructor Injection）</p>
 * <p>2、setter注入</p>
 * <p>3、接口注入</p>
 * <p>4、静态工厂初始化 如类：java.nio.charset.Charset类  forName方法</p>
 * 
 * Spring中装配bean的三种主要方式 
 * <p>在XML中进行显式配置        基于XML的bean定义（需要提供setter方法） </p>
 * <p>在Java中进行显式配置      基于Java类的bean定义（需要提供setter方法） </p>
 * <p>隐式的bean发现机制和自动装配 基于注解的bean定义（不需要提供setter方法）</p>
 */
public class SpringTest {

	/**
	 对象与对象之间可能存在什么关系？（is a，has a，use a）
	 例如：
 a) is a 关系
 class A implements IA ｛｝; A 实现了 IA 接口
 class A extends B ｛｝; A 继承了 B 这个类
 b) has a 关系
 class Point ｛｝
 class Circle ｛
     private Point point
 ｝
 circle 对象中有一个 Point 类型的对象
 c) use a 关系
 class ProjectUtil ｛
     public String getId(){
       return UUIDrandomUUID().toString();
     }
 ｝
 在 ProjectUtil 里使用了 UUID 这个类
	 */
	public static void main(String[] args) {
//		创建Spring的IOC容器的对象
//		ApplicationContext ctx= new ClassPathXmlApplicationContext("test/beans.xml");

		// 1、创建Spring的IOC容器的对象
		ApplicationContext ctx = new ClassPathXmlApplicationContext("test/beans.xml");// 从类路径下加载配置文件
		// ApplicationContext ctx= new
		// FileSystemXmlApplicationContext("D:\\eclipse-workspace\\javaDemo\\src\\main\\resources\\test\\beans.xml");//从文件系统中加载配置文件
		System.out.println("ConfigurableApplicationContext:有close方法");
		System.out.println("--------------------");
		System.out.println("说明了在创建SpringIOC容器的时候，就已经对类进行了实例化并对属性进行了赋值");
		System.out.println("--------------------");

		// 2、从IOC的容器中获取Bean的实例
		HelloWorld t1 = (HelloWorld) ctx.getBean("helloworld");

		// 3、调用sayHello方法
		t1.sayHello();

		System.out.println(t1.getName());

		HelloWorld t2 = (HelloWorld) ctx.getBean("helloworld");
		System.out.println("t1 hashcode:" + t1.hashCode());
		System.out.println("t2 hashcode:" + t2.hashCode());
		System.out.println("t1对象和t2对象比较：" + (t1 == t2));
		System.out.println("Spring在缺省情况下是单实例模式，在容器分配Bean的时候总是返回同一个实例。");
		System.out.println("--------------------");

	}
}
