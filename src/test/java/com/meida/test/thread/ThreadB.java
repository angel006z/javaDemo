package com.meida.test.thread;

public class ThreadB implements Runnable{

	@Override
	public void run() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("这是子线程B");
	}

}
