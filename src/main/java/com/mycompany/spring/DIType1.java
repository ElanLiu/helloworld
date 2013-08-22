package com.mycompany.spring;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class DIType1 {

	private InterfaceA interfaceA;

	private InterfaceB interfaceB;

	Properties property = new Properties();

	public void doSomething() {
		try {
			property.load(new FileInputStream("config.properties"));

			String implA = property.getProperty("implA");

			Object objA = Class.forName(implA).newInstance();

			String implB = property.getProperty("implB");

			Object objB = Class.forName(implB).newInstance();

			interfaceA = (InterfaceA) objA;

			interfaceA.doIt();
			
			interfaceB = (InterfaceB) objB;

			interfaceB.doIt();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
		}
	}

}
