package com.mycompany.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ThawAlien {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws Exception 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException, Exception {
		File file = new File("x.file");
		
		
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
		
		Object my = in.readObject();
		
		Alien al = (Alien)in.readObject();

		
		
		
		System.out.print(al.getName() + al.getSex());

	}

}
