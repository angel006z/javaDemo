package com.meida.test.thread;
/**
 * 
 * 
 * 什么是线程不安全
 * 当多个线程同时操作一个数据结构的时候产生了相互修改和串行的情况，没有保证数据的一致性，我们同城称这种代码的设计为“线程不安全的”。 
 * 
 * 什么是线程安全
 * 保证数据的高度一致性和准确性就叫线程安全的。
　　想实现线程安全大致可以有三种方法：
　　(1):多例模式，也就是不用单例模式；
　　(2):使用java.util.concurrent下面的类库；
　　(3):使用锁机制synchronized,lock方式；
 * 
 */
public class ThreadTest {
	public static void main(String[] args) {
		ThreadA threadA =new ThreadA();
		threadA.run();
		System.out.println("这是主线程");
		
//		new Thread(new ThreadB()).start();
//		System.out.println("这是主线程");
	}
}
