package com.mycompany.nio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

public class FreezeAlien {

	/**
	 * @param args
	 */
	
	private static Alien alien = new Alien();
	
	public static void main(String[] args) throws IOException {
		alien.setName("War");
		
		alien.setSex("male");
		
		File file = new File("x.file");
		
		ObjectOutput out = new ObjectOutputStream(new FileOutputStream(file));
		
		out.writeObject(alien);

	}

}
