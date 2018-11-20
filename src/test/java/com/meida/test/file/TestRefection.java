package com.meida.test.file;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class TestRefection {
	private String name;
	private int age;

	public static void main(String[] args) throws NoSuchFieldException, SecurityException, IllegalArgumentException,
			IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
		// Class.forName
		Class<com.meida.test.file.TestRefection> d = com.meida.test.file.TestRefection.class;
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
		TestRefection temp = d.newInstance();
		f.setAccessible(true);
		f.set(temp, "test");
		System.out.println(f.get(temp));
		f.setAccessible(false);

		// f.getAnnotations();

		
		
		TestRefection testObj = d.newInstance();
		Method mSet = d.getMethod("setName", String.class);
		mSet.invoke(testObj, "test set 方法");
		
		Method mGet = d.getMethod("getName");
		System.out.println(mGet.invoke(testObj));
		
		
		
		

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
