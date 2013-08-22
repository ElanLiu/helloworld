package com.mycompany.Decorator;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		InputStream in = new FileInputStream();
		in.say();
		
		InputStream in2 = new BufferedInputStream(new FileInputStream());
		
		in2.say();
		
	}

}
