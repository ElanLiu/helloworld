package com.mycompany.spring.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class DIType2 {
	
	private InterfaceA interfaceA;

	private InterfaceB interfaceB;
	
	public void doSomething(){
		ApplicationContext ctx = new FileSystemXmlApplicationContext("src/main/resources/META-INF/spring/spring.xml");
		
		interfaceA = (InterfaceA) ctx.getBean("interfaceA");
		
		interfaceB = (InterfaceB) ctx.getBean("interfaceB");
		
		interfaceA.doIt();
		
		interfaceB.doIt();
	}

}
