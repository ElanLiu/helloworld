package com.mycompany.testng;

import org.testng.annotations.Factory;

public class TestFactory {
	
	@Factory
	public Object[] createInstances(){
		
		Object[] obj = new Object[10];
		
		for(int i=0; i<10; i++){
			obj[i] = new StringUtilsTest(i);
		}
		
		return obj;
	}

}
