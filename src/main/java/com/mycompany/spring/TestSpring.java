package com.mycompany.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.mycompany.spring.di.DIType1;
import com.mycompany.spring.di.DIType2;
import com.mycompany.spring.di.DIType3;
import com.mycompany.spring.di.InterfaceAImpl;
import com.mycompany.spring.di.InterfaceBImpl;

public class TestSpring {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext ctx = new FileSystemXmlApplicationContext(
				"src/main/resources/META-INF/spring/spring.xml");

		/*Action action = (Action) ctx.getBean("theAction");

		Action ac2 = new UpperAction();

		System.out.println(action.execute("gsfdadf"));
		System.out.println(ac2.execute("gsfdadf"));

		System.out.println("============");

		Action action2 = ActionFactory.getAction("TheAction");
		System.out.println(action2.execute("gsfdadf"));*/
		
		
		System.out.println("=====Type1=======");
		DIType1 type1 = new DIType1();
		type1.doSomething();
		
		System.out.println("======Type2======");
		DIType2 type2 = new DIType2();
		type2.doSomething();
		
		System.out.println("=======Type3=====");
		DIType3 type3 = new DIType3(new InterfaceAImpl(), new InterfaceBImpl());
		type3.doSomething();

	}

}
