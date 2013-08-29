package com.mycompany.annotation;

import java.lang.annotation.Annotation;

import com.sun.org.apache.bcel.internal.classfile.Method;

public class DoSomething {
	
	
	
	@Todo(value="dd")
	public static void main(String args[]){
		//doIt();
		
		java.lang.reflect.Method[] mtd = TodoDemo.class.getDeclaredMethods();
		

		for(java.lang.reflect.Method m : mtd){
			boolean b = m.isAnnotationPresent(Todo.class);
			if(b){
				
				Todo to = m.getAnnotation(Todo.class);
				System.out.println(to.value());
				System.out.println(to.descrip());
			}
		}
	}

}
