package com.mycompany.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class TestSpring {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext ctx = new FileSystemXmlApplicationContext("spring.xml");
		
		Action action = (Action) ctx.getBean("theAction");
		
		Action ac2 = new UpperAction();
		
		System.out.println(action.execute("gsfdadf"));
		System.out.println(ac2.execute("gsfdadf"));
		
		System.out.println("============");
		
		Action action2 = ActionFactory.getAction("TheAction");
		System.out.println(action2.execute("gsfdadf"));
		

	}

}
