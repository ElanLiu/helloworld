package com.mycompany.spring.applicationcontext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.mycompany.spring.di.DIType1;
import com.mycompany.spring.di.DIType2;
import com.mycompany.spring.di.DIType3;
import com.mycompany.spring.di.InterfaceAImpl;
import com.mycompany.spring.di.InterfaceBImpl;

public class Demo {
	public static void main(String[] args) {
		ApplicationContext ctx = new FileSystemXmlApplicationContext(
				"src/main/resources/META-INF/spring/spring.xml");

		LoginAction action = (LoginAction) ctx.getBean("loginaction");
		
		action.login("Eric", "123");

	}
}
