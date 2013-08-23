package com.mycompany.spring.di;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

public class DIType1 {

	private InterfaceA interfaceA;

	private InterfaceB interfaceB;

	Properties property = new Properties();

	public void doSomething() {
		try {
			/*
			 * BeanWrapper--start
			 */
			property.load(new FileInputStream("config.properties"));

			String implA = property.getProperty("implA");
			
			Class<?> clzA = Class.forName(implA);
			
			Method mtd = clzA.getMethod(property.getProperty("method"), new Class[]{String.class});
			
			Object objA = clzA.newInstance();
			
			mtd.invoke(objA, new Object[]{property.getProperty("name")});
			
			interfaceA = (InterfaceA) objA;

			interfaceA.doIt();
			/*
			 * BeanWrapper--end
			 */
			
			
			String implB = property.getProperty("implB");

			Object objB = Class.forName(implB).newInstance();
			
			interfaceB = (InterfaceB) objB;

			interfaceB.doIt();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
