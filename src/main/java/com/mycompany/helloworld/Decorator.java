package com.mycompany.helloworld;

public class Decorator implements Clock{
	
	private Clock clock;
	
	public Decorator(Clock clock){
		this.clock = clock;
	}

	@Override
	public void ring() {
		clock.ring();
		
	}

}
