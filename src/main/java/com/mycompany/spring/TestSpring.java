package com.mycompany.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class TestSpring {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext ctx = new FileSystemXmlApplicationContext(
				"spring.xml");

		Action action = (Action) ctx.getBean("theAction");

		Action ac2 = new UpperAction();

		System.out.println(action.execute("gsfdadf"));
		System.out.println(ac2.execute("gsfdadf"));

		System.out.println("============");

		Action action2 = ActionFactory.getAction("TheAction");
		System.out.println(action2.execute("gsfdadf"));
		System.out.println("============");

		DIType1 type1 = new DIType1();
		type1.doSomething();
		System.out.println("============");

		DIType2 type2 = new DIType2();
		type2.doSomething();
		System.out.println("============");

		DIType3 type3 = new DIType3(new InterfaceAImpl(), new InterfaceBImpl());
		type3.doSomething();

	}

}
