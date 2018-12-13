package com.meida.test.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;


/**
 * 反射的概述:
 * JAVA反射机制是在运行状态中，对于任意一个类，都能够知道这个类的所有属性和方法；
 * 对于任意一个对象，都能够调用它的任意一个方法和属性；
 * 这种动态获取的信息以及动态调用对象的方法的功能称为java语言的反射机制。
 * 
 * 
 */
public class ReflectTest {


	public static void main(String[] args) throws NoSuchFieldException, SecurityException, IllegalArgumentException,
			IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

		
		// Class.forName
		Class<com.meida.test.reflect.ReflectTest> d = com.meida.test.reflect.ReflectTest.class;
		// 此方法可以获取私有属性，而getField只能获取公共的属性
		Field f = d.getDeclaredField("name");
		// 获取属性的名称
		System.out.println(f.getName());
		// 获取属性的type
		System.out.println(f.getGenericType());
		// 获取属性的修饰符
		int modifiers = f.getModifiers();
		System.out.println(Modifier.toString(modifiers));
		// 给属性赋值
		ReflectTest temp = d.newInstance();
		f.setAccessible(true);
		f.set(temp, "test");
		System.out.println(f.get(temp));
		f.setAccessible(false);

		// f.getAnnotations();

		
		
		ReflectTest testObj = d.newInstance();
		Method mSet = d.getMethod("setName", String.class);
		mSet.invoke(testObj, "test set 方法");
		
		Method mGet = d.getMethod("getName");
		System.out.println(mGet.invoke(testObj));
		
		
		
		

	}
	
	
	private String name;//私有字段
	private int age;//私有字段
	public String getName() { //name的可读属性
		return name;
	}

	public void setName(String name) { //name的可写属性
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
