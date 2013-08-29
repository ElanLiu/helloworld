package com.mycompany.spring.di;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.Resource;

public class BeanFactory {
	InputStream in;
	
	InterfaceA interfaceA;
	
	public BeanFactory(){
		try {
			in = new FileInputStream("src/main/resources/META-INF/spring/spring.xml");
			
			XmlBeanFactory factory = new XmlBeanFactory((Resource) in);
			
			interfaceA = (InterfaceA) factory.getBean("interfaceA");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
