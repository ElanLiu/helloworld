package com.mycompany.testng;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.jumpmind.symmetric.csv.CsvReader;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestDemo {
	
	public static List<String> strList = new ArrayList<String>();
	public static List<Integer> intList = new ArrayList<Integer>();
	public static List<?> list = new ArrayList<>();
	

	public static void main(String args[]) throws Exception{
		strList.add("ddd");
		intList.add(new Integer(3));
		
		list = strList;
		list = intList;
		
		System.out.println(TestDemo.class.getClass().getResource("qq.csv"));
		
		File file = new File("./src/main/resources/qq.csv");
		
		Reader reader = new FileReader(file);
		
		CsvReader csvReader = new CsvReader (reader);
		
		String[] str = null;
		List<String[]> list = new ArrayList<String[]>();
		
		while(csvReader.readRecord()){
			list.add(csvReader.getValues());
		}
		
		for(String[] s : list){
			for(String s2: s){
				System.out.print(s2);
				System.out.print(",");
			}
			
		}
		
	}
	

	/**
	 * @param args
	 */
	public  int i=0;
	
	@Test(dataProvider="CsvDataProvider2", dataProviderClass=com.mycompany.testng.CsvDataProvider2.class)
	public void test666(int name, String sex){
		System.out.println(name);
		
		
	}
	
	
	
	//@DataProvider(name = "CsvDataProvider1")
	public Object[][] createData(Method mtd){
		String[][] str = {
				{"asd", "ddd"},
				{"bb", "gg"}
		};
		
		System.out.println("11111");
		print(mtd);
		return str;
	}
	
	public static void print(Method mtd){
		System.out.println(mtd.getName());
	}
	
	@DataProvider(name = "CsvDataProvider1")
	public Iterator<Object[]> csvDataProvider(Method mtd){
		List<Object[]> list = new ArrayList<Object[]>();
		String[] str = {"aa", "dd"};
		//Object[] obj= {new Integer(1), new Integer(2)};
		list.add(str);
		print(mtd);
		
		System.out.println(mtd.getDeclaringClass().getSimpleName());
		return list.iterator();
	}

}
