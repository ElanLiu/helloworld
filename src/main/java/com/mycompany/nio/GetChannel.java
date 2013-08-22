package com.mycompany.nio;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.apache.log4j.Logger;

public class GetChannel {
	private static final int BSIZE = 1024;
	
	private static FileChannel fc;
	
	private static File file ;
	
	private static final Logger logger = Logger.getLogger(GetChannel.class);
	
	public static void main(String args[]) throws IOException{
		
		file = new File("123.txt");
		
		fc = new FileOutputStream(file).getChannel();
		
		fc.write(ByteBuffer.wrap("sss text".getBytes()));
		
		fc.close();
		
		//BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
		
		bw.write("fffffffff");
		
		bw.close();
	}

}
