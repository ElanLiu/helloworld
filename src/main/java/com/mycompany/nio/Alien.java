package com.mycompany.nio;

import java.io.Serializable;

public class Alien implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	
	private String sex;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	

}
