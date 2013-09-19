package com.mycompany.multi_thread;

import java.util.Timer;
import java.util.TimerTask;

import utils.TimeoutUtil;

public class Test {
	
	private  Thread t;
	private static int j;
	private int k;
	
	public static synchronized int getValue(){
		return j;
	}
	
	public  void f1(){
		t = new Thread(){
			public void run(){
				while(true){
					System.out.println(Thread.currentThread().getName() + ": " + ++j);
					try {
						Thread.currentThread().sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		t.start();
	}
	
	public  void f2(){
		t = new Thread(new Runnable(){

			@Override
			public void run() {
				while(true){
					System.out.println(Thread.currentThread().getName()+ ": " + ++j);
					try {
						Thread.currentThread().sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		});
		t.start();
	}
	
	private static boolean finished = false;
	
	public static void main(String[] args) {
		Timer timer = new Timer();
		
		new Thread("AA"){
			public void run(){
				
					
						System.out.println(getValue());
						System.out.println(Thread.currentThread().getName());
						j = 99;
						System.out.println(getValue());
					
				
				
			}
		}.start();
		
		new Thread("BB"){
			public void run(){
				System.out.println(Thread.currentThread().getName());
					j=100;
					System.out.println(getValue());
					
				
			}
		}.start();


	}

}
