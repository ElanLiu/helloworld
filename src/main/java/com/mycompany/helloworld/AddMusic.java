package com.mycompany.helloworld;

public class AddMusic extends Decorator{
	

	public AddMusic(Clock clock) {
		super(clock);
	}
	
	public void ring(){
		addMusic();
		super.ring();
	}
	
	public static void addMusic(){
		System.out.println("MMMMMusic");
	}
	

}
