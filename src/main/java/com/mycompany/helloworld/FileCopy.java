package com.mycompany.helloworld;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileCopy {
	
	private static char i;
	
	private static char j;
	
	private static byte h;
	
	private static byte p;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		i = 111;
		
		j = '在';
		
		h = 12;
		
		p = 'a';
		
		System.out.println(i);
		System.out.println(j);
		System.out.println(h);
		System.out.println(p);

	}
	
	public static void copy(String from_file, String to_file) throws IOException{
		File from = new File(from_file);
		
		File to= new File(to_file);
		
		if(from.equals(to))
			fileException("The same!");
		
		if(!from.exists())
			fileException("Not exists!");
		
		if(!from.isFile())
			fileException("Not a file!");
		
		if(!from.canRead())
			fileException("Can not read!");
		
		///////////////////////////////////
		
		if(!to.exists())
			fileException("Not exists!");
		
		if(to.isDirectory())
			to = new File(to, from_file);
		
		if(!to.canWrite())
			fileException("Can not write!");
		
		////////////////////////////////////
		
		InputStream in = null;
		OutputStream out = null;
		
		try{
			in = new FileInputStream(from);
			out = new FileOutputStream(to);
			
			byte[] buffer = new byte[4096];
			int bytes_read;
			
			while((bytes_read = in.read(buffer)) != -1){
				out.write(buffer, 0 ,bytes_read);
			}
		}finally{
			in.close();
			out.close();
		}
	}
	
	public static void fileException(String msg) throws IOException{
		throw new IOException("Encountered a error: " + msg);
	}

}
