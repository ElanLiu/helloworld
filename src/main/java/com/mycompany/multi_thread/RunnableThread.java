package com.mycompany.multi_thread;

public class RunnableThread implements Runnable{
	
	public void run(){
		System.out.println(Thread.currentThread().getName() + " is running!");
	}

}
