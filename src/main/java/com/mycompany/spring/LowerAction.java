package com.mycompany.spring;

public class LowerAction implements Action{
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String execute(String str) {
		
		return (getMessage() + str).toLowerCase();
	}

}
