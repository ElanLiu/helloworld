package com.mycompany.testng;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jumpmind.symmetric.csv.CsvReader;
import org.testng.annotations.DataProvider;

public class CsvDataProvider {
	
	private static List<Object[]> list = new ArrayList<Object[]>();
	
	private static final Logger logger = Logger.getLogger(CsvDataProvider.class);
	
	
	@DataProvider(name = "CsvDataProvider")
	public static Iterator<Object[]> csvDataProvider(Method mtd){
		
		//Class<?> clz = mtd.getClass();
		
		
		
		return getDataFromCsvFile(mtd).iterator();
	}
	
	public CsvDataProvider(Class clz, Method mtd){}
	
	public static File getFileAbsoluteDir( Method mtd){
		//String packageName = clz.getName();
		//String fileName = mtd.getName();
		//String fileDir = packageName + "/" + fileName + ".csv";
		String fileDir = "./src/main/resources/qq.csv";
		logger.debug("The file is: " + fileDir);
		
		return new File(fileDir);
	}
	
	@SuppressWarnings("rawtypes")
	public static boolean isFileExist(Method mtd){
		
		if( mtd.equals("") || null == mtd){
			if(logger.isInfoEnabled()){
				logger.info("The parameters input are illegal!");
			}
			return false;
		}
		//ClassLoader clzLoader = clz.getClassLoader();
		
		File file = getFileAbsoluteDir( mtd);
		if(!file.isFile()){
			if(logger.isInfoEnabled()){
				logger.info("The file do not exist!");
			}
			return false;
		}
		
		if(!file.canRead()){
			if(logger.isInfoEnabled()){
				logger.info("The file can not read!");
			}
			return false;
		}
		
		return true;
	}
	
	public static List<Object[]> getDataFromCsvFile(Method mtd){
		if(!isFileExist(mtd)){
			return null;
		}
		
		Reader reader = null;
		
		CsvReader csvReader = null;

		try {
			
			reader = new FileReader(getFileAbsoluteDir( mtd));
			
			csvReader = new CsvReader(reader);
			
			String[] values = null;
			
			while(csvReader.readRecord()){
				values = csvReader.getValues();
				logger.info("=======");
				list.add(values);
			}
		} catch (FileNotFoundException e) {
			logger.debug("");
			e.printStackTrace();
			return null;
		} catch(IOException e){
			logger.debug("");
			e.printStackTrace();
			return null;
		}catch(Exception e){
			logger.debug("=====");
			e.printStackTrace();
			return null;
		}
		return list;
	}

}
