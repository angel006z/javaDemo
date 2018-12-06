package com.medai.test.helloworld;

import java.util.HashMap;

public class HashCodeEqual {
	public static void main(String[] args) {
		Student a1 = new Student("a1");
		Student a2 = new Student("a2");
		HashMap<Student, Integer> map = new HashMap<Student, Integer>();
		map.put(a1, 10);
		map.put(a2, 20);
		
		System.out.println(map.get(new Student("a1")));
		//这里要同时重新hashCode()和equals()才能获取到值。
		//对于hash对象是先判断hashcode是否相等，然后再比较equals的。
		//hashcode作用是提供检索速度的。
		//https://www.cnblogs.com/tonghun/p/6938016.html
	}
}
