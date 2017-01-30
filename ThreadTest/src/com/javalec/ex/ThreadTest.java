package com.javalec.ex;

public class ThreadTest implements Runnable{

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName());
		System.out.println("Thread Test");
		
		for (int i = 0; i < 10; i++) {
			System.out.println("i : " + i);
			try {
				Thread.sleep(500);	// 0.5초 재운다. 하지만 0.5초후에 바로 시작될지 안될지는 알 수가 없다.
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
