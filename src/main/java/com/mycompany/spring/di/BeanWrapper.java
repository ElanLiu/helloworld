package com.mycompany.spring.di;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

public class BeanWrapper {
	Properties property = new Properties();
	
	public BeanWrapper(){
		
		try {
			property.load(new FileInputStream("config.properties"));
			
			String implA = property.getProperty("implA");
			
			Class<?> clzA = Class.forName(implA);
			
			Method mtd = clzA.getMethod(property.getProperty("method"), new Class[]{String.class});
			
			Object objA = clzA.newInstance();
			
			mtd.invoke(objA, new Object[]{property.getProperty("name")});
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(Exception e){}

		
		
	}

}
