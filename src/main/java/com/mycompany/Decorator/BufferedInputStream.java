package com.mycompany.Decorator;

public class BufferedInputStream extends FilterInputStream{

	public BufferedInputStream(InputStream in) {
		super(in);
	}
	
	@Override
	public void say() {
		super.say();
		
		System.out.print(" : BufferedInputStream");
	}

}
