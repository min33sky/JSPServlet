package com.javalec.ex;

public class MainClass {
	public static void main(String[] args) {
//		ThreadTest tt = new ThreadTest();
//		Thread thread = new Thread(tt, "A");
//		thread.start();
//		
		ThreadTest2 tt = new ThreadTest2();
		Thread thread = new Thread(tt, "B");
		thread.start();
		System.out.println(Thread.currentThread().getName());
		
		
	}
}
