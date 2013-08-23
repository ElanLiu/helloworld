package com.mycompany.spring.di;

public class InterfaceBImpl implements InterfaceB{
	private String name;

	@Override
	public void doIt() {
		System.out.println(name + " "+ "interfaceB: doIt");
		
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
