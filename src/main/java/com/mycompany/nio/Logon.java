package com.mycompany.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Logon implements Serializable{
	
	private String name;
	
	private transient String passwd;
	
	private String sex;

	/**
	 * @param args
	 */
	
	public Logon(String name, String passwd, String sex){
		this.name = name;
		this.passwd = passwd;
		this.sex = sex;
	}
	
	public static void main(String[] args) throws IOException, IOException, Exception {
		Logon log = new Logon("Eric", "12123", "male");
		System.out.println(log);
		
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("logon.out"));
		out.writeObject(log);
		out.close();
		System.out.println("==============");
		Thread.sleep(1000);
		
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("logon.out"));
		
		Logon ll;
		
		ll = (Logon) in.readObject();
		in.close();
		System.out.println(ll);
	}
	
	public String toString(){
		return "name: " + name + "\n" + "passwd: " + passwd + "\n" + "sex: " + sex;
	}

}
