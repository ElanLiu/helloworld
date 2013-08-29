package com.mycompany.spring.applicationcontext;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class LoginAction implements ApplicationContextAware{
	/*
	 * 事件传播
	 * 
	 * 基于Observer模式，ApplicationContext提供的发布功能，通知ApplicationListener
	 * 
	 * 登陆后，发布事件
	 */
	
	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
		
	}
	
	public int login(String userName, String password){
		ActionEvent event = new ActionEvent(userName);
		
		this.applicationContext.publishEvent(event);
		
		return 0;
	}

}
