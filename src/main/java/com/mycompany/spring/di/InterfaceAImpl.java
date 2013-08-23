package com.mycompany.spring.di;

public class InterfaceAImpl implements InterfaceA{
	
	private String name;

	@Override
	public void doIt() {
		System.out.println(name + " "+ "interfaceA: doIt");
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
