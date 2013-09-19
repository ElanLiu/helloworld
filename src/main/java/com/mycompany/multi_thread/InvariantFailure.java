package com.mycompany.multi_thread;

public class InvariantFailure implements InvariantState{
	public Object value;
	
	public InvariantFailure(Object obj){
		this.value = obj;
	}
}
