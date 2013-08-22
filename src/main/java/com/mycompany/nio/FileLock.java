package com.mycompany.nio;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.*;

public class FileLock {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws IOException, InterruptedException {
		
		File file = new File("123.txt");
		FileOutputStream out = new FileOutputStream(file);
		
		java.nio.channels.FileLock fl = out.getChannel().tryLock();
		
		if(fl != null){
			Thread.sleep(10000);
			fl.release();
		}
		
		out.close();

	}

}
