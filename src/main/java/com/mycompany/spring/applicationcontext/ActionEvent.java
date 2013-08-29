package com.mycompany.spring.applicationcontext;

import org.springframework.context.ApplicationEvent;

public class ActionEvent extends ApplicationEvent{

	public ActionEvent(Object source) {
		super(source);
	}

}
