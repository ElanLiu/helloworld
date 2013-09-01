package com.mycompany.nio;

public class ExTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int i = 1;
		
		int j = 0;
		
		int k = 1;
		
		try{
			k ? System.out.print("mm") : System.out.print("ll");
		}catch(Exception e){
			System.out.print("dddddd");
		}finally{
			System.out.print("fffffff");
		}
		
		System.out.print("hhhhhhhhh");

	}

}
