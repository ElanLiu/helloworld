package com.mycompany.spring.di;

public class DIType3 {
	private InterfaceA interfaceA;

	private InterfaceB interfaceB;
	
	public void doSomething(){
		interfaceA.doIt();
		interfaceB.doIt();
	}
	
	public DIType3(InterfaceAImpl interfaceA, InterfaceBImpl interfaceB){
		this.interfaceA = interfaceA;
		this.interfaceB = interfaceB;
	}

}
