package com.mycompany.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class DIType2 {
	
	private InterfaceA interfaceA;

	private InterfaceB interfaceB;
	
	public void doSomething(){
		ApplicationContext ctx = new FileSystemXmlApplicationContext("spring.xml");
		
		interfaceA = (InterfaceA) ctx.getBean("interfaceA");
		
		interfaceB = (InterfaceB) ctx.getBean("interfaceB");
		
		interfaceA.doIt();
		
		interfaceB.doIt();
	}

}
