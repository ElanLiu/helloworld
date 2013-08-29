package com.mycompany.annotation;



public class TodoDemo{

	@Todo(value="doIt")
	public void doIt(){}
	
	@Todo(value = "doItAgain", descrip="descrip")
	public void doItAgain(){}

}
