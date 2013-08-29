package com.mycompany.testng;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.Iterator;
import java.util.List;

import org.jumpmind.symmetric.csv.CsvReader;
import org.testng.annotations.DataProvider;

public class CsvDataProvider {
	
	private File file;
	
	private Reader reader;
	
	private CsvReader csvReader;
	
	private List list;
	
	@DataProvider(name = "CsvDataProvider")
	public Iterator<Object[]> csvDataProvider(){
		
	}
	
	public List<Object[]> getDataFromCsv(){

		try {
			file = new File("123.csv");
			
			reader = new FileReader(file);
			
			csvReader = new CsvReader(reader);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
