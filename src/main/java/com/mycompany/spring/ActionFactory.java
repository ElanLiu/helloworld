package com.mycompany.spring;

import java.io.FileInputStream;
import java.util.Properties;

public class ActionFactory {
	
	public static Action getAction(String name){
		Properties pro = new Properties();
		
		try{
			pro.load(new FileInputStream("config.properties"));
			
			String implName = (String) pro.get(name);
			
			String message = pro.getProperty("message");
			
			Object obj = Class.forName(implName).newInstance();
			
			return (Action) obj;
			
		}catch(Exception e){}
		
		return null;
	}

}
