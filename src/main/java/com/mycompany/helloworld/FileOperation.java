package com.mycompany.helloworld;

import java.io.File;

import org.apache.log4j.Logger;

public class FileOperation {
	
	private static final Logger logger = Logger.getLogger(FileOperation.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File file = new File(".");
		
		File file2 = new File("./tt");
		
		try {
			fileData(file);
			logger.info("===================");
			//delete("tt");
			fileData(file);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	
	public static void fileData(File file) throws Exception{
		if(file == null){
			logger.info("File is null!");
			return;
			}
			
		if(file.isDirectory() || file.list().length > 0){
			logger.info("File is directory!");
			
			for(String str : file.list()){
				logger.info(str);
			}
		}
		
		
	}
	
	public static void delete(String fileName){
		File file = new File(fileName);
		
		if(!file.exists())
			return;
		if(!file.canRead())
			return;
		
		file.delete();
	}
}
