package com.mycompany.helloworld;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;

import org.apache.log4j.Logger;

public class IODemo {
	private static final Logger logger = Logger.getLogger(IODemo.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException{
		
		File file = new File("123.txt");
		
		//reading input by lines
		BufferedReader in = new BufferedReader(new FileReader(file));
		
		String str1, str2 = "";
		
		while((str1 = in.readLine()) != null){
			str2 += str1 + "\n";
		}
		logger.info("reading input by lines: " + "\n" + str2);
		in.close();
		
		logger.info("=====================================");

		//Input from memory
		StringReader in2 = new StringReader(str2);
		int bytes_read;
		while((bytes_read = in2.read()) != -1){
			logger.info((char)bytes_read);
		}
		
		
		
	}

}
